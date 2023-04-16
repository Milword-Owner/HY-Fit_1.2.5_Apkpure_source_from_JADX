package com.baidubce.auth;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

public final class NTLMEngineImpl {
    /* access modifiers changed from: private */
    public static final Charset DEFAULT_CHARSET = Charset.forName("US-ASCII");
    protected static final int FLAG_DOMAIN_PRESENT = 4096;
    protected static final int FLAG_REQUEST_128BIT_KEY_EXCH = 536870912;
    protected static final int FLAG_REQUEST_56BIT_ENCRYPTION = Integer.MIN_VALUE;
    protected static final int FLAG_REQUEST_ALWAYS_SIGN = 32768;
    protected static final int FLAG_REQUEST_EXPLICIT_KEY_EXCH = 1073741824;
    protected static final int FLAG_REQUEST_LAN_MANAGER_KEY = 128;
    protected static final int FLAG_REQUEST_NTLM2_SESSION = 524288;
    protected static final int FLAG_REQUEST_NTLMv1 = 512;
    protected static final int FLAG_REQUEST_SEAL = 32;
    protected static final int FLAG_REQUEST_SIGN = 16;
    protected static final int FLAG_REQUEST_TARGET = 4;
    protected static final int FLAG_REQUEST_UNICODE_ENCODING = 1;
    protected static final int FLAG_REQUEST_VERSION = 33554432;
    protected static final int FLAG_TARGETINFO_PRESENT = 8388608;
    protected static final int FLAG_WORKSTATION_PRESENT = 8192;
    private static final SecureRandom RND_GEN;
    /* access modifiers changed from: private */
    public static final byte[] SIGNATURE;
    private static final String TYPE_1_MESSAGE = new Type1Message().getResponse();
    /* access modifiers changed from: private */
    public static final Charset UNICODE_LITTLE_UNMARKED = Charset.forName("UnicodeLittleUnmarked");

    /* renamed from: F */
    static int m1830F(int i, int i2, int i3) {
        return ((i ^ -1) & i3) | (i2 & i);
    }

    /* renamed from: G */
    static int m1831G(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    /* renamed from: H */
    static int m1832H(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    static int rotintlft(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public static class NTLMEngineException extends Exception {
        public NTLMEngineException(String str) {
            super(str);
        }

        public NTLMEngineException(String str, Throwable th) {
            super(str, th);
        }
    }

    static {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (Exception unused) {
            secureRandom = null;
        }
        RND_GEN = secureRandom;
        byte[] bytes = "NTLMSSP".getBytes(DEFAULT_CHARSET);
        SIGNATURE = new byte[(bytes.length + 1)];
        System.arraycopy(bytes, 0, SIGNATURE, 0, bytes.length);
        SIGNATURE[bytes.length] = 0;
    }

    static String getResponseFor(String str, String str2, String str3, String str4, String str5) throws NTLMEngineException {
        if (str == null || str.trim().equals("")) {
            return getType1Message(str4, str5);
        }
        Type2Message type2Message = new Type2Message(str);
        return getType3Message(str2, str3, str4, str5, type2Message.getChallenge(), type2Message.getFlags(), type2Message.getTarget(), type2Message.getTargetInfo());
    }

    static String getType1Message(String str, String str2) throws NTLMEngineException {
        return TYPE_1_MESSAGE;
    }

    static String getType3Message(String str, String str2, String str3, String str4, byte[] bArr, int i, String str5, byte[] bArr2) throws NTLMEngineException {
        return new Type3Message(str4, str3, str, str2, bArr, i, str5, bArr2).getResponse();
    }

    private static String stripDotSuffix(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(".");
        return indexOf != -1 ? str.substring(0, indexOf) : str;
    }

    /* access modifiers changed from: private */
    public static String convertHost(String str) {
        return stripDotSuffix(str);
    }

    /* access modifiers changed from: private */
    public static String convertDomain(String str) {
        return stripDotSuffix(str);
    }

    /* access modifiers changed from: private */
    public static int readULong(byte[] bArr, int i) throws NTLMEngineException {
        if (bArr.length >= i + 4) {
            return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
        }
        throw new NTLMEngineException("NTLM authentication - buffer too small for DWORD");
    }

    /* access modifiers changed from: private */
    public static int readUShort(byte[] bArr, int i) throws NTLMEngineException {
        if (bArr.length >= i + 2) {
            return ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | (bArr[i] & UByte.MAX_VALUE);
        }
        throw new NTLMEngineException("NTLM authentication - buffer too small for WORD");
    }

    /* access modifiers changed from: private */
    public static byte[] readSecurityBuffer(byte[] bArr, int i) throws NTLMEngineException {
        int readUShort = readUShort(bArr, i);
        int readULong = readULong(bArr, i + 4);
        if (bArr.length >= readULong + readUShort) {
            byte[] bArr2 = new byte[readUShort];
            System.arraycopy(bArr, readULong, bArr2, 0, readUShort);
            return bArr2;
        }
        throw new NTLMEngineException("NTLM authentication - buffer too small for data item");
    }

    /* access modifiers changed from: private */
    public static byte[] makeRandomChallenge() throws NTLMEngineException {
        SecureRandom secureRandom = RND_GEN;
        if (secureRandom != null) {
            byte[] bArr = new byte[8];
            synchronized (secureRandom) {
                RND_GEN.nextBytes(bArr);
            }
            return bArr;
        }
        throw new NTLMEngineException("Random generator not available");
    }

    /* access modifiers changed from: private */
    public static byte[] makeSecondaryKey() throws NTLMEngineException {
        SecureRandom secureRandom = RND_GEN;
        if (secureRandom != null) {
            byte[] bArr = new byte[16];
            synchronized (secureRandom) {
                RND_GEN.nextBytes(bArr);
            }
            return bArr;
        }
        throw new NTLMEngineException("Random generator not available");
    }

    protected static class CipherGen {
        protected final byte[] challenge;
        protected byte[] clientChallenge;
        protected byte[] clientChallenge2;
        protected final String domain;
        protected byte[] lanManagerSessionKey;
        protected byte[] lm2SessionResponse;
        protected byte[] lmHash;
        protected byte[] lmResponse;
        protected byte[] lmUserSessionKey;
        protected byte[] lmv2Hash;
        protected byte[] lmv2Response;
        protected byte[] ntlm2SessionResponse;
        protected byte[] ntlm2SessionResponseUserSessionKey;
        protected byte[] ntlmHash;
        protected byte[] ntlmResponse;
        protected byte[] ntlmUserSessionKey;
        protected byte[] ntlmv2Blob;
        protected byte[] ntlmv2Hash;
        protected byte[] ntlmv2Response;
        protected byte[] ntlmv2UserSessionKey;
        protected final String password;
        protected byte[] secondaryKey;
        protected final String target;
        protected final byte[] targetInformation;
        protected byte[] timestamp;
        protected final String user;

        public CipherGen(String str, String str2, String str3, byte[] bArr, String str4, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
            this.lmHash = null;
            this.lmResponse = null;
            this.ntlmHash = null;
            this.ntlmResponse = null;
            this.ntlmv2Hash = null;
            this.lmv2Hash = null;
            this.lmv2Response = null;
            this.ntlmv2Blob = null;
            this.ntlmv2Response = null;
            this.ntlm2SessionResponse = null;
            this.lm2SessionResponse = null;
            this.lmUserSessionKey = null;
            this.ntlmUserSessionKey = null;
            this.ntlmv2UserSessionKey = null;
            this.ntlm2SessionResponseUserSessionKey = null;
            this.lanManagerSessionKey = null;
            this.domain = str;
            this.target = str4;
            this.user = str2;
            this.password = str3;
            this.challenge = bArr;
            this.targetInformation = bArr2;
            this.clientChallenge = bArr3;
            this.clientChallenge2 = bArr4;
            this.secondaryKey = bArr5;
            this.timestamp = bArr6;
        }

        public CipherGen(String str, String str2, String str3, byte[] bArr, String str4, byte[] bArr2) {
            this(str, str2, str3, bArr, str4, bArr2, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
        }

        public byte[] getClientChallenge() throws NTLMEngineException {
            if (this.clientChallenge == null) {
                this.clientChallenge = NTLMEngineImpl.makeRandomChallenge();
            }
            return this.clientChallenge;
        }

        public byte[] getClientChallenge2() throws NTLMEngineException {
            if (this.clientChallenge2 == null) {
                this.clientChallenge2 = NTLMEngineImpl.makeRandomChallenge();
            }
            return this.clientChallenge2;
        }

        public byte[] getSecondaryKey() throws NTLMEngineException {
            if (this.secondaryKey == null) {
                this.secondaryKey = NTLMEngineImpl.makeSecondaryKey();
            }
            return this.secondaryKey;
        }

        public byte[] getLMHash() throws NTLMEngineException {
            if (this.lmHash == null) {
                this.lmHash = NTLMEngineImpl.lmHash(this.password);
            }
            return this.lmHash;
        }

        public byte[] getLMResponse() throws NTLMEngineException {
            if (this.lmResponse == null) {
                this.lmResponse = NTLMEngineImpl.lmResponse(getLMHash(), this.challenge);
            }
            return this.lmResponse;
        }

        public byte[] getNTLMHash() throws NTLMEngineException {
            if (this.ntlmHash == null) {
                this.ntlmHash = NTLMEngineImpl.ntlmHash(this.password);
            }
            return this.ntlmHash;
        }

        public byte[] getNTLMResponse() throws NTLMEngineException {
            if (this.ntlmResponse == null) {
                this.ntlmResponse = NTLMEngineImpl.lmResponse(getNTLMHash(), this.challenge);
            }
            return this.ntlmResponse;
        }

        public byte[] getLMv2Hash() throws NTLMEngineException {
            if (this.lmv2Hash == null) {
                this.lmv2Hash = NTLMEngineImpl.lmv2Hash(this.domain, this.user, getNTLMHash());
            }
            return this.lmv2Hash;
        }

        public byte[] getNTLMv2Hash() throws NTLMEngineException {
            if (this.ntlmv2Hash == null) {
                this.ntlmv2Hash = NTLMEngineImpl.ntlmv2Hash(this.domain, this.user, getNTLMHash());
            }
            return this.ntlmv2Hash;
        }

        public byte[] getTimestamp() {
            if (this.timestamp == null) {
                long currentTimeMillis = (System.currentTimeMillis() + 11644473600000L) * 10000;
                this.timestamp = new byte[8];
                for (int i = 0; i < 8; i++) {
                    this.timestamp[i] = (byte) ((int) currentTimeMillis);
                    currentTimeMillis >>>= 8;
                }
            }
            return this.timestamp;
        }

        public byte[] getNTLMv2Blob() throws NTLMEngineException {
            if (this.ntlmv2Blob == null) {
                this.ntlmv2Blob = NTLMEngineImpl.createBlob(getClientChallenge2(), this.targetInformation, getTimestamp());
            }
            return this.ntlmv2Blob;
        }

        public byte[] getNTLMv2Response() throws NTLMEngineException {
            if (this.ntlmv2Response == null) {
                this.ntlmv2Response = NTLMEngineImpl.lmv2Response(getNTLMv2Hash(), this.challenge, getNTLMv2Blob());
            }
            return this.ntlmv2Response;
        }

        public byte[] getLMv2Response() throws NTLMEngineException {
            if (this.lmv2Response == null) {
                this.lmv2Response = NTLMEngineImpl.lmv2Response(getLMv2Hash(), this.challenge, getClientChallenge());
            }
            return this.lmv2Response;
        }

        public byte[] getNTLM2SessionResponse() throws NTLMEngineException {
            if (this.ntlm2SessionResponse == null) {
                this.ntlm2SessionResponse = NTLMEngineImpl.ntlm2SessionResponse(getNTLMHash(), this.challenge, getClientChallenge());
            }
            return this.ntlm2SessionResponse;
        }

        public byte[] getLM2SessionResponse() throws NTLMEngineException {
            if (this.lm2SessionResponse == null) {
                byte[] clientChallenge3 = getClientChallenge();
                this.lm2SessionResponse = new byte[24];
                System.arraycopy(clientChallenge3, 0, this.lm2SessionResponse, 0, clientChallenge3.length);
                byte[] bArr = this.lm2SessionResponse;
                Arrays.fill(bArr, clientChallenge3.length, bArr.length, (byte) 0);
            }
            return this.lm2SessionResponse;
        }

        public byte[] getLMUserSessionKey() throws NTLMEngineException {
            if (this.lmUserSessionKey == null) {
                this.lmUserSessionKey = new byte[16];
                System.arraycopy(getLMHash(), 0, this.lmUserSessionKey, 0, 8);
                Arrays.fill(this.lmUserSessionKey, 8, 16, (byte) 0);
            }
            return this.lmUserSessionKey;
        }

        public byte[] getNTLMUserSessionKey() throws NTLMEngineException {
            if (this.ntlmUserSessionKey == null) {
                MD4 md4 = new MD4();
                md4.update(getNTLMHash());
                this.ntlmUserSessionKey = md4.getOutput();
            }
            return this.ntlmUserSessionKey;
        }

        public byte[] getNTLMv2UserSessionKey() throws NTLMEngineException {
            if (this.ntlmv2UserSessionKey == null) {
                byte[] nTLMv2Hash = getNTLMv2Hash();
                byte[] bArr = new byte[16];
                System.arraycopy(getNTLMv2Response(), 0, bArr, 0, 16);
                this.ntlmv2UserSessionKey = NTLMEngineImpl.hmacMD5(bArr, nTLMv2Hash);
            }
            return this.ntlmv2UserSessionKey;
        }

        public byte[] getNTLM2SessionResponseUserSessionKey() throws NTLMEngineException {
            if (this.ntlm2SessionResponseUserSessionKey == null) {
                byte[] lM2SessionResponse = getLM2SessionResponse();
                byte[] bArr = this.challenge;
                byte[] bArr2 = new byte[(bArr.length + lM2SessionResponse.length)];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                System.arraycopy(lM2SessionResponse, 0, bArr2, this.challenge.length, lM2SessionResponse.length);
                this.ntlm2SessionResponseUserSessionKey = NTLMEngineImpl.hmacMD5(bArr2, getNTLMUserSessionKey());
            }
            return this.ntlm2SessionResponseUserSessionKey;
        }

        public byte[] getLanManagerSessionKey() throws NTLMEngineException {
            if (this.lanManagerSessionKey == null) {
                try {
                    byte[] bArr = new byte[14];
                    System.arraycopy(getLMHash(), 0, bArr, 0, 8);
                    Arrays.fill(bArr, 8, bArr.length, (byte) -67);
                    Key access$900 = NTLMEngineImpl.createDESKey(bArr, 0);
                    Key access$9002 = NTLMEngineImpl.createDESKey(bArr, 7);
                    byte[] bArr2 = new byte[8];
                    System.arraycopy(getLMResponse(), 0, bArr2, 0, bArr2.length);
                    Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
                    instance.init(1, access$900);
                    byte[] doFinal = instance.doFinal(bArr2);
                    Cipher instance2 = Cipher.getInstance("DES/ECB/NoPadding");
                    instance2.init(1, access$9002);
                    byte[] doFinal2 = instance2.doFinal(bArr2);
                    this.lanManagerSessionKey = new byte[16];
                    System.arraycopy(doFinal, 0, this.lanManagerSessionKey, 0, doFinal.length);
                    System.arraycopy(doFinal2, 0, this.lanManagerSessionKey, doFinal.length, doFinal2.length);
                } catch (Exception e) {
                    throw new NTLMEngineException(e.getMessage(), e);
                }
            }
            return this.lanManagerSessionKey;
        }
    }

    static byte[] hmacMD5(byte[] bArr, byte[] bArr2) throws NTLMEngineException {
        HMACMD5 hmacmd5 = new HMACMD5(bArr2);
        hmacmd5.update(bArr);
        return hmacmd5.getOutput();
    }

    static byte[] RC4(byte[] bArr, byte[] bArr2) throws NTLMEngineException {
        try {
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(1, new SecretKeySpec(bArr2, "RC4"));
            return instance.doFinal(bArr);
        } catch (Exception e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    static byte[] ntlm2SessionResponse(byte[] bArr, byte[] bArr2, byte[] bArr3) throws NTLMEngineException {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr2);
            instance.update(bArr3);
            byte[] digest = instance.digest();
            byte[] bArr4 = new byte[8];
            System.arraycopy(digest, 0, bArr4, 0, 8);
            return lmResponse(bArr, bArr4);
        } catch (Exception e) {
            if (e instanceof NTLMEngineException) {
                throw ((NTLMEngineException) e);
            }
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] lmHash(String str) throws NTLMEngineException {
        try {
            byte[] bytes = str.toUpperCase(Locale.ROOT).getBytes(DEFAULT_CHARSET);
            byte[] bArr = new byte[14];
            System.arraycopy(bytes, 0, bArr, 0, Math.min(bytes.length, 14));
            Key createDESKey = createDESKey(bArr, 0);
            Key createDESKey2 = createDESKey(bArr, 7);
            byte[] bytes2 = "KGS!@#$%".getBytes(DEFAULT_CHARSET);
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(1, createDESKey);
            byte[] doFinal = instance.doFinal(bytes2);
            instance.init(1, createDESKey2);
            byte[] doFinal2 = instance.doFinal(bytes2);
            byte[] bArr2 = new byte[16];
            System.arraycopy(doFinal, 0, bArr2, 0, 8);
            System.arraycopy(doFinal2, 0, bArr2, 8, 8);
            return bArr2;
        } catch (Exception e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] ntlmHash(String str) throws NTLMEngineException {
        Charset charset = UNICODE_LITTLE_UNMARKED;
        if (charset != null) {
            byte[] bytes = str.getBytes(charset);
            MD4 md4 = new MD4();
            md4.update(bytes);
            return md4.getOutput();
        }
        throw new NTLMEngineException("Unicode not supported");
    }

    /* access modifiers changed from: private */
    public static byte[] lmv2Hash(String str, String str2, byte[] bArr) throws NTLMEngineException {
        if (UNICODE_LITTLE_UNMARKED != null) {
            HMACMD5 hmacmd5 = new HMACMD5(bArr);
            hmacmd5.update(str2.toUpperCase(Locale.ROOT).getBytes(UNICODE_LITTLE_UNMARKED));
            if (str != null) {
                hmacmd5.update(str.toUpperCase(Locale.ROOT).getBytes(UNICODE_LITTLE_UNMARKED));
            }
            return hmacmd5.getOutput();
        }
        throw new NTLMEngineException("Unicode not supported");
    }

    /* access modifiers changed from: private */
    public static byte[] ntlmv2Hash(String str, String str2, byte[] bArr) throws NTLMEngineException {
        if (UNICODE_LITTLE_UNMARKED != null) {
            HMACMD5 hmacmd5 = new HMACMD5(bArr);
            hmacmd5.update(str2.toUpperCase(Locale.ROOT).getBytes(UNICODE_LITTLE_UNMARKED));
            if (str != null) {
                hmacmd5.update(str.getBytes(UNICODE_LITTLE_UNMARKED));
            }
            return hmacmd5.getOutput();
        }
        throw new NTLMEngineException("Unicode not supported");
    }

    /* access modifiers changed from: private */
    public static byte[] lmResponse(byte[] bArr, byte[] bArr2) throws NTLMEngineException {
        try {
            byte[] bArr3 = new byte[21];
            System.arraycopy(bArr, 0, bArr3, 0, 16);
            Key createDESKey = createDESKey(bArr3, 0);
            Key createDESKey2 = createDESKey(bArr3, 7);
            Key createDESKey3 = createDESKey(bArr3, 14);
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(1, createDESKey);
            byte[] doFinal = instance.doFinal(bArr2);
            instance.init(1, createDESKey2);
            byte[] doFinal2 = instance.doFinal(bArr2);
            instance.init(1, createDESKey3);
            byte[] doFinal3 = instance.doFinal(bArr2);
            byte[] bArr4 = new byte[24];
            System.arraycopy(doFinal, 0, bArr4, 0, 8);
            System.arraycopy(doFinal2, 0, bArr4, 8, 8);
            System.arraycopy(doFinal3, 0, bArr4, 16, 8);
            return bArr4;
        } catch (Exception e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] lmv2Response(byte[] bArr, byte[] bArr2, byte[] bArr3) throws NTLMEngineException {
        HMACMD5 hmacmd5 = new HMACMD5(bArr);
        hmacmd5.update(bArr2);
        hmacmd5.update(bArr3);
        byte[] output = hmacmd5.getOutput();
        byte[] bArr4 = new byte[(output.length + bArr3.length)];
        System.arraycopy(output, 0, bArr4, 0, output.length);
        System.arraycopy(bArr3, 0, bArr4, output.length, bArr3.length);
        return bArr4;
    }

    /* access modifiers changed from: private */
    public static byte[] createBlob(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = {1, 1, 0, 0};
        byte[] bArr5 = {0, 0, 0, 0};
        byte[] bArr6 = {0, 0, 0, 0};
        byte[] bArr7 = {0, 0, 0, 0};
        byte[] bArr8 = new byte[(bArr4.length + bArr5.length + bArr3.length + 8 + bArr6.length + bArr2.length + bArr7.length)];
        System.arraycopy(bArr4, 0, bArr8, 0, bArr4.length);
        int length = bArr4.length + 0;
        System.arraycopy(bArr5, 0, bArr8, length, bArr5.length);
        int length2 = length + bArr5.length;
        System.arraycopy(bArr3, 0, bArr8, length2, bArr3.length);
        int length3 = length2 + bArr3.length;
        System.arraycopy(bArr, 0, bArr8, length3, 8);
        int i = length3 + 8;
        System.arraycopy(bArr6, 0, bArr8, i, bArr6.length);
        int length4 = i + bArr6.length;
        System.arraycopy(bArr2, 0, bArr8, length4, bArr2.length);
        System.arraycopy(bArr7, 0, bArr8, length4 + bArr2.length, bArr7.length);
        int length5 = bArr7.length;
        return bArr8;
    }

    /* access modifiers changed from: private */
    public static Key createDESKey(byte[] bArr, int i) {
        byte[] bArr2 = new byte[7];
        System.arraycopy(bArr, i, bArr2, 0, 7);
        byte[] bArr3 = {bArr2[0], (byte) ((bArr2[0] << 7) | ((bArr2[1] & UByte.MAX_VALUE) >>> 1)), (byte) ((bArr2[1] << 6) | ((bArr2[2] & UByte.MAX_VALUE) >>> 2)), (byte) ((bArr2[2] << 5) | ((bArr2[3] & UByte.MAX_VALUE) >>> 3)), (byte) ((bArr2[3] << 4) | ((bArr2[4] & UByte.MAX_VALUE) >>> 4)), (byte) ((bArr2[4] << 3) | ((bArr2[5] & UByte.MAX_VALUE) >>> 5)), (byte) ((bArr2[5] << 2) | ((bArr2[6] & UByte.MAX_VALUE) >>> 6)), (byte) (bArr2[6] << 1)};
        oddParity(bArr3);
        return new SecretKeySpec(bArr3, "DES");
    }

    private static void oddParity(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            if ((((b >>> 1) ^ ((((((b >>> 7) ^ (b >>> 6)) ^ (b >>> 5)) ^ (b >>> 4)) ^ (b >>> 3)) ^ (b >>> 2))) & 1) == 0) {
                bArr[i] = (byte) (bArr[i] | 1);
            } else {
                bArr[i] = (byte) (bArr[i] & -2);
            }
        }
    }

    static class NTLMMessage {
        private int currentOutputPosition;
        private byte[] messageContents;

        NTLMMessage() {
            this.messageContents = null;
            this.currentOutputPosition = 0;
        }

        NTLMMessage(String str, int i) throws NTLMEngineException {
            this.messageContents = null;
            int i2 = 0;
            this.currentOutputPosition = 0;
            this.messageContents = Base64.decode(str.getBytes(NTLMEngineImpl.DEFAULT_CHARSET), 2);
            if (this.messageContents.length >= NTLMEngineImpl.SIGNATURE.length) {
                while (i2 < NTLMEngineImpl.SIGNATURE.length) {
                    if (this.messageContents[i2] == NTLMEngineImpl.SIGNATURE[i2]) {
                        i2++;
                    } else {
                        throw new NTLMEngineException("NTLM message expected - instead got unrecognized bytes");
                    }
                }
                int readULong = readULong(NTLMEngineImpl.SIGNATURE.length);
                if (readULong == i) {
                    this.currentOutputPosition = this.messageContents.length;
                    return;
                }
                throw new NTLMEngineException("NTLM type " + Integer.toString(i) + " message expected - instead got type " + Integer.toString(readULong));
            }
            throw new NTLMEngineException("NTLM message decoding error - packet too short");
        }

        /* access modifiers changed from: protected */
        public int getPreambleLength() {
            return NTLMEngineImpl.SIGNATURE.length + 4;
        }

        /* access modifiers changed from: protected */
        public int getMessageLength() {
            return this.currentOutputPosition;
        }

        /* access modifiers changed from: protected */
        public byte readByte(int i) throws NTLMEngineException {
            byte[] bArr = this.messageContents;
            if (bArr.length >= i + 1) {
                return bArr[i];
            }
            throw new NTLMEngineException("NTLM: Message too short");
        }

        /* access modifiers changed from: protected */
        public void readBytes(byte[] bArr, int i) throws NTLMEngineException {
            byte[] bArr2 = this.messageContents;
            if (bArr2.length >= bArr.length + i) {
                System.arraycopy(bArr2, i, bArr, 0, bArr.length);
                return;
            }
            throw new NTLMEngineException("NTLM: Message too short");
        }

        /* access modifiers changed from: protected */
        public int readUShort(int i) throws NTLMEngineException {
            return NTLMEngineImpl.readUShort(this.messageContents, i);
        }

        /* access modifiers changed from: protected */
        public int readULong(int i) throws NTLMEngineException {
            return NTLMEngineImpl.readULong(this.messageContents, i);
        }

        /* access modifiers changed from: protected */
        public byte[] readSecurityBuffer(int i) throws NTLMEngineException {
            return NTLMEngineImpl.readSecurityBuffer(this.messageContents, i);
        }

        /* access modifiers changed from: protected */
        public void prepareResponse(int i, int i2) {
            this.messageContents = new byte[i];
            this.currentOutputPosition = 0;
            addBytes(NTLMEngineImpl.SIGNATURE);
            addULong(i2);
        }

        /* access modifiers changed from: protected */
        public void addByte(byte b) {
            byte[] bArr = this.messageContents;
            int i = this.currentOutputPosition;
            bArr[i] = b;
            this.currentOutputPosition = i + 1;
        }

        /* access modifiers changed from: protected */
        public void addBytes(byte[] bArr) {
            if (bArr != null) {
                for (byte b : bArr) {
                    byte[] bArr2 = this.messageContents;
                    int i = this.currentOutputPosition;
                    bArr2[i] = b;
                    this.currentOutputPosition = i + 1;
                }
            }
        }

        /* access modifiers changed from: protected */
        public void addUShort(int i) {
            addByte((byte) (i & 255));
            addByte((byte) ((i >> 8) & 255));
        }

        /* access modifiers changed from: protected */
        public void addULong(int i) {
            addByte((byte) (i & 255));
            addByte((byte) ((i >> 8) & 255));
            addByte((byte) ((i >> 16) & 255));
            addByte((byte) ((i >> 24) & 255));
        }

        /* access modifiers changed from: package-private */
        public String getResponse() {
            byte[] bArr = this.messageContents;
            int length = bArr.length;
            int i = this.currentOutputPosition;
            if (length > i) {
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                bArr = bArr2;
            }
            return Base64.encodeToString(bArr, 2);
        }
    }

    static class Type1Message extends NTLMMessage {
        private final byte[] domainBytes;
        private final byte[] hostBytes;

        Type1Message(String str, String str2) throws NTLMEngineException {
            if (NTLMEngineImpl.UNICODE_LITTLE_UNMARKED != null) {
                String access$1600 = NTLMEngineImpl.convertHost(str2);
                String access$1700 = NTLMEngineImpl.convertDomain(str);
                byte[] bArr = null;
                this.hostBytes = access$1600 != null ? access$1600.getBytes(NTLMEngineImpl.UNICODE_LITTLE_UNMARKED) : null;
                this.domainBytes = access$1700 != null ? access$1700.toUpperCase(Locale.ROOT).getBytes(NTLMEngineImpl.UNICODE_LITTLE_UNMARKED) : bArr;
                return;
            }
            throw new NTLMEngineException("Unicode not supported");
        }

        Type1Message() {
            this.hostBytes = null;
            this.domainBytes = null;
        }

        /* access modifiers changed from: package-private */
        public String getResponse() {
            prepareResponse(40, 1);
            addULong(-1576500735);
            addUShort(0);
            addUShort(0);
            addULong(40);
            addUShort(0);
            addUShort(0);
            addULong(40);
            addUShort(261);
            addULong(2600);
            addUShort(3840);
            byte[] bArr = this.hostBytes;
            if (bArr != null) {
                addBytes(bArr);
            }
            byte[] bArr2 = this.domainBytes;
            if (bArr2 != null) {
                addBytes(bArr2);
            }
            return super.getResponse();
        }
    }

    static class Type2Message extends NTLMMessage {
        protected byte[] challenge = new byte[8];
        protected int flags;
        protected String target;
        protected byte[] targetInfo;

        Type2Message(String str) throws NTLMEngineException {
            super(str, 2);
            readBytes(this.challenge, 24);
            this.flags = readULong(20);
            if ((this.flags & 1) != 0) {
                this.target = null;
                if (getMessageLength() >= 20) {
                    byte[] readSecurityBuffer = readSecurityBuffer(12);
                    if (readSecurityBuffer.length != 0) {
                        try {
                            this.target = new String(readSecurityBuffer, "UnicodeLittleUnmarked");
                        } catch (UnsupportedEncodingException e) {
                            throw new NTLMEngineException(e.getMessage(), e);
                        }
                    }
                }
                this.targetInfo = null;
                if (getMessageLength() >= 48) {
                    byte[] readSecurityBuffer2 = readSecurityBuffer(40);
                    if (readSecurityBuffer2.length != 0) {
                        this.targetInfo = readSecurityBuffer2;
                        return;
                    }
                    return;
                }
                return;
            }
            throw new NTLMEngineException("NTLM type 2 message indicates no support for Unicode. Flags are: " + Integer.toString(this.flags));
        }

        /* access modifiers changed from: package-private */
        public byte[] getChallenge() {
            return this.challenge;
        }

        /* access modifiers changed from: package-private */
        public String getTarget() {
            return this.target;
        }

        /* access modifiers changed from: package-private */
        public byte[] getTargetInfo() {
            return this.targetInfo;
        }

        /* access modifiers changed from: package-private */
        public int getFlags() {
            return this.flags;
        }
    }

    static class Type3Message extends NTLMMessage {
        protected byte[] domainBytes;
        protected byte[] hostBytes;
        protected byte[] lmResp;
        protected byte[] ntResp;
        protected byte[] sessionKey;
        protected int type2Flags;
        protected byte[] userBytes;

        Type3Message(String str, String str2, String str3, String str4, byte[] bArr, int i, String str5, byte[] bArr2) throws NTLMEngineException {
            byte[] bArr3;
            int i2 = i;
            this.type2Flags = i2;
            String access$1600 = NTLMEngineImpl.convertHost(str2);
            String access$1700 = NTLMEngineImpl.convertDomain(str);
            CipherGen cipherGen = new CipherGen(access$1700, str3, str4, bArr, str5, bArr2);
            if ((8388608 & i2) != 0 && bArr2 != null && str5 != null) {
                try {
                    this.ntResp = cipherGen.getNTLMv2Response();
                    this.lmResp = cipherGen.getLMv2Response();
                    if ((i2 & 128) != 0) {
                        bArr3 = cipherGen.getLanManagerSessionKey();
                    } else {
                        bArr3 = cipherGen.getNTLMv2UserSessionKey();
                    }
                } catch (NTLMEngineException unused) {
                    this.ntResp = new byte[0];
                    this.lmResp = cipherGen.getLMResponse();
                    if ((i2 & 128) != 0) {
                        bArr3 = cipherGen.getLanManagerSessionKey();
                    } else {
                        bArr3 = cipherGen.getLMUserSessionKey();
                    }
                }
            } else if ((524288 & i2) != 0) {
                this.ntResp = cipherGen.getNTLM2SessionResponse();
                this.lmResp = cipherGen.getLM2SessionResponse();
                if ((i2 & 128) != 0) {
                    bArr3 = cipherGen.getLanManagerSessionKey();
                } else {
                    bArr3 = cipherGen.getNTLM2SessionResponseUserSessionKey();
                }
            } else {
                this.ntResp = cipherGen.getNTLMResponse();
                this.lmResp = cipherGen.getLMResponse();
                if ((i2 & 128) != 0) {
                    bArr3 = cipherGen.getLanManagerSessionKey();
                } else {
                    bArr3 = cipherGen.getNTLMUserSessionKey();
                }
            }
            byte[] bArr4 = null;
            if ((i2 & 16) == 0) {
                this.sessionKey = null;
            } else if ((i2 & 1073741824) != 0) {
                this.sessionKey = NTLMEngineImpl.RC4(cipherGen.getSecondaryKey(), bArr3);
            } else {
                this.sessionKey = bArr3;
            }
            if (NTLMEngineImpl.UNICODE_LITTLE_UNMARKED != null) {
                this.hostBytes = access$1600 != null ? access$1600.getBytes(NTLMEngineImpl.UNICODE_LITTLE_UNMARKED) : null;
                this.domainBytes = access$1700 != null ? access$1700.toUpperCase(Locale.ROOT).getBytes(NTLMEngineImpl.UNICODE_LITTLE_UNMARKED) : bArr4;
                String str6 = str3;
                this.userBytes = str3.getBytes(NTLMEngineImpl.UNICODE_LITTLE_UNMARKED);
                return;
            }
            throw new NTLMEngineException("Unicode not supported");
        }

        /* access modifiers changed from: package-private */
        public String getResponse() {
            int length = this.ntResp.length;
            int length2 = this.lmResp.length;
            byte[] bArr = this.domainBytes;
            int i = 0;
            int length3 = bArr != null ? bArr.length : 0;
            byte[] bArr2 = this.hostBytes;
            int length4 = bArr2 != null ? bArr2.length : 0;
            int length5 = this.userBytes.length;
            byte[] bArr3 = this.sessionKey;
            if (bArr3 != null) {
                i = bArr3.length;
            }
            int i2 = length2 + 72;
            int i3 = i2 + length;
            int i4 = i3 + length3;
            int i5 = i4 + length5;
            int i6 = i5 + length4;
            prepareResponse(i6 + i, 3);
            addUShort(length2);
            addUShort(length2);
            addULong(72);
            addUShort(length);
            addUShort(length);
            addULong(i2);
            addUShort(length3);
            addUShort(length3);
            addULong(i3);
            addUShort(length5);
            addUShort(length5);
            addULong(i4);
            addUShort(length4);
            addUShort(length4);
            addULong(i5);
            addUShort(i);
            addUShort(i);
            addULong(i6);
            int i7 = this.type2Flags;
            addULong((i7 & 4) | (i7 & 128) | (i7 & 512) | (524288 & i7) | NTLMEngineImpl.FLAG_REQUEST_VERSION | (32768 & i7) | (i7 & 32) | (i7 & 16) | (NTLMEngineImpl.FLAG_REQUEST_128BIT_KEY_EXCH & i7) | (Integer.MIN_VALUE & i7) | (1073741824 & i7) | (8388608 & i7) | (i7 & 1));
            addUShort(261);
            addULong(2600);
            addUShort(3840);
            addBytes(this.lmResp);
            addBytes(this.ntResp);
            addBytes(this.domainBytes);
            addBytes(this.userBytes);
            addBytes(this.hostBytes);
            byte[] bArr4 = this.sessionKey;
            if (bArr4 != null) {
                addBytes(bArr4);
            }
            return super.getResponse();
        }
    }

    static void writeULong(byte[] bArr, int i, int i2) {
        bArr[i2] = (byte) (i & 255);
        bArr[i2 + 1] = (byte) ((i >> 8) & 255);
        bArr[i2 + 2] = (byte) ((i >> 16) & 255);
        bArr[i2 + 3] = (byte) ((i >> 24) & 255);
    }

    static class MD4 {

        /* renamed from: A */
        protected int f1480A = 1732584193;

        /* renamed from: B */
        protected int f1481B = -271733879;

        /* renamed from: C */
        protected int f1482C = -1732584194;

        /* renamed from: D */
        protected int f1483D = 271733878;
        protected long count = 0;
        protected byte[] dataBuffer = new byte[64];

        MD4() {
        }

        /* access modifiers changed from: package-private */
        public void update(byte[] bArr) {
            byte[] bArr2;
            int i = (int) (this.count & 63);
            int i2 = 0;
            while (true) {
                int length = (bArr.length - i2) + i;
                bArr2 = this.dataBuffer;
                if (length < bArr2.length) {
                    break;
                }
                int length2 = bArr2.length - i;
                System.arraycopy(bArr, i2, bArr2, i, length2);
                this.count += (long) length2;
                i2 += length2;
                processBuffer();
                i = 0;
            }
            if (i2 < bArr.length) {
                int length3 = bArr.length - i2;
                System.arraycopy(bArr, i2, bArr2, i, length3);
                this.count += (long) length3;
            }
        }

        /* access modifiers changed from: package-private */
        public byte[] getOutput() {
            int i = (int) (this.count & 63);
            int i2 = i < 56 ? 56 - i : 120 - i;
            byte[] bArr = new byte[(i2 + 8)];
            bArr[0] = ByteCompanionObject.MIN_VALUE;
            for (int i3 = 0; i3 < 8; i3++) {
                bArr[i2 + i3] = (byte) ((int) ((this.count * 8) >>> (i3 * 8)));
            }
            update(bArr);
            byte[] bArr2 = new byte[16];
            NTLMEngineImpl.writeULong(bArr2, this.f1480A, 0);
            NTLMEngineImpl.writeULong(bArr2, this.f1481B, 4);
            NTLMEngineImpl.writeULong(bArr2, this.f1482C, 8);
            NTLMEngineImpl.writeULong(bArr2, this.f1483D, 12);
            return bArr2;
        }

        /* access modifiers changed from: protected */
        public void processBuffer() {
            int[] iArr = new int[16];
            for (int i = 0; i < 16; i++) {
                byte[] bArr = this.dataBuffer;
                int i2 = i * 4;
                iArr[i] = (bArr[i2] & UByte.MAX_VALUE) + ((bArr[i2 + 1] & UByte.MAX_VALUE) << 8) + ((bArr[i2 + 2] & UByte.MAX_VALUE) << 16) + ((bArr[i2 + 3] & UByte.MAX_VALUE) << 24);
            }
            int i3 = this.f1480A;
            int i4 = this.f1481B;
            int i5 = this.f1482C;
            int i6 = this.f1483D;
            round1(iArr);
            round2(iArr);
            round3(iArr);
            this.f1480A += i3;
            this.f1481B += i4;
            this.f1482C += i5;
            this.f1483D += i6;
        }

        /* access modifiers changed from: protected */
        public void round1(int[] iArr) {
            this.f1480A = NTLMEngineImpl.rotintlft(this.f1480A + NTLMEngineImpl.m1830F(this.f1481B, this.f1482C, this.f1483D) + iArr[0], 3);
            this.f1483D = NTLMEngineImpl.rotintlft(this.f1483D + NTLMEngineImpl.m1830F(this.f1480A, this.f1481B, this.f1482C) + iArr[1], 7);
            this.f1482C = NTLMEngineImpl.rotintlft(this.f1482C + NTLMEngineImpl.m1830F(this.f1483D, this.f1480A, this.f1481B) + iArr[2], 11);
            this.f1481B = NTLMEngineImpl.rotintlft(this.f1481B + NTLMEngineImpl.m1830F(this.f1482C, this.f1483D, this.f1480A) + iArr[3], 19);
            this.f1480A = NTLMEngineImpl.rotintlft(this.f1480A + NTLMEngineImpl.m1830F(this.f1481B, this.f1482C, this.f1483D) + iArr[4], 3);
            this.f1483D = NTLMEngineImpl.rotintlft(this.f1483D + NTLMEngineImpl.m1830F(this.f1480A, this.f1481B, this.f1482C) + iArr[5], 7);
            this.f1482C = NTLMEngineImpl.rotintlft(this.f1482C + NTLMEngineImpl.m1830F(this.f1483D, this.f1480A, this.f1481B) + iArr[6], 11);
            this.f1481B = NTLMEngineImpl.rotintlft(this.f1481B + NTLMEngineImpl.m1830F(this.f1482C, this.f1483D, this.f1480A) + iArr[7], 19);
            this.f1480A = NTLMEngineImpl.rotintlft(this.f1480A + NTLMEngineImpl.m1830F(this.f1481B, this.f1482C, this.f1483D) + iArr[8], 3);
            this.f1483D = NTLMEngineImpl.rotintlft(this.f1483D + NTLMEngineImpl.m1830F(this.f1480A, this.f1481B, this.f1482C) + iArr[9], 7);
            this.f1482C = NTLMEngineImpl.rotintlft(this.f1482C + NTLMEngineImpl.m1830F(this.f1483D, this.f1480A, this.f1481B) + iArr[10], 11);
            this.f1481B = NTLMEngineImpl.rotintlft(this.f1481B + NTLMEngineImpl.m1830F(this.f1482C, this.f1483D, this.f1480A) + iArr[11], 19);
            this.f1480A = NTLMEngineImpl.rotintlft(this.f1480A + NTLMEngineImpl.m1830F(this.f1481B, this.f1482C, this.f1483D) + iArr[12], 3);
            this.f1483D = NTLMEngineImpl.rotintlft(this.f1483D + NTLMEngineImpl.m1830F(this.f1480A, this.f1481B, this.f1482C) + iArr[13], 7);
            this.f1482C = NTLMEngineImpl.rotintlft(this.f1482C + NTLMEngineImpl.m1830F(this.f1483D, this.f1480A, this.f1481B) + iArr[14], 11);
            this.f1481B = NTLMEngineImpl.rotintlft(this.f1481B + NTLMEngineImpl.m1830F(this.f1482C, this.f1483D, this.f1480A) + iArr[15], 19);
        }

        /* access modifiers changed from: protected */
        public void round2(int[] iArr) {
            this.f1480A = NTLMEngineImpl.rotintlft(this.f1480A + NTLMEngineImpl.m1831G(this.f1481B, this.f1482C, this.f1483D) + iArr[0] + 1518500249, 3);
            this.f1483D = NTLMEngineImpl.rotintlft(this.f1483D + NTLMEngineImpl.m1831G(this.f1480A, this.f1481B, this.f1482C) + iArr[4] + 1518500249, 5);
            this.f1482C = NTLMEngineImpl.rotintlft(this.f1482C + NTLMEngineImpl.m1831G(this.f1483D, this.f1480A, this.f1481B) + iArr[8] + 1518500249, 9);
            this.f1481B = NTLMEngineImpl.rotintlft(this.f1481B + NTLMEngineImpl.m1831G(this.f1482C, this.f1483D, this.f1480A) + iArr[12] + 1518500249, 13);
            this.f1480A = NTLMEngineImpl.rotintlft(this.f1480A + NTLMEngineImpl.m1831G(this.f1481B, this.f1482C, this.f1483D) + iArr[1] + 1518500249, 3);
            this.f1483D = NTLMEngineImpl.rotintlft(this.f1483D + NTLMEngineImpl.m1831G(this.f1480A, this.f1481B, this.f1482C) + iArr[5] + 1518500249, 5);
            this.f1482C = NTLMEngineImpl.rotintlft(this.f1482C + NTLMEngineImpl.m1831G(this.f1483D, this.f1480A, this.f1481B) + iArr[9] + 1518500249, 9);
            this.f1481B = NTLMEngineImpl.rotintlft(this.f1481B + NTLMEngineImpl.m1831G(this.f1482C, this.f1483D, this.f1480A) + iArr[13] + 1518500249, 13);
            this.f1480A = NTLMEngineImpl.rotintlft(this.f1480A + NTLMEngineImpl.m1831G(this.f1481B, this.f1482C, this.f1483D) + iArr[2] + 1518500249, 3);
            this.f1483D = NTLMEngineImpl.rotintlft(this.f1483D + NTLMEngineImpl.m1831G(this.f1480A, this.f1481B, this.f1482C) + iArr[6] + 1518500249, 5);
            this.f1482C = NTLMEngineImpl.rotintlft(this.f1482C + NTLMEngineImpl.m1831G(this.f1483D, this.f1480A, this.f1481B) + iArr[10] + 1518500249, 9);
            this.f1481B = NTLMEngineImpl.rotintlft(this.f1481B + NTLMEngineImpl.m1831G(this.f1482C, this.f1483D, this.f1480A) + iArr[14] + 1518500249, 13);
            this.f1480A = NTLMEngineImpl.rotintlft(this.f1480A + NTLMEngineImpl.m1831G(this.f1481B, this.f1482C, this.f1483D) + iArr[3] + 1518500249, 3);
            this.f1483D = NTLMEngineImpl.rotintlft(this.f1483D + NTLMEngineImpl.m1831G(this.f1480A, this.f1481B, this.f1482C) + iArr[7] + 1518500249, 5);
            this.f1482C = NTLMEngineImpl.rotintlft(this.f1482C + NTLMEngineImpl.m1831G(this.f1483D, this.f1480A, this.f1481B) + iArr[11] + 1518500249, 9);
            this.f1481B = NTLMEngineImpl.rotintlft(this.f1481B + NTLMEngineImpl.m1831G(this.f1482C, this.f1483D, this.f1480A) + iArr[15] + 1518500249, 13);
        }

        /* access modifiers changed from: protected */
        public void round3(int[] iArr) {
            this.f1480A = NTLMEngineImpl.rotintlft(this.f1480A + NTLMEngineImpl.m1832H(this.f1481B, this.f1482C, this.f1483D) + iArr[0] + 1859775393, 3);
            this.f1483D = NTLMEngineImpl.rotintlft(this.f1483D + NTLMEngineImpl.m1832H(this.f1480A, this.f1481B, this.f1482C) + iArr[8] + 1859775393, 9);
            this.f1482C = NTLMEngineImpl.rotintlft(this.f1482C + NTLMEngineImpl.m1832H(this.f1483D, this.f1480A, this.f1481B) + iArr[4] + 1859775393, 11);
            this.f1481B = NTLMEngineImpl.rotintlft(this.f1481B + NTLMEngineImpl.m1832H(this.f1482C, this.f1483D, this.f1480A) + iArr[12] + 1859775393, 15);
            this.f1480A = NTLMEngineImpl.rotintlft(this.f1480A + NTLMEngineImpl.m1832H(this.f1481B, this.f1482C, this.f1483D) + iArr[2] + 1859775393, 3);
            this.f1483D = NTLMEngineImpl.rotintlft(this.f1483D + NTLMEngineImpl.m1832H(this.f1480A, this.f1481B, this.f1482C) + iArr[10] + 1859775393, 9);
            this.f1482C = NTLMEngineImpl.rotintlft(this.f1482C + NTLMEngineImpl.m1832H(this.f1483D, this.f1480A, this.f1481B) + iArr[6] + 1859775393, 11);
            this.f1481B = NTLMEngineImpl.rotintlft(this.f1481B + NTLMEngineImpl.m1832H(this.f1482C, this.f1483D, this.f1480A) + iArr[14] + 1859775393, 15);
            this.f1480A = NTLMEngineImpl.rotintlft(this.f1480A + NTLMEngineImpl.m1832H(this.f1481B, this.f1482C, this.f1483D) + iArr[1] + 1859775393, 3);
            this.f1483D = NTLMEngineImpl.rotintlft(this.f1483D + NTLMEngineImpl.m1832H(this.f1480A, this.f1481B, this.f1482C) + iArr[9] + 1859775393, 9);
            this.f1482C = NTLMEngineImpl.rotintlft(this.f1482C + NTLMEngineImpl.m1832H(this.f1483D, this.f1480A, this.f1481B) + iArr[5] + 1859775393, 11);
            this.f1481B = NTLMEngineImpl.rotintlft(this.f1481B + NTLMEngineImpl.m1832H(this.f1482C, this.f1483D, this.f1480A) + iArr[13] + 1859775393, 15);
            this.f1480A = NTLMEngineImpl.rotintlft(this.f1480A + NTLMEngineImpl.m1832H(this.f1481B, this.f1482C, this.f1483D) + iArr[3] + 1859775393, 3);
            this.f1483D = NTLMEngineImpl.rotintlft(this.f1483D + NTLMEngineImpl.m1832H(this.f1480A, this.f1481B, this.f1482C) + iArr[11] + 1859775393, 9);
            this.f1482C = NTLMEngineImpl.rotintlft(this.f1482C + NTLMEngineImpl.m1832H(this.f1483D, this.f1480A, this.f1481B) + iArr[7] + 1859775393, 11);
            this.f1481B = NTLMEngineImpl.rotintlft(this.f1481B + NTLMEngineImpl.m1832H(this.f1482C, this.f1483D, this.f1480A) + iArr[15] + 1859775393, 15);
        }
    }

    static class HMACMD5 {
        protected byte[] ipad;
        protected MessageDigest md5;
        protected byte[] opad;

        HMACMD5(byte[] bArr) throws NTLMEngineException {
            try {
                this.md5 = MessageDigest.getInstance("MD5");
                this.ipad = new byte[64];
                this.opad = new byte[64];
                int length = bArr.length;
                if (length > 64) {
                    this.md5.update(bArr);
                    bArr = this.md5.digest();
                    length = bArr.length;
                }
                int i = 0;
                while (i < length) {
                    this.ipad[i] = (byte) (54 ^ bArr[i]);
                    this.opad[i] = (byte) (92 ^ bArr[i]);
                    i++;
                }
                while (i < 64) {
                    this.ipad[i] = 54;
                    this.opad[i] = 92;
                    i++;
                }
                this.md5.reset();
                this.md5.update(this.ipad);
            } catch (Exception e) {
                throw new NTLMEngineException("Error getting md5 message digest implementation: " + e.getMessage(), e);
            }
        }

        /* access modifiers changed from: package-private */
        public byte[] getOutput() {
            byte[] digest = this.md5.digest();
            this.md5.update(this.opad);
            return this.md5.digest(digest);
        }

        /* access modifiers changed from: package-private */
        public void update(byte[] bArr) {
            this.md5.update(bArr);
        }

        /* access modifiers changed from: package-private */
        public void update(byte[] bArr, int i, int i2) {
            this.md5.update(bArr, i, i2);
        }
    }

    public String generateType1Msg(String str, String str2) throws NTLMEngineException {
        return getType1Message(str2, str);
    }

    public String generateType3Msg(String str, String str2, String str3, String str4, String str5) throws NTLMEngineException {
        Type2Message type2Message = new Type2Message(str5);
        return getType3Message(str, str2, str4, str3, type2Message.getChallenge(), type2Message.getFlags(), type2Message.getTarget(), type2Message.getTargetInfo());
    }
}
