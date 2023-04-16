package com.baidubce.services.bos;

import android.annotation.SuppressLint;
import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.BceConfig;
import com.baidubce.BceServiceException;
import com.baidubce.auth.BceV1Signer;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.StatusCodes;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.BosMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.internal.RestartableNonResettableInputStream;
import com.baidubce.internal.RestartableResettableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.User;
import com.baidubce.services.bos.callback.BosProgressCallback;
import com.baidubce.services.bos.common.utils.BosUtils;
import com.baidubce.services.bos.model.AbortMultipartUploadRequest;
import com.baidubce.services.bos.model.AppendObjectRequest;
import com.baidubce.services.bos.model.AppendObjectResponse;
import com.baidubce.services.bos.model.BosObject;
import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.bos.model.BosResponse;
import com.baidubce.services.bos.model.CannedAccessControlList;
import com.baidubce.services.bos.model.CompleteMultipartUploadRequest;
import com.baidubce.services.bos.model.CompleteMultipartUploadResponse;
import com.baidubce.services.bos.model.CopyObjectRequest;
import com.baidubce.services.bos.model.CopyObjectResponse;
import com.baidubce.services.bos.model.CopyObjectResponseWithExceptionInfo;
import com.baidubce.services.bos.model.CreateBucketRequest;
import com.baidubce.services.bos.model.CreateBucketResponse;
import com.baidubce.services.bos.model.DeleteBucketRequest;
import com.baidubce.services.bos.model.DeleteObjectRequest;
import com.baidubce.services.bos.model.DoesBucketExistRequest;
import com.baidubce.services.bos.model.GeneratePresignedUrlRequest;
import com.baidubce.services.bos.model.GetBosAccountOwnerRequest;
import com.baidubce.services.bos.model.GetBucketAclRequest;
import com.baidubce.services.bos.model.GetBucketAclResponse;
import com.baidubce.services.bos.model.GetBucketLocationRequest;
import com.baidubce.services.bos.model.GetBucketLocationResponse;
import com.baidubce.services.bos.model.GetObjectMetadataRequest;
import com.baidubce.services.bos.model.GetObjectRequest;
import com.baidubce.services.bos.model.GetObjectResponse;
import com.baidubce.services.bos.model.InitiateMultipartUploadRequest;
import com.baidubce.services.bos.model.InitiateMultipartUploadResponse;
import com.baidubce.services.bos.model.ListBucketsRequest;
import com.baidubce.services.bos.model.ListBucketsResponse;
import com.baidubce.services.bos.model.ListMultipartUploadsRequest;
import com.baidubce.services.bos.model.ListMultipartUploadsResponse;
import com.baidubce.services.bos.model.ListObjectsRequest;
import com.baidubce.services.bos.model.ListObjectsResponse;
import com.baidubce.services.bos.model.ListPartsRequest;
import com.baidubce.services.bos.model.ListPartsResponse;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PartETag;
import com.baidubce.services.bos.model.PutObjectRequest;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.baidubce.services.bos.model.PutSuperObjectRequest;
import com.baidubce.services.bos.model.PutSuperObjectResponse;
import com.baidubce.services.bos.model.ResponseHeaderOverrides;
import com.baidubce.services.bos.model.SetBucketAclRequest;
import com.baidubce.services.bos.model.UploadPartRequest;
import com.baidubce.services.bos.model.UploadPartResponse;
import com.baidubce.util.BLog;
import com.baidubce.util.CheckUtils;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;

@SuppressLint({"NewApi"})
public class BosClient extends AbstractBceClient {
    public static final String STORAGE_CLASS_COLD = "COLD";
    public static final String STORAGE_CLASS_STANDARD = "STANDARD";
    public static final String STORAGE_CLASS_STANDARD_IA = "STANDARD_IA";
    private static final HttpResponseHandler[] bos_handlers = {new BceMetadataResponseHandler(), new BosMetadataResponseHandler(), new BceErrorResponseHandler(), new BosObjectResponseHandler(), new BceJsonResponseHandler()};

    public BosClient() {
        this(new BosClientConfiguration());
    }

    public BosClient(BosClientConfiguration bosClientConfiguration) {
        super(bosClientConfiguration, bos_handlers);
    }

    public User getBosAccountOwner() {
        return getBosAccountOwner(new GetBosAccountOwnerRequest());
    }

    public User getBosAccountOwner(GetBosAccountOwnerRequest getBosAccountOwnerRequest) {
        CheckUtils.isNotNull(getBosAccountOwnerRequest, "request should not be null.");
        return ((ListBucketsResponse) invokeHttpClient(createRequest(getBosAccountOwnerRequest, HttpMethodName.GET), ListBucketsResponse.class)).getOwner();
    }

    public ListBucketsResponse listBuckets() {
        return listBuckets(new ListBucketsRequest());
    }

    public ListBucketsResponse listBuckets(ListBucketsRequest listBucketsRequest) {
        CheckUtils.isNotNull(listBucketsRequest, "request should not be null.");
        return (ListBucketsResponse) invokeHttpClient(createRequest(listBucketsRequest, HttpMethodName.GET), ListBucketsResponse.class);
    }

    public CreateBucketResponse createBucket(String str) {
        return createBucket(new CreateBucketRequest(str));
    }

    public CreateBucketResponse createBucket(CreateBucketRequest createBucketRequest) {
        CheckUtils.isNotNull(createBucketRequest, "request should not be null.");
        InternalRequest createRequest = createRequest(createBucketRequest, HttpMethodName.PUT);
        setZeroContentLength(createRequest);
        CreateBucketResponse createBucketResponse = new CreateBucketResponse();
        createBucketResponse.setName(createBucketRequest.getBucketName());
        createBucketResponse.setLocation(((BosResponse) invokeHttpClient(createRequest, BosResponse.class)).getMetadata().getLocation());
        return createBucketResponse;
    }

    public GetBucketLocationResponse getBucketLocation(String str) {
        return getBucketLocation(new GetBucketLocationRequest(str));
    }

    public GetBucketLocationResponse getBucketLocation(GetBucketLocationRequest getBucketLocationRequest) {
        CheckUtils.isNotNull(getBucketLocationRequest, "request should not be null.");
        InternalRequest createRequest = createRequest(getBucketLocationRequest, HttpMethodName.GET);
        createRequest.addParameter("location", (String) null);
        return (GetBucketLocationResponse) invokeHttpClient(createRequest, GetBucketLocationResponse.class);
    }

    public boolean doesBucketExist(String str) {
        return doesBucketExist(new DoesBucketExistRequest(str));
    }

    public boolean doesBucketExist(DoesBucketExistRequest doesBucketExistRequest) {
        CheckUtils.isNotNull(doesBucketExistRequest, "request should not be null.");
        try {
            invokeHttpClient(createRequest(doesBucketExistRequest, HttpMethodName.HEAD), BosResponse.class);
            return true;
        } catch (BceServiceException e) {
            if (e.getStatusCode() == 403) {
                return true;
            }
            if (e.getStatusCode() == 404) {
                return false;
            }
            throw e;
        }
    }

    public GetBucketAclResponse getBucketAcl(String str) {
        return getBucketAcl(new GetBucketAclRequest(str));
    }

    public GetBucketAclResponse getBucketAcl(GetBucketAclRequest getBucketAclRequest) {
        CheckUtils.isNotNull(getBucketAclRequest, "request should not be null.");
        InternalRequest createRequest = createRequest(getBucketAclRequest, HttpMethodName.GET);
        createRequest.addParameter("acl", (String) null);
        GetBucketAclResponse getBucketAclResponse = (GetBucketAclResponse) invokeHttpClient(createRequest, GetBucketAclResponse.class);
        if (getBucketAclResponse.getVersion() <= 1) {
            return getBucketAclResponse;
        }
        throw new BceClientException("Unsupported acl version.");
    }

    public void setBucketAcl(String str, CannedAccessControlList cannedAccessControlList) throws JSONException {
        setBucketAcl(new SetBucketAclRequest(str, cannedAccessControlList));
    }

    public void setBucketAcl(SetBucketAclRequest setBucketAclRequest) throws JSONException {
        CheckUtils.isNotNull(setBucketAclRequest, "request should not be null.");
        InternalRequest createRequest = createRequest(setBucketAclRequest, HttpMethodName.PUT);
        createRequest.addParameter("acl", (String) null);
        if (setBucketAclRequest.getCannedAcl() != null) {
            createRequest.addHeader(Headers.BCE_ACL, setBucketAclRequest.getCannedAcl().toString());
            setZeroContentLength(createRequest);
        } else if (setBucketAclRequest.getAccessControlList() != null) {
            try {
                byte[] bytes = JsonUtils.setAclJson(setBucketAclRequest.getAccessControlList()).getBytes("UTF-8");
                createRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(bytes.length));
                createRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
                createRequest.setContent(RestartableInputStream.wrap(bytes));
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes:" + e.getMessage(), e);
            }
        } else {
            CheckUtils.isNotNull(null, "request.acl should not be null.");
        }
        invokeHttpClient(createRequest, BosResponse.class);
    }

    public void deleteBucket(String str) {
        deleteBucket(new DeleteBucketRequest(str));
    }

    public void deleteBucket(DeleteBucketRequest deleteBucketRequest) {
        CheckUtils.isNotNull(deleteBucketRequest, "request should not be null.");
        invokeHttpClient(createRequest(deleteBucketRequest, HttpMethodName.DELETE), BosResponse.class);
    }

    public URL generatePresignedUrl(String str, String str2, int i) {
        return generatePresignedUrl(str, str2, i, HttpMethodName.GET);
    }

    public URL generatePresignedUrl(String str, String str2, int i, HttpMethodName httpMethodName) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(str, str2, httpMethodName);
        generatePresignedUrlRequest.setExpiration(i);
        return generatePresignedUrl(generatePresignedUrlRequest);
    }

    public URL generatePresignedUrl(GeneratePresignedUrlRequest generatePresignedUrlRequest) {
        String str;
        CheckUtils.isNotNull(generatePresignedUrlRequest, "The request parameter must be specified when generating a pre-signed URL");
        HttpMethodName valueOf = HttpMethodName.valueOf(generatePresignedUrlRequest.getMethod().toString());
        Boolean isCnameEnabled = ((BosClientConfiguration) this.config).isCnameEnabled();
        if (isCnameEnabled == Boolean.FALSE || (isCnameEnabled == null && !BosUtils.isCnameLikeHost(getEndpoint().getHost()))) {
            str = generatePresignedUrlRequest.getBucketName();
        } else {
            str = null;
        }
        InternalRequest internalRequest = new InternalRequest(valueOf, HttpUtils.appendUri(getEndpoint(), AbstractBceClient.URL_PREFIX, str, generatePresignedUrlRequest.getKey()));
        internalRequest.setCredentials(generatePresignedUrlRequest.getRequestCredentials());
        SignOptions signOptions = new SignOptions();
        signOptions.setExpirationInSeconds(generatePresignedUrlRequest.getExpiration());
        for (Map.Entry next : generatePresignedUrlRequest.getRequestHeaders().entrySet()) {
            if (next.getValue() == null) {
                internalRequest.addHeader((String) next.getKey(), "");
            } else {
                internalRequest.addHeader((String) next.getKey(), (String) next.getValue());
            }
        }
        for (Map.Entry next2 : generatePresignedUrlRequest.getRequestParameters().entrySet()) {
            if (next2.getValue() == null) {
                internalRequest.addParameter((String) next2.getKey(), "");
            } else {
                internalRequest.addParameter((String) next2.getKey(), (String) next2.getValue());
            }
        }
        if (generatePresignedUrlRequest.getContentType() != null) {
            internalRequest.addHeader(Headers.CONTENT_TYPE, generatePresignedUrlRequest.getContentType());
        }
        if (generatePresignedUrlRequest.getContentMd5() != null) {
            internalRequest.addHeader(Headers.CONTENT_MD5, generatePresignedUrlRequest.getContentMd5());
        }
        addResponseHeaderParameters(internalRequest, generatePresignedUrlRequest.getResponseHeaders());
        new BceV1Signer().sign(internalRequest, this.config.getCredentials(), signOptions);
        return convertRequestToUrl(internalRequest);
    }

    public ListObjectsResponse listObjects(String str) {
        return listObjects(new ListObjectsRequest(str));
    }

    public ListObjectsResponse listObjects(String str, String str2) {
        return listObjects(new ListObjectsRequest(str, str2));
    }

    public ListObjectsResponse listObjects(ListObjectsRequest listObjectsRequest) {
        CheckUtils.isNotNull(listObjectsRequest, "request should not be null.");
        InternalRequest createRequest = createRequest(listObjectsRequest, HttpMethodName.GET);
        if (listObjectsRequest.getPrefix() != null) {
            createRequest.addParameter("prefix", listObjectsRequest.getPrefix());
        }
        if (listObjectsRequest.getMarker() != null) {
            createRequest.addParameter("marker", listObjectsRequest.getMarker());
        }
        if (listObjectsRequest.getDelimiter() != null) {
            createRequest.addParameter("delimiter", listObjectsRequest.getDelimiter());
        }
        if (listObjectsRequest.getMaxKeys() >= 0) {
            createRequest.addParameter("maxKeys", String.valueOf(listObjectsRequest.getMaxKeys()));
        }
        ListObjectsResponse listObjectsResponse = (ListObjectsResponse) invokeHttpClient(createRequest, ListObjectsResponse.class);
        listObjectsResponse.setBucketName(listObjectsRequest.getBucketName());
        for (BosObjectSummary bucketName : listObjectsResponse.getContents()) {
            bucketName.setBucketName(listObjectsRequest.getBucketName());
        }
        return listObjectsResponse;
    }

    public ListObjectsResponse listNextBatchOfObjects(ListObjectsResponse listObjectsResponse) {
        CheckUtils.isNotNull(listObjectsResponse, "previousResponse should not be null.");
        if (listObjectsResponse.isTruncated()) {
            return listObjects(new ListObjectsRequest(listObjectsResponse.getBucketName()).withPrefix(listObjectsResponse.getPrefix()).withMarker(listObjectsResponse.getNextMarker()).withDelimiter(listObjectsResponse.getDelimiter()).withMaxKeys(listObjectsResponse.getMaxKeys()));
        }
        ListObjectsResponse listObjectsResponse2 = new ListObjectsResponse();
        listObjectsResponse2.setBucketName(listObjectsResponse.getBucketName());
        listObjectsResponse2.setDelimiter(listObjectsResponse.getDelimiter());
        listObjectsResponse2.setMarker(listObjectsResponse.getNextMarker());
        listObjectsResponse2.setMaxKeys(listObjectsResponse.getMaxKeys());
        listObjectsResponse2.setPrefix(listObjectsResponse.getPrefix());
        listObjectsResponse2.setTruncated(false);
        return listObjectsResponse2;
    }

    public BosObject getObject(String str, String str2) {
        return getObject(new GetObjectRequest(str, str2));
    }

    public ObjectMetadata getObject(String str, String str2, File file) {
        return getObject(new GetObjectRequest(str, str2), file);
    }

    public BosObject getObject(GetObjectRequest getObjectRequest) {
        CheckUtils.isNotNull(getObjectRequest, "request should not be null.");
        InternalRequest createRequest = createRequest(getObjectRequest, HttpMethodName.GET);
        long[] range = getObjectRequest.getRange();
        if (range != null) {
            createRequest.addHeader(Headers.RANGE, "bytes=" + range[0] + "-" + range[1]);
        }
        BosObject object = ((GetObjectResponse) invokeHttpClient(createRequest, GetObjectResponse.class)).getObject();
        object.setBucketName(getObjectRequest.getBucketName());
        object.setKey(getObjectRequest.getKey());
        return object;
    }

    public ObjectMetadata getObject(GetObjectRequest getObjectRequest, File file) {
        CheckUtils.isNotNull(getObjectRequest, "request should not be null.");
        CheckUtils.isNotNull(file, "destinationFile should not be null.");
        BosObject object = getObject(getObjectRequest);
        downloadObjectToFile(object, file, getObjectRequest.getRange() == null);
        return object.getObjectMetadata();
    }

    public byte[] getObjectContent(String str, String str2) {
        return getObjectContent(new GetObjectRequest(str, str2));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:8|9|10|11|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0016 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getObjectContent(com.baidubce.services.bos.model.GetObjectRequest r5) {
        /*
            r4 = this;
            com.baidubce.services.bos.model.BosObject r5 = r4.getObject(r5)
            com.baidubce.services.bos.BosObjectInputStream r5 = r5.getObjectContent()
            byte[] r0 = com.baidubce.util.ConvertUtils.inputStreamToByte(r5)     // Catch:{ IOException -> 0x0012 }
            r5.close()     // Catch:{ IOException -> 0x000f }
        L_0x000f:
            return r0
        L_0x0010:
            r0 = move-exception
            goto L_0x0031
        L_0x0012:
            r0 = move-exception
            r5.close()     // Catch:{ IOException -> 0x0016 }
        L_0x0016:
            com.baidubce.BceClientException r1 = new com.baidubce.BceClientException     // Catch:{ all -> 0x0010 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0010 }
            r2.<init>()     // Catch:{ all -> 0x0010 }
            java.lang.String r3 = "Fail read object content:"
            r2.append(r3)     // Catch:{ all -> 0x0010 }
            java.lang.String r3 = r0.getMessage()     // Catch:{ all -> 0x0010 }
            r2.append(r3)     // Catch:{ all -> 0x0010 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0010 }
            r1.<init>(r2, r0)     // Catch:{ all -> 0x0010 }
            throw r1     // Catch:{ all -> 0x0010 }
        L_0x0031:
            r5.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.services.bos.BosClient.getObjectContent(com.baidubce.services.bos.model.GetObjectRequest):byte[]");
    }

    public ObjectMetadata getObjectMetadata(String str, String str2) {
        return getObjectMetadata(new GetObjectMetadataRequest(str, str2));
    }

    public ObjectMetadata getObjectMetadata(GetObjectMetadataRequest getObjectMetadataRequest) {
        CheckUtils.isNotNull(getObjectMetadataRequest, "request should not be null.");
        return ((GetObjectResponse) invokeHttpClient(createRequest(getObjectMetadataRequest, HttpMethodName.HEAD), GetObjectResponse.class)).getObject().getObjectMetadata();
    }

    public PutObjectResponse putObject(String str, String str2, File file) {
        return putObject(new PutObjectRequest(str, str2, file));
    }

    public PutObjectResponse putObject(String str, String str2, File file, ObjectMetadata objectMetadata) {
        return putObject(new PutObjectRequest(str, str2, file, objectMetadata));
    }

    public PutObjectResponse putObject(String str, String str2, String str3) {
        try {
            return putObject(str, str2, str3.getBytes("UTF-8"), new ObjectMetadata());
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get bytes:" + e.getMessage(), e);
        }
    }

    public PutObjectResponse putObject(String str, String str2, String str3, ObjectMetadata objectMetadata) {
        try {
            return putObject(str, str2, str3.getBytes("UTF-8"), objectMetadata);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get bytes:" + e.getMessage(), e);
        }
    }

    public PutObjectResponse putObject(String str, String str2, byte[] bArr) {
        return putObject(str, str2, bArr, new ObjectMetadata());
    }

    public AppendObjectResponse appendObject(String str, String str2, File file) {
        return appendObject(new AppendObjectRequest(str, str2, file));
    }

    public AppendObjectResponse appendObject(String str, String str2, File file, ObjectMetadata objectMetadata) {
        return appendObject(new AppendObjectRequest(str, str2, file, objectMetadata));
    }

    public AppendObjectResponse appendObject(String str, String str2, String str3) {
        try {
            return appendObject(str, str2, str3.getBytes("UTF-8"), new ObjectMetadata());
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get bytes.", e);
        }
    }

    public AppendObjectResponse appendObject(String str, String str2, String str3, ObjectMetadata objectMetadata) {
        try {
            return appendObject(str, str2, str3.getBytes("UTF-8"), objectMetadata);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get bytes.", e);
        }
    }

    public AppendObjectResponse appendObject(String str, String str2, byte[] bArr) {
        return appendObject(str, str2, bArr, new ObjectMetadata());
    }

    public AppendObjectResponse appendObject(String str, String str2, byte[] bArr, ObjectMetadata objectMetadata) {
        CheckUtils.isNotNull(objectMetadata, "metadata should not be null.");
        if (objectMetadata.getContentLength() == -1) {
            objectMetadata.setContentLength((long) bArr.length);
        }
        return appendObject(new AppendObjectRequest(str, str2, (InputStream) RestartableInputStream.wrap(bArr), objectMetadata));
    }

    public AppendObjectResponse appendObject(String str, String str2, InputStream inputStream) {
        return appendObject(new AppendObjectRequest(str, str2, inputStream));
    }

    public AppendObjectResponse appendObject(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) {
        return appendObject(new AppendObjectRequest(str, str2, inputStream, objectMetadata));
    }

    public AppendObjectResponse appendObject(AppendObjectRequest appendObjectRequest) {
        CheckUtils.isNotNull(appendObjectRequest, "request should not be null.");
        assertStringNotNullOrEmpty(appendObjectRequest.getKey(), "object key should not be null or empty");
        InternalRequest createRequest = createRequest(appendObjectRequest, HttpMethodName.POST);
        createRequest.addParameter("append", (String) null);
        if (appendObjectRequest.getOffset() != null) {
            createRequest.addParameter("offset", appendObjectRequest.getOffset().toString());
        }
        BosResponse uploadObject = uploadObject(appendObjectRequest, createRequest);
        AppendObjectResponse appendObjectResponse = new AppendObjectResponse();
        appendObjectResponse.setNextAppendOffset(uploadObject.getMetadata().getNextAppendOffset());
        appendObjectResponse.setContentMd5(uploadObject.getMetadata().getContentMd5());
        appendObjectResponse.setETag(uploadObject.getMetadata().getETag());
        appendObjectResponse.setCrc32(uploadObject.getMetadata().getCrc32());
        appendObjectResponse.setNextAppendOffset(uploadObject.getMetadata().getNextAppendOffset());
        return appendObjectResponse;
    }

    public PutObjectResponse putObject(String str, String str2, byte[] bArr, ObjectMetadata objectMetadata) {
        if (objectMetadata.getContentLength() == -1) {
            objectMetadata.setContentLength((long) bArr.length);
        }
        return putObject(new PutObjectRequest(str, str2, (InputStream) RestartableInputStream.wrap(bArr), objectMetadata));
    }

    public PutObjectResponse putObject(String str, String str2, InputStream inputStream) {
        return putObject(new PutObjectRequest(str, str2, inputStream));
    }

    public PutObjectResponse putObject(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) {
        return putObject(new PutObjectRequest(str, str2, inputStream, objectMetadata));
    }

    public PutObjectResponse putObject(PutObjectRequest putObjectRequest) {
        CheckUtils.isNotNull(putObjectRequest, "request should not be null.");
        assertStringNotNullOrEmpty(putObjectRequest.getKey(), "object key should not be null or empty");
        BosResponse uploadObject = uploadObject(putObjectRequest, createRequest(putObjectRequest, HttpMethodName.PUT));
        PutObjectResponse putObjectResponse = new PutObjectResponse();
        putObjectResponse.setServerCallbackReturnBody(uploadObject.getServerCallbackReturnBody());
        putObjectResponse.setHttpResponse(uploadObject.getHttpResponse());
        putObjectResponse.setETag(uploadObject.getMetadata().getETag());
        putObjectResponse.setCrc32(uploadObject.getMetadata().getCrc32());
        return putObjectResponse;
    }

    @Deprecated
    public PutObjectResponse putObject(PutObjectRequest putObjectRequest, BosProgressCallback bosProgressCallback) {
        putObjectRequest.setProgressCallback(bosProgressCallback);
        return putObject(putObjectRequest);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0082 A[SYNTHETIC, Splitter:B:31:0x0082] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.baidubce.services.bos.model.BosResponse uploadObject(com.baidubce.services.bos.model.PutObjectRequest r12, com.baidubce.internal.InternalRequest r13) {
        /*
            r11 = this;
            java.lang.String r0 = "The inputStream accured error"
            java.lang.String r1 = "Fail to close input stream"
            com.baidubce.services.bos.model.ObjectMetadata r2 = r12.getObjectMetadata()
            java.io.InputStream r3 = r12.getInputStream()
            java.io.File r4 = r12.getFile()
            r5 = 0
            if (r4 == 0) goto L_0x00b3
            java.io.File r3 = r12.getFile()
            long r7 = r3.length()
            r9 = 5368709120(0x140000000, double:2.6524947387E-314)
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 > 0) goto L_0x009c
            long r7 = r2.getContentLength()
            int r4 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x0034
            long r4 = r3.length()
            r2.setContentLength(r4)
        L_0x0034:
            java.lang.String r4 = r2.getContentType()
            if (r4 != 0) goto L_0x0045
            com.baidubce.util.Mimetypes r4 = com.baidubce.util.Mimetypes.getInstance()
            java.lang.String r4 = r4.getMimetype((java.io.File) r3)
            r2.setContentType(r4)
        L_0x0045:
            long r4 = r2.getContentLength()
            long r6 = r3.length()
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x008a
            r4 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0077 }
            r5.<init>(r3)     // Catch:{ Exception -> 0x0077 }
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x0072, all -> 0x006f }
            byte[] r6 = com.baidubce.util.HashUtils.computeSha256Hash(r5)     // Catch:{ Exception -> 0x0072, all -> 0x006f }
            char[] r6 = com.baidubce.util.ConvertUtils.encodeHex(r6)     // Catch:{ Exception -> 0x0072, all -> 0x006f }
            r4.<init>(r6)     // Catch:{ Exception -> 0x0072, all -> 0x006f }
            r2.setBceContentSha256(r4)     // Catch:{ Exception -> 0x0072, all -> 0x006f }
            r5.close()     // Catch:{ Exception -> 0x006b }
            goto L_0x008a
        L_0x006b:
            com.baidubce.util.BLog.error(r0)
            goto L_0x008a
        L_0x006f:
            r12 = move-exception
            r4 = r5
            goto L_0x0080
        L_0x0072:
            r12 = move-exception
            r4 = r5
            goto L_0x0078
        L_0x0075:
            r12 = move-exception
            goto L_0x0080
        L_0x0077:
            r12 = move-exception
        L_0x0078:
            com.baidubce.BceClientException r13 = new com.baidubce.BceClientException     // Catch:{ all -> 0x0075 }
            java.lang.String r1 = "Unable to calculate SHA-256 hash"
            r13.<init>(r1, r12)     // Catch:{ all -> 0x0075 }
            throw r13     // Catch:{ all -> 0x0075 }
        L_0x0080:
            if (r4 == 0) goto L_0x0089
            r4.close()     // Catch:{ Exception -> 0x0086 }
            goto L_0x0089
        L_0x0086:
            com.baidubce.util.BLog.error(r0)
        L_0x0089:
            throw r12
        L_0x008a:
            com.baidubce.internal.RestartableFileInputStream r0 = new com.baidubce.internal.RestartableFileInputStream     // Catch:{ FileNotFoundException -> 0x0093 }
            r0.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0093 }
            r13.setContent(r0)     // Catch:{ FileNotFoundException -> 0x0093 }
            goto L_0x00fc
        L_0x0093:
            r12 = move-exception
            com.baidubce.BceClientException r13 = new com.baidubce.BceClientException
            java.lang.String r0 = "Unable to find file to upload"
            r13.<init>(r0, r12)
            throw r13
        L_0x009c:
            com.baidubce.BceServiceException r12 = new com.baidubce.BceServiceException
            java.lang.String r13 = "Your proposed upload exceeds the maximum allowed object size."
            r12.<init>(r13)
            r13 = 400(0x190, float:5.6E-43)
            r12.setStatusCode(r13)
            java.lang.String r13 = "EntityTooLarge"
            r12.setErrorCode(r13)
            com.baidubce.BceServiceException$ErrorType r13 = com.baidubce.BceServiceException.ErrorType.Client
            r12.setErrorType(r13)
            throw r12
        L_0x00b3:
            java.lang.String r0 = "Either file or inputStream should be set."
            com.baidubce.util.CheckUtils.isNotNull(r3, r0)
            long r7 = r2.getContentLength()
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x00d6
            java.lang.String r0 = "No content length specified for stream data. Trying to read them all into memory."
            com.baidubce.util.BLog.warn(r0)
            java.util.List r0 = r11.readAll(r3, r2)
            com.baidubce.internal.RestartableMultiByteArrayInputStream r3 = new com.baidubce.internal.RestartableMultiByteArrayInputStream
            long r4 = r2.getContentLength()
            r3.<init>(r0, r4)
            r13.setContent(r3)
            goto L_0x00e7
        L_0x00d6:
            boolean r0 = r3 instanceof com.baidubce.internal.RestartableInputStream
            if (r0 == 0) goto L_0x00e0
            com.baidubce.internal.RestartableInputStream r3 = (com.baidubce.internal.RestartableInputStream) r3
            r13.setContent(r3)
            goto L_0x00e7
        L_0x00e0:
            com.baidubce.internal.RestartableInputStream r0 = r11.wrapRestartableInputStream(r3)
            r13.setContent(r0)
        L_0x00e7:
            java.lang.String r0 = r2.getContentType()
            if (r0 != 0) goto L_0x00fc
            com.baidubce.util.Mimetypes r0 = com.baidubce.util.Mimetypes.getInstance()
            java.lang.String r3 = r12.getKey()
            java.lang.String r0 = r0.getMimetype((java.lang.String) r3)
            r2.setContentType(r0)
        L_0x00fc:
            java.lang.String r0 = r12.getStorageClass()
            if (r0 == 0) goto L_0x0109
            java.lang.String r0 = r12.getStorageClass()
            r2.setStorageClass(r0)
        L_0x0109:
            java.lang.String r0 = r12.getProcess()
            if (r0 == 0) goto L_0x0118
            java.lang.String r0 = r12.getProcess()
            java.lang.String r3 = "x-bce-process"
            r13.addHeader(r3, r0)
        L_0x0118:
            long r3 = r2.getContentLength()
            java.lang.String r0 = java.lang.String.valueOf(r3)
            java.lang.String r3 = "Content-Length"
            r13.addHeader(r3, r0)
            populateRequestMetadata(r13, r2)
            java.lang.Class<com.baidubce.services.bos.model.BosResponse> r0 = com.baidubce.services.bos.model.BosResponse.class
            com.baidubce.services.bos.callback.BosProgressCallback r12 = r12.getProgressCallback()     // Catch:{ all -> 0x0141 }
            com.baidubce.model.AbstractBceResponse r12 = r11.invokeHttpClient(r13, r0, r12)     // Catch:{ all -> 0x0141 }
            com.baidubce.services.bos.model.BosResponse r12 = (com.baidubce.services.bos.model.BosResponse) r12     // Catch:{ all -> 0x0141 }
            com.baidubce.internal.RestartableInputStream r13 = r13.getContent()     // Catch:{ Exception -> 0x013c }
            r13.close()     // Catch:{ Exception -> 0x013c }
            goto L_0x0140
        L_0x013c:
            r13 = move-exception
            com.baidubce.util.BLog.error((java.lang.String) r1, (java.lang.Throwable) r13)
        L_0x0140:
            return r12
        L_0x0141:
            r12 = move-exception
            com.baidubce.internal.RestartableInputStream r13 = r13.getContent()     // Catch:{ Exception -> 0x014a }
            r13.close()     // Catch:{ Exception -> 0x014a }
            goto L_0x014e
        L_0x014a:
            r13 = move-exception
            com.baidubce.util.BLog.error((java.lang.String) r1, (java.lang.Throwable) r13)
        L_0x014e:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.services.bos.BosClient.uploadObject(com.baidubce.services.bos.model.PutObjectRequest, com.baidubce.internal.InternalRequest):com.baidubce.services.bos.model.BosResponse");
    }

    public CopyObjectResponse copyObject(String str, String str2, String str3, String str4) {
        return copyObject(new CopyObjectRequest(str, str2, str3, str4));
    }

    public CopyObjectResponse copyObject(CopyObjectRequest copyObjectRequest) {
        CheckUtils.isNotNull(copyObjectRequest, "request should not be null.");
        assertStringNotNullOrEmpty(copyObjectRequest.getSourceKey(), "object key should not be null or empty");
        InternalRequest createRequest = createRequest(copyObjectRequest, HttpMethodName.PUT);
        createRequest.addHeader(Headers.BCE_COPY_SOURCE, HttpUtils.normalizePath(BceConfig.BOS_DELIMITER + copyObjectRequest.getSourceBucketName() + BceConfig.BOS_DELIMITER + copyObjectRequest.getSourceKey()));
        if (copyObjectRequest.getETag() != null) {
            createRequest.addHeader(Headers.BCE_COPY_SOURCE_IF_MATCH, "\"" + copyObjectRequest.getETag() + "\"");
        }
        if (copyObjectRequest.getNoneMatchETagConstraint() != null) {
            createRequest.addHeader(Headers.BCE_COPY_SOURCE_IF_NONE_MATCH, "\"" + copyObjectRequest.getNoneMatchETagConstraint() + "\"");
        }
        if (copyObjectRequest.getUnmodifiedSinceConstraint() != null) {
            createRequest.addHeader(Headers.BCE_COPY_SOURCE_IF_UNMODIFIED_SINCE, copyObjectRequest.getUnmodifiedSinceConstraint());
        }
        if (copyObjectRequest.getModifiedSinceConstraint() != null) {
            createRequest.addHeader(Headers.BCE_COPY_SOURCE_IF_MODIFIED_SINCE, copyObjectRequest.getModifiedSinceConstraint());
        }
        if (copyObjectRequest.getStorageClass() != null) {
            createRequest.addHeader(Headers.BCE_STORAGE_CLASS, copyObjectRequest.getStorageClass());
        }
        ObjectMetadata newObjectMetadata = copyObjectRequest.getNewObjectMetadata();
        if (newObjectMetadata != null) {
            createRequest.addHeader(Headers.BCE_COPY_METADATA_DIRECTIVE, "replace");
            populateRequestMetadata(createRequest, newObjectMetadata);
        } else {
            createRequest.addHeader(Headers.BCE_COPY_METADATA_DIRECTIVE, "copy");
        }
        setZeroContentLength(createRequest);
        CopyObjectResponseWithExceptionInfo copyObjectResponseWithExceptionInfo = (CopyObjectResponseWithExceptionInfo) invokeHttpClient(createRequest, CopyObjectResponseWithExceptionInfo.class);
        if (copyObjectResponseWithExceptionInfo.getETag() != null || copyObjectResponseWithExceptionInfo.getLastModified() != null || copyObjectResponseWithExceptionInfo.getMessage() == null) {
            return copyObjectResponseWithExceptionInfo;
        }
        BceServiceException bceServiceException = new BceServiceException(copyObjectResponseWithExceptionInfo.getMessage());
        bceServiceException.setErrorCode(copyObjectResponseWithExceptionInfo.getCode());
        bceServiceException.setRequestId(copyObjectResponseWithExceptionInfo.getRequestId());
        if (bceServiceException.getErrorCode() == "InternalError") {
            bceServiceException.setErrorType(BceServiceException.ErrorType.Service);
        } else {
            bceServiceException.setErrorType(BceServiceException.ErrorType.Client);
        }
        bceServiceException.setStatusCode(StatusCodes.INTERNAL_ERROR);
        throw bceServiceException;
    }

    public void deleteObject(String str, String str2) {
        deleteObject(new DeleteObjectRequest(str, str2));
    }

    public void deleteObject(DeleteObjectRequest deleteObjectRequest) {
        CheckUtils.isNotNull(deleteObjectRequest, "request should not be null.");
        assertStringNotNullOrEmpty(deleteObjectRequest.getKey(), "object key should not be null or empty");
        invokeHttpClient(createRequest(deleteObjectRequest, HttpMethodName.DELETE), BosResponse.class);
    }

    public InitiateMultipartUploadResponse initiateMultipartUpload(String str, String str2) {
        return initiateMultipartUpload(new InitiateMultipartUploadRequest(str, str2));
    }

    public InitiateMultipartUploadResponse initiateMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest) {
        CheckUtils.isNotNull(initiateMultipartUploadRequest, "request should not be null.");
        InternalRequest createRequest = createRequest(initiateMultipartUploadRequest, HttpMethodName.POST);
        createRequest.addParameter("uploads", (String) null);
        if (initiateMultipartUploadRequest.getStorageClass() != null) {
            createRequest.addHeader(Headers.BCE_STORAGE_CLASS, initiateMultipartUploadRequest.getStorageClass());
        }
        setZeroContentLength(createRequest);
        if (initiateMultipartUploadRequest.getObjectMetadata() != null) {
            populateRequestMetadata(createRequest, initiateMultipartUploadRequest.getObjectMetadata());
        }
        return (InitiateMultipartUploadResponse) invokeHttpClient(createRequest, InitiateMultipartUploadResponse.class);
    }

    @Deprecated
    public UploadPartResponse uploadPart(UploadPartRequest uploadPartRequest, BosProgressCallback bosProgressCallback) {
        uploadPartRequest.setProgressCallback(bosProgressCallback);
        return uploadPart(uploadPartRequest);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: com.baidubce.util.MD5DigestCalculatingInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: com.baidubce.util.MD5DigestCalculatingInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: com.baidubce.util.MD5DigestCalculatingInputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.baidubce.services.bos.model.UploadPartResponse uploadPart(com.baidubce.services.bos.model.UploadPartRequest r6) {
        /*
            r5 = this;
            java.lang.String r0 = "request should not be null."
            com.baidubce.util.CheckUtils.isNotNull(r6, r0)
            long r0 = r6.getPartSize()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            java.lang.String r1 = "partSize should not be null"
            com.baidubce.util.CheckUtils.isNotNull(r0, r1)
            int r0 = r6.getPartNumber()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r1 = "partNumber should not be null"
            com.baidubce.util.CheckUtils.isNotNull(r0, r1)
            long r0 = r6.getPartSize()
            r2 = 5368709120(0x140000000, double:2.6524947387E-314)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x010c
            com.baidubce.http.HttpMethodName r0 = com.baidubce.http.HttpMethodName.PUT
            com.baidubce.internal.InternalRequest r0 = r5.createRequest(r6, r0)
            java.lang.String r1 = r6.getUploadId()
            java.lang.String r2 = "uploadId"
            r0.addParameter(r2, r1)
            int r1 = r6.getPartNumber()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "partNumber"
            r0.addParameter(r2, r1)
            long r1 = r6.getPartSize()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Content-Length"
            r0.addHeader(r2, r1)
            java.io.InputStream r1 = r6.getInputStream()
            r2 = 0
            java.lang.String r3 = r6.getMd5Digest()
            if (r3 != 0) goto L_0x006e
            com.baidubce.util.MD5DigestCalculatingInputStream r3 = new com.baidubce.util.MD5DigestCalculatingInputStream     // Catch:{ NoSuchAlgorithmException -> 0x0068 }
            r3.<init>(r1)     // Catch:{ NoSuchAlgorithmException -> 0x0068 }
            r1 = r3
            r2 = r1
            goto L_0x006e
        L_0x0068:
            r3 = move-exception
            java.lang.String r4 = "Unable to verify data integrity."
            com.baidubce.util.BLog.error((java.lang.String) r4, (java.lang.Throwable) r3)
        L_0x006e:
            java.lang.Long r3 = r6.getCrc32()
            if (r3 == 0) goto L_0x0081
            java.lang.Long r3 = r6.getCrc32()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r4 = "x-bce-content-crc32"
            r0.addHeader(r4, r3)
        L_0x0081:
            long r3 = r6.getPartSize()     // Catch:{ all -> 0x0105 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0105 }
            com.baidubce.internal.RestartableInputStream r3 = r5.wrapRestartableInputStream(r1, r3)     // Catch:{ all -> 0x0105 }
            r0.setContent(r3)     // Catch:{ all -> 0x0105 }
            java.lang.Class<com.baidubce.services.bos.model.BosResponse> r3 = com.baidubce.services.bos.model.BosResponse.class
            com.baidubce.services.bos.callback.BosProgressCallback r4 = r6.getProgressCallback()     // Catch:{ all -> 0x0105 }
            com.baidubce.model.AbstractBceResponse r0 = r5.invokeHttpClient(r0, r3, r4)     // Catch:{ all -> 0x0105 }
            com.baidubce.services.bos.model.BosResponse r0 = (com.baidubce.services.bos.model.BosResponse) r0     // Catch:{ all -> 0x0105 }
            if (r2 == 0) goto L_0x00dd
            byte[] r2 = r2.getMd5Digest()     // Catch:{ all -> 0x0105 }
            com.baidubce.services.bos.model.BosResponseMetadata r3 = r0.getMetadata()     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r3 = r3.getETag()     // Catch:{ Exception -> 0x00c1 }
            char[] r3 = r3.toCharArray()     // Catch:{ Exception -> 0x00c1 }
            byte[] r3 = com.baidubce.util.ConvertUtils.decodeHex(r3)     // Catch:{ Exception -> 0x00c1 }
            boolean r2 = java.util.Arrays.equals(r2, r3)     // Catch:{ all -> 0x0105 }
            if (r2 == 0) goto L_0x00b9
            goto L_0x00dd
        L_0x00b9:
            com.baidubce.BceClientException r6 = new com.baidubce.BceClientException     // Catch:{ all -> 0x0105 }
            java.lang.String r0 = "Unable to verify integrity of data upload.  Client calculated content hash didn't match hash calculated by Baidu BOS.  You may need to delete the data stored in Baidu BOS."
            r6.<init>(r0)     // Catch:{ all -> 0x0105 }
            throw r6     // Catch:{ all -> 0x0105 }
        L_0x00c1:
            r6 = move-exception
            com.baidubce.BceClientException r0 = new com.baidubce.BceClientException     // Catch:{ all -> 0x0105 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0105 }
            r2.<init>()     // Catch:{ all -> 0x0105 }
            java.lang.String r3 = "Unable to verify integrity of data upload:"
            r2.append(r3)     // Catch:{ all -> 0x0105 }
            java.lang.String r3 = r6.getMessage()     // Catch:{ all -> 0x0105 }
            r2.append(r3)     // Catch:{ all -> 0x0105 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0105 }
            r0.<init>(r2, r6)     // Catch:{ all -> 0x0105 }
            throw r0     // Catch:{ all -> 0x0105 }
        L_0x00dd:
            com.baidubce.services.bos.model.UploadPartResponse r2 = new com.baidubce.services.bos.model.UploadPartResponse     // Catch:{ all -> 0x0105 }
            r2.<init>()     // Catch:{ all -> 0x0105 }
            com.baidubce.services.bos.model.BosResponseMetadata r3 = r0.getMetadata()     // Catch:{ all -> 0x0105 }
            java.lang.String r3 = r3.getETag()     // Catch:{ all -> 0x0105 }
            r2.setETag(r3)     // Catch:{ all -> 0x0105 }
            com.baidubce.services.bos.model.BosResponseMetadata r0 = r0.getMetadata()     // Catch:{ all -> 0x0105 }
            java.lang.Long r0 = r0.getCrc32()     // Catch:{ all -> 0x0105 }
            r2.setCrc32(r0)     // Catch:{ all -> 0x0105 }
            int r6 = r6.getPartNumber()     // Catch:{ all -> 0x0105 }
            r2.setPartNumber(r6)     // Catch:{ all -> 0x0105 }
            if (r1 == 0) goto L_0x0104
            r1.close()     // Catch:{ Exception -> 0x0104 }
        L_0x0104:
            return r2
        L_0x0105:
            r6 = move-exception
            if (r1 == 0) goto L_0x010b
            r1.close()     // Catch:{ Exception -> 0x010b }
        L_0x010b:
            throw r6
        L_0x010c:
            com.baidubce.BceClientException r0 = new com.baidubce.BceClientException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "PartNumber "
            r1.append(r2)
            int r6 = r6.getPartNumber()
            r1.append(r6)
            java.lang.String r6 = " : Part Size should not be more than 5GB."
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r0.<init>(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.services.bos.BosClient.uploadPart(com.baidubce.services.bos.model.UploadPartRequest):com.baidubce.services.bos.model.UploadPartResponse");
    }

    public ListPartsResponse listParts(String str, String str2, String str3) {
        return listParts(new ListPartsRequest(str, str2, str3));
    }

    public ListPartsResponse listParts(ListPartsRequest listPartsRequest) {
        CheckUtils.isNotNull(listPartsRequest, "request should not be null.");
        InternalRequest createRequest = createRequest(listPartsRequest, HttpMethodName.GET);
        createRequest.addParameter("uploadId", listPartsRequest.getUploadId());
        int maxParts = listPartsRequest.getMaxParts();
        if (maxParts >= 0) {
            createRequest.addParameter("maxParts", String.valueOf(maxParts));
        }
        createRequest.addParameter("partNumberMarker", String.valueOf(listPartsRequest.getPartNumberMarker()));
        ListPartsResponse listPartsResponse = (ListPartsResponse) invokeHttpClient(createRequest, ListPartsResponse.class);
        listPartsResponse.setBucketName(listPartsRequest.getBucketName());
        return listPartsResponse;
    }

    public CompleteMultipartUploadResponse completeMultipartUpload(String str, String str2, String str3, List<PartETag> list) throws JSONException {
        return completeMultipartUpload(new CompleteMultipartUploadRequest(str, str2, str3, list));
    }

    public CompleteMultipartUploadResponse completeMultipartUpload(String str, String str2, String str3, List<PartETag> list, ObjectMetadata objectMetadata) throws JSONException {
        return completeMultipartUpload(new CompleteMultipartUploadRequest(str, str2, str3, list, objectMetadata));
    }

    public CompleteMultipartUploadResponse completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest) throws JSONException {
        CheckUtils.isNotNull(completeMultipartUploadRequest, "request should not be null.");
        InternalRequest createRequest = createRequest(completeMultipartUploadRequest, HttpMethodName.POST);
        createRequest.addParameter("uploadId", completeMultipartUploadRequest.getUploadId());
        ObjectMetadata objectMetadata = completeMultipartUploadRequest.getObjectMetadata();
        if (objectMetadata != null) {
            populateRequestMetadata(createRequest, objectMetadata);
        }
        try {
            byte[] bytes = JsonUtils.setPartETag(completeMultipartUploadRequest.getPartETags()).getBytes("UTF-8");
            createRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(bytes.length));
            createRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            if (completeMultipartUploadRequest.getProcess() != null) {
                createRequest.addHeader(Headers.BCE_PROCESS, completeMultipartUploadRequest.getProcess());
            }
            createRequest.setContent(RestartableInputStream.wrap(bytes));
            CompleteMultipartUploadResponse completeMultipartUploadResponse = (CompleteMultipartUploadResponse) invokeHttpClient(createRequest, CompleteMultipartUploadResponse.class);
            completeMultipartUploadResponse.setBucketName(completeMultipartUploadRequest.getBucketName());
            completeMultipartUploadResponse.setCrc32(completeMultipartUploadResponse.getMetadata().getCrc32());
            return completeMultipartUploadResponse;
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes:" + e.getMessage(), e);
        }
    }

    public void abortMultipartUpload(String str, String str2, String str3) {
        abortMultipartUpload(new AbortMultipartUploadRequest(str, str2, str3));
    }

    public void abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest) {
        CheckUtils.isNotNull(abortMultipartUploadRequest, "request should not be null.");
        InternalRequest createRequest = createRequest(abortMultipartUploadRequest, HttpMethodName.DELETE);
        createRequest.addParameter("uploadId", abortMultipartUploadRequest.getUploadId());
        invokeHttpClient(createRequest, BosResponse.class);
    }

    public ListMultipartUploadsResponse listMultipartUploads(String str) {
        return listMultipartUploads(new ListMultipartUploadsRequest(str));
    }

    public ListMultipartUploadsResponse listMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest) {
        CheckUtils.isNotNull(listMultipartUploadsRequest, "request should not be null.");
        InternalRequest createRequest = createRequest(listMultipartUploadsRequest, HttpMethodName.GET);
        createRequest.addParameter("uploads", (String) null);
        String keyMarker = listMultipartUploadsRequest.getKeyMarker();
        if (keyMarker != null) {
            createRequest.addParameter("keyMarker", keyMarker);
        }
        int maxUploads = listMultipartUploadsRequest.getMaxUploads();
        if (maxUploads >= 0) {
            createRequest.addParameter("maxUploads", String.valueOf(maxUploads));
        }
        String delimiter = listMultipartUploadsRequest.getDelimiter();
        if (delimiter != null) {
            createRequest.addParameter("delimiter", delimiter);
        }
        String prefix = listMultipartUploadsRequest.getPrefix();
        if (prefix != null) {
            createRequest.addParameter("prefix", prefix);
        }
        ListMultipartUploadsResponse listMultipartUploadsResponse = (ListMultipartUploadsResponse) invokeHttpClient(createRequest, ListMultipartUploadsResponse.class);
        listMultipartUploadsResponse.setBucketName(listMultipartUploadsRequest.getBucketName());
        return listMultipartUploadsResponse;
    }

    private static void populateRequestMetadata(InternalRequest internalRequest, ObjectMetadata objectMetadata) {
        if (objectMetadata.getContentType() != null) {
            internalRequest.addHeader(Headers.CONTENT_TYPE, objectMetadata.getContentType());
        }
        if (objectMetadata.getContentMd5() != null) {
            internalRequest.addHeader(Headers.CONTENT_MD5, objectMetadata.getContentMd5());
        }
        if (objectMetadata.getContentEncoding() != null) {
            internalRequest.addHeader(Headers.CONTENT_ENCODING, HttpUtils.normalize(objectMetadata.getContentEncoding()));
        }
        if (objectMetadata.getBceContentSha256() != null) {
            internalRequest.addHeader(Headers.BCE_CONTENT_SHA256, objectMetadata.getBceContentSha256());
        }
        if (objectMetadata.getContentDisposition() != null) {
            internalRequest.addHeader(Headers.CONTENT_DISPOSITION, HttpUtils.normalize(objectMetadata.getContentDisposition()));
        }
        if (objectMetadata.getETag() != null) {
            internalRequest.addHeader(Headers.ETAG, objectMetadata.getETag());
        }
        if (objectMetadata.getExpires() != null) {
            internalRequest.addHeader(Headers.EXPIRES, objectMetadata.getExpires());
        }
        if (objectMetadata.getCacheControl() != null) {
            internalRequest.addHeader(Headers.CACHE_CONTROL, objectMetadata.getCacheControl());
        }
        if (objectMetadata.getStorageClass() != null) {
            internalRequest.addHeader(Headers.BCE_STORAGE_CLASS, objectMetadata.getStorageClass());
        }
        if (objectMetadata.getCrc32() != null) {
            internalRequest.addHeader(Headers.BCE_CRC32, String.valueOf(objectMetadata.getCrc32()));
        }
        Map<String, String> userMetadata = objectMetadata.getUserMetadata();
        if (userMetadata != null) {
            for (Map.Entry next : userMetadata.entrySet()) {
                String str = (String) next.getKey();
                if (str != null) {
                    String str2 = (String) next.getValue();
                    if (str2 == null) {
                        str2 = "";
                    }
                    if (str.length() + str2.length() <= 32768) {
                        internalRequest.addHeader(Headers.BCE_USER_METADATA_PREFIX + HttpUtils.normalize(str.trim()), HttpUtils.normalize(str2));
                    } else {
                        throw new BceClientException("MetadataTooLarge");
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0008, code lost:
        r1 = ((com.baidubce.services.bos.BosClientConfiguration) r4.config).isCnameEnabled();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T extends com.baidubce.model.AbstractBceRequest> java.net.URI getUri(T r5, java.net.URI r6) {
        /*
            r4 = this;
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.baidubce.services.bos.model.GenericBucketRequest
            if (r1 == 0) goto L_0x0028
            com.baidubce.BceClientConfiguration r1 = r4.config
            com.baidubce.services.bos.BosClientConfiguration r1 = (com.baidubce.services.bos.BosClientConfiguration) r1
            java.lang.Boolean r1 = r1.isCnameEnabled()
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            if (r1 == r2) goto L_0x0020
            if (r1 != 0) goto L_0x0028
            java.lang.String r1 = r6.getHost()
            boolean r1 = com.baidubce.services.bos.common.utils.BosUtils.isCnameLikeHost(r1)
            if (r1 != 0) goto L_0x0028
        L_0x0020:
            r1 = r5
            com.baidubce.services.bos.model.GenericBucketRequest r1 = (com.baidubce.services.bos.model.GenericBucketRequest) r1
            java.lang.String r1 = r1.getBucketName()
            goto L_0x0029
        L_0x0028:
            r1 = r0
        L_0x0029:
            boolean r2 = r5 instanceof com.baidubce.services.bos.model.GenericObjectRequest
            if (r2 == 0) goto L_0x0033
            com.baidubce.services.bos.model.GenericObjectRequest r5 = (com.baidubce.services.bos.model.GenericObjectRequest) r5
            java.lang.String r0 = r5.getKey()
        L_0x0033:
            r5 = 3
            java.lang.String[] r5 = new java.lang.String[r5]
            r2 = 0
            java.lang.String r3 = "v1"
            r5[r2] = r3
            r2 = 1
            r5[r2] = r1
            r1 = 2
            r5[r1] = r0
            java.net.URI r5 = com.baidubce.util.HttpUtils.appendUri(r6, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.services.bos.BosClient.getUri(com.baidubce.model.AbstractBceRequest, java.net.URI):java.net.URI");
    }

    private <T extends AbstractBceRequest> InternalRequest createRequest(T t, HttpMethodName httpMethodName) {
        URI endpoint = getEndpoint();
        URI uri = null;
        if ((this.config instanceof BosClientConfiguration) && ((BosClientConfiguration) this.config).getBackupEndpoint() != null) {
            try {
                uri = new URI(((BosClientConfiguration) this.config).getBackupEndpoint());
            } catch (URISyntaxException unused) {
            }
        }
        InternalRequest internalRequest = new InternalRequest(httpMethodName, getUri(t, endpoint), getUri(t, uri));
        internalRequest.setCredentials(t.getRequestCredentials());
        internalRequest.setRequest(t);
        return internalRequest;
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r3v3, types: [java.io.OutputStream, java.io.BufferedOutputStream] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x009e A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00fc A[SYNTHETIC, Splitter:B:62:0x00fc] */
    /* JADX WARNING: Removed duplicated region for block: B:72:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void downloadObjectToFile(com.baidubce.services.bos.model.BosObject r9, java.io.File r10, boolean r11) {
        /*
            r8 = this;
            java.lang.String r0 = "Couldn't get object content"
            java.lang.String r1 = "Couldn't close the output stream"
            java.io.File r2 = r10.getParentFile()
            if (r2 == 0) goto L_0x0013
            boolean r3 = r2.exists()
            if (r3 != 0) goto L_0x0013
            r2.mkdirs()
        L_0x0013:
            r2 = 0
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x00d0 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00d0 }
            r4.<init>(r10)     // Catch:{ IOException -> 0x00d0 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x00d0 }
            int r4 = r8.getStreamBufferSize()     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            byte[] r4 = new byte[r4]     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
        L_0x0024:
            com.baidubce.services.bos.BosObjectInputStream r5 = r9.getObjectContent()     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            int r5 = r5.read(r4)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r6 = -1
            r7 = 0
            if (r5 <= r6) goto L_0x0034
            r3.write(r4, r7, r5)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            goto L_0x0024
        L_0x0034:
            r3.close()     // Catch:{ Exception -> 0x0038 }
            goto L_0x0040
        L_0x0038:
            r3 = move-exception
            java.lang.Throwable r3 = r3.getCause()
            com.baidubce.util.BLog.error((java.lang.String) r1, (java.lang.Throwable) r3)
        L_0x0040:
            com.baidubce.services.bos.BosObjectInputStream r1 = r9.getObjectContent()     // Catch:{ Exception -> 0x0048 }
            r1.close()     // Catch:{ Exception -> 0x0048 }
            goto L_0x0050
        L_0x0048:
            r1 = move-exception
            java.lang.Throwable r1 = r1.getCause()
            com.baidubce.util.BLog.error((java.lang.String) r0, (java.lang.Throwable) r1)
        L_0x0050:
            if (r11 == 0) goto L_0x00c7
            com.baidubce.services.bos.model.ObjectMetadata r9 = r9.getObjectMetadata()
            java.lang.String r11 = r9.getBceContentSha256()     // Catch:{ Exception -> 0x0095 }
            if (r11 == 0) goto L_0x0075
            java.lang.String r9 = r9.getBceContentSha256()     // Catch:{ Exception -> 0x0095 }
            char[] r9 = r9.toCharArray()     // Catch:{ Exception -> 0x0095 }
            byte[] r9 = com.baidubce.util.ConvertUtils.decodeHex(r9)     // Catch:{ Exception -> 0x0095 }
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0073 }
            r11.<init>(r10)     // Catch:{ Exception -> 0x0073 }
            byte[] r11 = com.baidubce.util.HashUtils.computeSha256Hash(r11)     // Catch:{ Exception -> 0x0073 }
        L_0x0071:
            r2 = r11
            goto L_0x009c
        L_0x0073:
            r11 = move-exception
            goto L_0x0097
        L_0x0075:
            java.lang.String r11 = r9.getContentMd5()     // Catch:{ Exception -> 0x0095 }
            if (r11 == 0) goto L_0x0093
            java.lang.String r9 = r9.getContentMd5()     // Catch:{ Exception -> 0x0095 }
            java.lang.String r11 = "UTF-8"
            byte[] r9 = r9.getBytes(r11)     // Catch:{ Exception -> 0x0095 }
            byte[] r9 = android.util.Base64.decode(r9, r7)     // Catch:{ Exception -> 0x0095 }
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0073 }
            r11.<init>(r10)     // Catch:{ Exception -> 0x0073 }
            byte[] r11 = com.baidubce.util.HashUtils.computeMd5Hash(r11)     // Catch:{ Exception -> 0x0073 }
            goto L_0x0071
        L_0x0093:
            r9 = r2
            goto L_0x009c
        L_0x0095:
            r11 = move-exception
            r9 = r2
        L_0x0097:
            java.lang.String r0 = "Unable to verify the integrity of the downloaded file"
            com.baidubce.util.BLog.error((java.lang.String) r0, (java.lang.Throwable) r11)
        L_0x009c:
            if (r9 == 0) goto L_0x00c7
            if (r2 == 0) goto L_0x00c7
            boolean r9 = java.util.Arrays.equals(r2, r9)
            if (r9 == 0) goto L_0x00a7
            goto L_0x00c7
        L_0x00a7:
            com.baidubce.BceClientException r9 = new com.baidubce.BceClientException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "Integrity verification failed! Client calculated content hash didn't match hash from server. The data stored in '"
            r11.append(r0)
            java.lang.String r10 = r10.getAbsolutePath()
            r11.append(r10)
            java.lang.String r10 = "' may be corrupt."
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            r9.<init>(r10)
            throw r9
        L_0x00c7:
            return
        L_0x00c8:
            r10 = move-exception
            r2 = r3
            goto L_0x00fa
        L_0x00cb:
            r10 = move-exception
            r2 = r3
            goto L_0x00d1
        L_0x00ce:
            r10 = move-exception
            goto L_0x00fa
        L_0x00d0:
            r10 = move-exception
        L_0x00d1:
            com.baidubce.services.bos.BosObjectInputStream r11 = r9.getObjectContent()     // Catch:{ IOException -> 0x00d9 }
            r11.close()     // Catch:{ IOException -> 0x00d9 }
            goto L_0x00df
        L_0x00d9:
            r11 = move-exception
            java.lang.String r3 = "Couldn't abort stream"
            com.baidubce.util.BLog.error((java.lang.String) r3, (java.lang.Throwable) r11)     // Catch:{ all -> 0x00ce }
        L_0x00df:
            com.baidubce.BceClientException r11 = new com.baidubce.BceClientException     // Catch:{ all -> 0x00ce }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ce }
            r3.<init>()     // Catch:{ all -> 0x00ce }
            java.lang.String r4 = "Unable to write to disk:"
            r3.append(r4)     // Catch:{ all -> 0x00ce }
            java.lang.String r4 = r10.getMessage()     // Catch:{ all -> 0x00ce }
            r3.append(r4)     // Catch:{ all -> 0x00ce }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00ce }
            r11.<init>(r3, r10)     // Catch:{ all -> 0x00ce }
            throw r11     // Catch:{ all -> 0x00ce }
        L_0x00fa:
            if (r2 == 0) goto L_0x0108
            r2.close()     // Catch:{ Exception -> 0x0100 }
            goto L_0x0108
        L_0x0100:
            r11 = move-exception
            java.lang.Throwable r11 = r11.getCause()
            com.baidubce.util.BLog.error((java.lang.String) r1, (java.lang.Throwable) r11)
        L_0x0108:
            com.baidubce.services.bos.BosObjectInputStream r9 = r9.getObjectContent()     // Catch:{ Exception -> 0x0110 }
            r9.close()     // Catch:{ Exception -> 0x0110 }
            goto L_0x0118
        L_0x0110:
            r9 = move-exception
            java.lang.Throwable r9 = r9.getCause()
            com.baidubce.util.BLog.error((java.lang.String) r0, (java.lang.Throwable) r9)
        L_0x0118:
            goto L_0x011a
        L_0x0119:
            throw r10
        L_0x011a:
            goto L_0x0119
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.services.bos.BosClient.downloadObjectToFile(com.baidubce.services.bos.model.BosObject, java.io.File, boolean):void");
    }

    private List<byte[]> readAll(InputStream inputStream, ObjectMetadata objectMetadata) {
        ArrayList arrayList = new ArrayList();
        int streamBufferSize = getStreamBufferSize();
        long j = 0;
        while (true) {
            byte[] bArr = new byte[streamBufferSize];
            arrayList.add(bArr);
            int i = 0;
            while (true) {
                if (i < streamBufferSize) {
                    try {
                        int read = inputStream.read(bArr, i, streamBufferSize - i);
                        if (read < 0) {
                            objectMetadata.setContentLength(j);
                            return arrayList;
                        }
                        j += (long) read;
                        i += read;
                    } catch (IOException e) {
                        throw new BceClientException("Fail to read data:" + e.getMessage(), e);
                    }
                }
            }
        }
    }

    private RestartableInputStream wrapRestartableInputStream(InputStream inputStream) {
        if (inputStream.markSupported()) {
            return new RestartableResettableInputStream(inputStream);
        }
        return new RestartableNonResettableInputStream(inputStream, getStreamBufferSize());
    }

    private RestartableInputStream wrapRestartableInputStream(InputStream inputStream, Long l) {
        if (inputStream.markSupported()) {
            return new RestartableResettableInputStream(inputStream);
        }
        return new RestartableNonResettableInputStream(inputStream, l.longValue() > ((long) getStreamBufferSize()) ? getStreamBufferSize() : l.intValue());
    }

    private void setZeroContentLength(InternalRequest internalRequest) {
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(0));
    }

    private int getStreamBufferSize() {
        return ((BosClientConfiguration) this.config).getStreamBufferSize();
    }

    private void assertStringNotNullOrEmpty(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException(str2);
        } else if (str.isEmpty()) {
            throw new IllegalArgumentException(str2);
        }
    }

    private void addResponseHeaderParameters(InternalRequest internalRequest, ResponseHeaderOverrides responseHeaderOverrides) {
        if (responseHeaderOverrides != null) {
            if (responseHeaderOverrides.getCacheControl() != null) {
                internalRequest.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CACHE_CONTROL, responseHeaderOverrides.getCacheControl());
            }
            if (responseHeaderOverrides.getContentDisposition() != null) {
                internalRequest.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_DISPOSITION, responseHeaderOverrides.getContentDisposition());
            }
            if (responseHeaderOverrides.getContentEncoding() != null) {
                internalRequest.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_ENCODING, responseHeaderOverrides.getContentEncoding());
            }
            if (responseHeaderOverrides.getContentLanguage() != null) {
                internalRequest.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_LANGUAGE, responseHeaderOverrides.getContentLanguage());
            }
            if (responseHeaderOverrides.getContentType() != null) {
                internalRequest.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_TYPE, responseHeaderOverrides.getContentType());
            }
            if (responseHeaderOverrides.getExpires() != null) {
                internalRequest.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_EXPIRES, responseHeaderOverrides.getExpires());
            }
        }
    }

    private URL convertRequestToUrl(InternalRequest<AbstractBceRequest> internalRequest) {
        String str;
        String str2;
        String normalizePath = HttpUtils.normalizePath(internalRequest.getUri().getPath());
        boolean z = true;
        if (normalizePath.startsWith(BceConfig.BOS_DELIMITER)) {
            normalizePath = normalizePath.substring(1);
        }
        String str3 = getEndpoint() + (BceConfig.BOS_DELIMITER + normalizePath).replaceAll("(?<=/)/", "%2F");
        for (String next : internalRequest.getParameters().keySet()) {
            if (z) {
                str2 = str3 + "?";
                z = false;
            } else {
                str2 = str3 + "&";
            }
            str3 = str2 + next + "=" + HttpUtils.normalize(internalRequest.getParameters().get(next));
        }
        String str4 = internalRequest.getHeaders().get(Headers.AUTHORIZATION);
        if (str4 != null) {
            if (z) {
                str = str3 + "?";
            } else {
                str = str3 + "&";
            }
            str3 = str + "authorization=" + HttpUtils.normalize(str4);
        }
        try {
            return new URL(str3);
        } catch (MalformedURLException e) {
            throw new BceClientException("Unable to convert request to well formed URL: " + e.getMessage(), e);
        }
    }

    public PutSuperObjectResponse putSuperObjectFromFile(File file, String str, String str2, long j, int i) {
        return putSuperObjectFromFile(new PutSuperObjectRequest(str, str2, file, j, i));
    }

    public PutSuperObjectResponse putSuperObjectFromFile(File file, String str, String str2) {
        return putSuperObjectFromFile(new PutSuperObjectRequest(str, str2, file));
    }

    public PutSuperObjectResponse putSuperObjectFromFile(File file, String str, String str2, long j) {
        return putSuperObjectFromFile(new PutSuperObjectRequest(str, str2, file, j));
    }

    public PutSuperObjectResponse putSuperObjectFromFile(File file, String str, String str2, int i) {
        return putSuperObjectFromFile(new PutSuperObjectRequest(str, str2, file, i));
    }

    public PutSuperObjectResponse putSuperObjectFromFile(PutSuperObjectRequest putSuperObjectRequest) {
        boolean z;
        String str;
        final PutSuperObjectRequest putSuperObjectRequest2 = putSuperObjectRequest;
        PutSuperObjectResponse putSuperObjectResponse = new PutSuperObjectResponse();
        File file = putSuperObjectRequest.getFile();
        long partSize = putSuperObjectRequest.getPartSize();
        if (partSize > 0) {
            String bucketName = putSuperObjectRequest.getBucketName();
            String key = putSuperObjectRequest.getKey();
            putSuperObjectResponse.setBucketName(bucketName);
            putSuperObjectResponse.setKey(key);
            int i = putSuperObjectRequest.getnThreads();
            AtomicBoolean isSuperObjectUploadCancelled = putSuperObjectRequest.getIsSuperObjectUploadCancelled();
            long length = file.length();
            int i2 = (int) (length / partSize);
            if (length % partSize > 0) {
                i2++;
            }
            if (((long) i2) > BceClientConfiguration.MAX_PARTS) {
                throw new BceClientException("Total parts count should not exceed 10000");
            } else if (length <= this.config.getMultipartBlockSize()) {
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
                if (putSuperObjectRequest.getProgressCallback() != null) {
                    putObjectRequest.setProgressCallback(new BosProgressCallback<PutObjectRequest>() {
                        public void onProgress(PutObjectRequest putObjectRequest, long j, long j2) {
                            if (putSuperObjectRequest2.getIsSuperObjectUploadCancelled().get()) {
                                putObjectRequest.cancel();
                                putSuperObjectRequest2.getProgressCallback().onProgress(putSuperObjectRequest2, 0, j2);
                            }
                            putSuperObjectRequest2.getProgressCallback().onProgress(putSuperObjectRequest2, j, j2);
                        }
                    });
                }
                PutObjectResponse putObject = putObject(putObjectRequest);
                putSuperObjectResponse.setIsUploadPart(false);
                putSuperObjectResponse.setServerCallbackReturnBody(putObject.getServerCallbackReturnBody());
                putSuperObjectResponse.setCrc32(putObject.getCrc32());
                putSuperObjectResponse.setETag(putObject.getETag());
                putSuperObjectResponse.setHttpResponse(putObject.getHttpResponse());
                return putSuperObjectResponse;
            } else {
                putSuperObjectRequest2.initSuperFileTask(length, i2);
                String uploadId = initiateMultipartUpload(bucketName, key).getUploadId();
                putSuperObjectRequest2.setUploadId(uploadId);
                ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(i);
                ArrayList arrayList = new ArrayList();
                List synchronizedList = Collections.synchronizedList(new ArrayList());
                for (int i3 = 0; i3 < i2; i3++) {
                    arrayList.add(newFixedThreadPool.submit(new UploadPartTask(this, putSuperObjectRequest2, i3, synchronizedList)));
                }
                int i4 = 0;
                while (i4 < arrayList.size()) {
                    try {
                        ArrayList arrayList2 = arrayList;
                        if (!((Boolean) ((Future) arrayList.get(i4)).get()).booleanValue()) {
                            BLog.error("The upload task [ " + i4 + "] failed.");
                            z = false;
                            break;
                        }
                        BLog.info("The upload task [ " + i4 + "] completed.");
                        i4++;
                        arrayList = arrayList2;
                    } catch (InterruptedException | ExecutionException unused) {
                    }
                }
                z = true;
                newFixedThreadPool.shutdown();
                boolean z2 = z;
                try {
                    if (!newFixedThreadPool.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                        newFixedThreadPool.shutdownNow();
                    }
                    if (isSuperObjectUploadCancelled.get() || synchronizedList.size() != i2) {
                        z2 = false;
                    }
                    if (z2) {
                        Collections.sort(synchronizedList, new Comparator<PartETag>() {
                            public int compare(PartETag partETag, PartETag partETag2) {
                                return partETag.getPartNumber() - partETag2.getPartNumber();
                            }
                        });
                        try {
                            CompleteMultipartUploadResponse completeMultipartUpload = completeMultipartUpload(new CompleteMultipartUploadRequest(bucketName, key, uploadId, synchronizedList));
                            putSuperObjectResponse.setHttpResponse(completeMultipartUpload.getHttpResponse());
                            putSuperObjectResponse.setETag(completeMultipartUpload.getETag());
                            putSuperObjectResponse.setCrc32(completeMultipartUpload.getCrc32());
                            putSuperObjectResponse.setServerCallbackReturnBody(completeMultipartUpload.getServerCallbackReturnBody());
                            putSuperObjectResponse.setLocation(completeMultipartUpload.getLocation());
                            if (putSuperObjectRequest.getProgressCallback() == null || putSuperObjectRequest.getCurrentSize() == length) {
                                String str2 = uploadId;
                            } else {
                                str = uploadId;
                                try {
                                    putSuperObjectRequest.getProgressCallback().onProgress(putSuperObjectRequest, length, length);
                                } catch (JSONException unused2) {
                                    BLog.error("Failed to completeMultipartUpload: [upload] = " + str);
                                    return putSuperObjectResponse;
                                }
                            }
                            BLog.info("Success to upload file: " + file.getAbsolutePath() + " to BOS with ETag: " + completeMultipartUpload.getETag());
                        } catch (JSONException unused3) {
                            str = uploadId;
                            BLog.error("Failed to completeMultipartUpload: [upload] = " + str);
                            return putSuperObjectResponse;
                        }
                    } else {
                        abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, key, uploadId));
                        putSuperObjectRequest2.setCurrentSize(0);
                        if (putSuperObjectRequest.getProgressCallback() != null) {
                            putSuperObjectRequest.getProgressCallback().onProgress(putSuperObjectRequest, 0, length);
                        }
                        BLog.info("Failed to upload file: " + file.getAbsolutePath());
                    }
                    return putSuperObjectResponse;
                } catch (InterruptedException e) {
                    throw new BceClientException("close thread pool fail exception", e);
                }
            }
        } else {
            throw new BceClientException("the partsize must be greater than 0");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00af, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e0, code lost:
        r0.addCurrentSize(0 - (r0.getCurrentSize(r3) - r16));
        r0.setCurrentSize(r3, r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f5, code lost:
        monitor-enter(r21.getProgressCallback());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r21.getProgressCallback().onProgress(r21, r21.getCurrentSize(), r21.getTotalSize());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00af A[ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e0 A[Catch:{ all -> 0x0117 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x010e A[SYNTHETIC, Splitter:B:52:0x010e] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x011b A[SYNTHETIC, Splitter:B:59:0x011b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean uploadFilePart(com.baidubce.services.bos.model.PutSuperObjectRequest r21, int r22, java.util.List<com.baidubce.services.bos.model.PartETag> r23) {
        /*
            r20 = this;
            r1 = r20
            r0 = r21
            r8 = r22
            com.baidubce.BceClientConfiguration r2 = r1.config
            int r2 = r2.getUploadRetry()
            java.io.File r9 = r21.getFile()
            long r10 = r21.getPartSize()
            java.lang.String r12 = r21.getBucketName()
            java.lang.String r13 = r21.getKey()
            java.lang.String r14 = r21.getUploadId()
            java.util.concurrent.atomic.AtomicBoolean r15 = r21.getIsSuperObjectUploadCancelled()
        L_0x0024:
            if (r2 <= 0) goto L_0x011f
            boolean r3 = r15.get()
            if (r3 == 0) goto L_0x002e
            goto L_0x011f
        L_0x002e:
            int r3 = r8 + 1
            long r4 = r0.getCurrentSize(r3)
            r6 = 0
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
            r7.<init>(r9)     // Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
            r16 = r4
            long r4 = (long) r8
            long r4 = r4 * r10
            r7.skip(r4)     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            long r18 = r9.length()     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            long r4 = r18 - r4
            long r4 = java.lang.Math.min(r10, r4)     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            com.baidubce.services.bos.model.UploadPartRequest r6 = new com.baidubce.services.bos.model.UploadPartRequest     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            r6.<init>()     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            r6.setBucketName(r12)     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            r6.setKey(r13)     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            r6.setUploadId(r14)     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            r6.setInputStream(r7)     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            r6.setPartSize(r4)     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            r6.setPartNumber(r3)     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            com.baidubce.services.bos.callback.BosProgressCallback r4 = r21.getProgressCallback()     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            if (r4 == 0) goto L_0x0071
            com.baidubce.services.bos.BosClient$3 r4 = new com.baidubce.services.bos.BosClient$3     // Catch:{ IOException -> 0x00b4, all -> 0x00af }
            r4.<init>(r0)     // Catch:{ IOException -> 0x00b4, all -> 0x00af }
            r6.setProgressCallback(r4)     // Catch:{ IOException -> 0x00b4, all -> 0x00af }
        L_0x0071:
            boolean r4 = r15.get()     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            if (r4 == 0) goto L_0x007f
            r7.close()     // Catch:{ Exception -> 0x007c }
            goto L_0x011f
        L_0x007c:
            goto L_0x011f
        L_0x007f:
            com.baidubce.services.bos.model.UploadPartResponse r4 = r1.uploadPart(r6)     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            com.baidubce.services.bos.model.PartETag r5 = r4.getPartETag()     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            r6 = r23
            r6.add(r5)     // Catch:{ IOException -> 0x00b4, all -> 0x00af }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00b4, all -> 0x00af }
            r5.<init>()     // Catch:{ IOException -> 0x00b4, all -> 0x00af }
            java.lang.String r1 = "Complete upload with ETag: "
            r5.append(r1)     // Catch:{ IOException -> 0x00b4, all -> 0x00af }
            com.baidubce.services.bos.model.PartETag r1 = r4.getPartETag()     // Catch:{ IOException -> 0x00b4, all -> 0x00af }
            r5.append(r1)     // Catch:{ IOException -> 0x00b4, all -> 0x00af }
            java.lang.String r1 = r5.toString()     // Catch:{ IOException -> 0x00b4, all -> 0x00af }
            com.baidubce.util.BLog.info(r1)     // Catch:{ IOException -> 0x00b4, all -> 0x00af }
            r7.close()     // Catch:{ Exception -> 0x00a7 }
        L_0x00a7:
            if (r2 <= 0) goto L_0x00ab
            goto L_0x011f
        L_0x00ab:
            r1 = r20
            goto L_0x0024
        L_0x00af:
            r0 = move-exception
            goto L_0x0119
        L_0x00b2:
            r6 = r23
        L_0x00b4:
            r1 = r7
            goto L_0x00bc
        L_0x00b6:
            r0 = move-exception
            r7 = r6
            goto L_0x0119
        L_0x00b9:
            r16 = r4
            r1 = r6
        L_0x00bc:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0117 }
            r4.<init>()     // Catch:{ all -> 0x0117 }
            java.lang.String r5 = "Failed to upload the part "
            r4.append(r5)     // Catch:{ all -> 0x0117 }
            r4.append(r8)     // Catch:{ all -> 0x0117 }
            java.lang.String r5 = " [tryCount] = "
            r4.append(r5)     // Catch:{ all -> 0x0117 }
            r4.append(r2)     // Catch:{ all -> 0x0117 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0117 }
            com.baidubce.util.BLog.error(r4)     // Catch:{ all -> 0x0117 }
            int r18 = r2 + -1
            com.baidubce.services.bos.callback.BosProgressCallback r2 = r21.getProgressCallback()     // Catch:{ all -> 0x0117 }
            if (r2 == 0) goto L_0x010c
            long r4 = r0.getCurrentSize(r3)     // Catch:{ all -> 0x0117 }
            long r4 = r4 - r16
            r6 = 0
            long r6 = r6 - r4
            r0.addCurrentSize(r6)     // Catch:{ all -> 0x0117 }
            r4 = r16
            r0.setCurrentSize(r3, r4)     // Catch:{ all -> 0x0117 }
            com.baidubce.services.bos.callback.BosProgressCallback r16 = r21.getProgressCallback()     // Catch:{ all -> 0x0117 }
            monitor-enter(r16)     // Catch:{ all -> 0x0117 }
            com.baidubce.services.bos.callback.BosProgressCallback r2 = r21.getProgressCallback()     // Catch:{ all -> 0x0109 }
            long r4 = r21.getCurrentSize()     // Catch:{ all -> 0x0109 }
            long r6 = r21.getTotalSize()     // Catch:{ all -> 0x0109 }
            r3 = r21
            r2.onProgress(r3, r4, r6)     // Catch:{ all -> 0x0109 }
            monitor-exit(r16)     // Catch:{ all -> 0x0109 }
            goto L_0x010c
        L_0x0109:
            r0 = move-exception
            monitor-exit(r16)     // Catch:{ all -> 0x0109 }
            throw r0     // Catch:{ all -> 0x0117 }
        L_0x010c:
            if (r1 == 0) goto L_0x0111
            r1.close()     // Catch:{ Exception -> 0x0111 }
        L_0x0111:
            r1 = r20
            r2 = r18
            goto L_0x0024
        L_0x0117:
            r0 = move-exception
            r7 = r1
        L_0x0119:
            if (r7 == 0) goto L_0x011e
            r7.close()     // Catch:{ Exception -> 0x011e }
        L_0x011e:
            throw r0
        L_0x011f:
            boolean r0 = r15.get()
            if (r0 == 0) goto L_0x012b
            java.lang.String r0 = "Request is canceled"
            com.baidubce.util.BLog.info(r0)
            goto L_0x0156
        L_0x012b:
            if (r2 != 0) goto L_0x0142
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Failed to upload the part "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            com.baidubce.util.BLog.error(r0)
            goto L_0x0156
        L_0x0142:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Success to upload the part "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            com.baidubce.util.BLog.info(r0)
        L_0x0156:
            if (r2 <= 0) goto L_0x0160
            boolean r0 = r15.get()
            if (r0 != 0) goto L_0x0160
            r0 = 1
            goto L_0x0161
        L_0x0160:
            r0 = 0
        L_0x0161:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.services.bos.BosClient.uploadFilePart(com.baidubce.services.bos.model.PutSuperObjectRequest, int, java.util.List):boolean");
    }

    private static class UploadPartTask implements Callable<Boolean> {
        BosClient client;
        List<PartETag> partETags;
        int partNum;
        PutSuperObjectRequest putSuperObjectRequest;

        UploadPartTask(BosClient bosClient, PutSuperObjectRequest putSuperObjectRequest2, int i, List<PartETag> list) {
            this.client = bosClient;
            this.putSuperObjectRequest = putSuperObjectRequest2;
            this.partNum = i;
            this.partETags = list;
        }

        public Boolean call() {
            return Boolean.valueOf(this.client.uploadFilePart(this.putSuperObjectRequest, this.partNum, this.partETags));
        }
    }
}
