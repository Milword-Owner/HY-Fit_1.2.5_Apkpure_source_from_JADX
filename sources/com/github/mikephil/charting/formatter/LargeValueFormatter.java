package com.github.mikephil.charting.formatter;

import com.baidu.mobstat.Config;
import java.text.DecimalFormat;

public class LargeValueFormatter extends ValueFormatter {
    private DecimalFormat mFormat;
    private int mMaxLength;
    private String[] mSuffix;
    private String mText;

    public int getDecimalDigits() {
        return 0;
    }

    public LargeValueFormatter() {
        this.mSuffix = new String[]{"", Config.APP_KEY, Config.MODEL, "b", "t"};
        this.mMaxLength = 5;
        this.mText = "";
        this.mFormat = new DecimalFormat("###E00");
    }

    public LargeValueFormatter(String str) {
        this();
        this.mText = str;
    }

    public String getFormattedValue(float f) {
        return makePretty((double) f) + this.mText;
    }

    public void setAppendix(String str) {
        this.mText = str;
    }

    public void setSuffix(String[] strArr) {
        this.mSuffix = strArr;
    }

    public void setMaxLength(int i) {
        this.mMaxLength = i;
    }

    private String makePretty(double d) {
        String format = this.mFormat.format(d);
        int numericValue = Character.getNumericValue(format.charAt(format.length() - 1));
        String replaceAll = format.replaceAll("E[0-9][0-9]", this.mSuffix[Integer.valueOf(Character.getNumericValue(format.charAt(format.length() - 2)) + "" + numericValue).intValue() / 3]);
        while (true) {
            if (replaceAll.length() <= this.mMaxLength && !replaceAll.matches("[0-9]+\\.[a-z]")) {
                return replaceAll;
            }
            replaceAll = replaceAll.substring(0, replaceAll.length() - 2) + replaceAll.substring(replaceAll.length() - 1);
        }
    }
}
