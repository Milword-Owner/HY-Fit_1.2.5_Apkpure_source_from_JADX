package chipsea.bias.v235;

public class CSBiasAPI {
    public static final int CSBIAS_ERR_AGE = -4;
    public static final int CSBIAS_ERR_HEIGHT = -3;
    public static final int CSBIAS_ERR_IMPEDANCE = -6;
    public static final int CSBIAS_ERR_MODE = -7;
    public static final int CSBIAS_ERR_SEX = -5;
    public static final int CSBIAS_ERR_VCODE = -8;
    public static final int CSBIAS_ERR_WEIGTH = -2;
    public static final int CSBIAS_OK = 0;

    public static class CSBiasDataV235 {
        public double BFP;
        public double BMC;
        public double BMI;
        public int BMR;
        public double BWP;

        /* renamed from: FC */
        public double f72FC;

        /* renamed from: MA */
        public int f73MA;

        /* renamed from: MC */
        public double f74MC;

        /* renamed from: PP */
        public double f75PP;
        public int SBC;
        public double SBW;
        public double SLM;
        public double SMM;
        public double VFR;

        /* renamed from: WC */
        public double f76WC;
    }

    public static class CSBiasV235Resp {
        public CSBiasDataV235 data;
        public int result;
    }

    public static native CSBiasV235Resp cs_bias_v235(int i, int i2, int i3, int i4, int i5, int i6, int i7);

    static {
        System.loadLibrary("chipsea_bias_v235");
    }
}
