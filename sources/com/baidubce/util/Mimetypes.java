package com.baidubce.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Mimetypes {
    public static final String MIMETYPE_OCTET_STREAM = "application/octet-stream";
    private static Mimetypes mimetypes;
    private HashMap<String, String> extensionToMimetypeMap = new HashMap<>();

    private Mimetypes() {
    }

    public static synchronized Mimetypes getInstance() {
        String str;
        synchronized (Mimetypes.class) {
            if (mimetypes != null) {
                Mimetypes mimetypes2 = mimetypes;
                return mimetypes2;
            }
            mimetypes = new Mimetypes();
            InputStream resourceAsStream = mimetypes.getClass().getResourceAsStream("/mime.types");
            if (resourceAsStream != null) {
                BLog.debug("Loading mime types from file in the classpath: mime.types");
                try {
                    mimetypes.loadAndReplaceMimetypes(resourceAsStream);
                    try {
                        resourceAsStream.close();
                    } catch (IOException e) {
                        e = e;
                        String str2 = "";
                    }
                } catch (IOException e2) {
                    try {
                        BLog.error("Failed to load mime types from file in the classpath: mime.types", (Throwable) e2);
                    } finally {
                        try {
                            resourceAsStream.close();
                        } catch (IOException e3) {
                            str = "";
                            BLog.debug(str, (Throwable) e3);
                        }
                    }
                }
            } else {
                BLog.warn("Unable to find 'mime.types' file in classpath");
            }
            Mimetypes mimetypes3 = mimetypes;
            return mimetypes3;
        }
        Mimetypes mimetypes32 = mimetypes;
        return mimetypes32;
    }

    public void loadAndReplaceMimetypes(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                String trim = readLine.trim();
                if (!trim.startsWith("#") && trim.length() != 0) {
                    StringTokenizer stringTokenizer = new StringTokenizer(trim, " \t");
                    if (stringTokenizer.countTokens() > 1) {
                        String nextToken = stringTokenizer.nextToken();
                        while (stringTokenizer.hasMoreTokens()) {
                            String nextToken2 = stringTokenizer.nextToken();
                            this.extensionToMimetypeMap.put(nextToken2.toLowerCase(), nextToken);
                            BLog.debug("Setting mime type for extension '" + nextToken2.toLowerCase() + "' to '" + nextToken + "'");
                        }
                    } else {
                        BLog.debug("Ignoring mimetype with no associated file extensions: '" + trim + "'");
                    }
                }
            } else {
                return;
            }
        }
    }

    public String getMimetype(String str) {
        int i;
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf <= 0 || (i = lastIndexOf + 1) >= str.length()) {
            BLog.debug("File name has no extension, mime type cannot be recognised for: " + str);
        } else {
            String lowerCase = str.substring(i).toLowerCase();
            if (this.extensionToMimetypeMap.keySet().contains(lowerCase)) {
                String str2 = this.extensionToMimetypeMap.get(lowerCase);
                BLog.debug("Recognised extension '" + lowerCase + "', mimetype is: '" + str2 + "'");
                return str2;
            }
            BLog.debug("Extension '" + lowerCase + "' is unrecognized in mime type listing, using default mime type: '" + MIMETYPE_OCTET_STREAM + "'");
        }
        return MIMETYPE_OCTET_STREAM;
    }

    public String getMimetype(File file) {
        return getMimetype(file.getName());
    }
}
