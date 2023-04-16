package com.mob.commons.dialog.entity;

@Deprecated
public class MobPolicyUi extends BaseEntity {
    private int backgroundColorId;
    private String backgroundColorStr;
    private int negativeBtnColorId;
    private String negativeBtnColorStr;
    private int positiveBtnColorId;
    private String positiveBtnColorStr;

    private MobPolicyUi(Builder builder) {
        this.backgroundColorId = builder.backgroundColorId;
        this.backgroundColorStr = builder.backgroundColorStr;
        this.positiveBtnColorId = builder.positiveBtnColorId;
        this.positiveBtnColorStr = builder.positiveBtnColorStr;
        this.negativeBtnColorId = builder.negativeBtnColorId;
        this.negativeBtnColorStr = builder.negativeBtnColorStr;
    }

    public int getBackgroundColorId() {
        return this.backgroundColorId;
    }

    public String getBackgroundColorStr() {
        return this.backgroundColorStr;
    }

    public int getPositiveBtnColorId() {
        return this.positiveBtnColorId;
    }

    public String getPositiveBtnColorStr() {
        return this.positiveBtnColorStr;
    }

    public int getNegativeBtnColorId() {
        return this.negativeBtnColorId;
    }

    public String getNegativeBtnColorStr() {
        return this.negativeBtnColorStr;
    }

    public static class Builder extends BaseEntity {
        /* access modifiers changed from: private */
        public int backgroundColorId = -1;
        /* access modifiers changed from: private */
        public String backgroundColorStr;
        /* access modifiers changed from: private */
        public int negativeBtnColorId = -1;
        /* access modifiers changed from: private */
        public String negativeBtnColorStr;
        /* access modifiers changed from: private */
        public int positiveBtnColorId = -1;
        /* access modifiers changed from: private */
        public String positiveBtnColorStr;

        public MobPolicyUi build() {
            return new MobPolicyUi(this);
        }

        public Builder setBackgroundColorId(int i) {
            this.backgroundColorId = i;
            return this;
        }

        public Builder setBackgroundColorStr(String str) {
            this.backgroundColorStr = str;
            return this;
        }

        public Builder setPositiveBtnColorId(int i) {
            this.positiveBtnColorId = i;
            return this;
        }

        public Builder setPositiveBtnColorStr(String str) {
            this.positiveBtnColorStr = str;
            return this;
        }

        public Builder setNegativeBtnColorId(int i) {
            this.negativeBtnColorId = i;
            return this;
        }

        public Builder setNegativeBtnColorStr(String str) {
            this.negativeBtnColorStr = str;
            return this;
        }
    }
}
