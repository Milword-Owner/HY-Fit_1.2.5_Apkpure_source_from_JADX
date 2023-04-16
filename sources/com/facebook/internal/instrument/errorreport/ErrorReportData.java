package com.facebook.internal.instrument.errorreport;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.facebook.internal.instrument.InstrumentUtility;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ErrorReportData {
    private static final String PARAM_TIMESTAMP = "timestamp";
    private static final String PRARAM_ERROR_MESSAGE = "error_message";
    @Nullable
    private String errorMessage;
    private String filename;
    @Nullable
    private Long timestamp;

    public ErrorReportData(String str) {
        this.timestamp = Long.valueOf(System.currentTimeMillis() / 1000);
        this.errorMessage = str;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(InstrumentUtility.ERROR_REPORT_PREFIX);
        stringBuffer.append(this.timestamp);
        stringBuffer.append(".json");
        this.filename = stringBuffer.toString();
    }

    public ErrorReportData(File file) {
        this.filename = file.getName();
        JSONObject readFile = InstrumentUtility.readFile(this.filename, true);
        if (readFile != null) {
            this.timestamp = Long.valueOf(readFile.optLong(PARAM_TIMESTAMP, 0));
            this.errorMessage = readFile.optString("error_message", (String) null);
        }
    }

    public int compareTo(ErrorReportData errorReportData) {
        Long l = this.timestamp;
        if (l == null) {
            return -1;
        }
        Long l2 = errorReportData.timestamp;
        if (l2 == null) {
            return 1;
        }
        return l2.compareTo(l);
    }

    public boolean isValid() {
        return (this.errorMessage == null || this.timestamp == null) ? false : true;
    }

    public void save() {
        if (isValid()) {
            InstrumentUtility.writeFile(this.filename, toString());
        }
    }

    public void clear() {
        InstrumentUtility.deleteFile(this.filename);
    }

    @Nullable
    public String toString() {
        JSONObject parameters = getParameters();
        if (parameters == null) {
            return null;
        }
        return parameters.toString();
    }

    @Nullable
    public JSONObject getParameters() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.timestamp != null) {
                jSONObject.put(PARAM_TIMESTAMP, this.timestamp);
            }
            jSONObject.put("error_message", this.errorMessage);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
