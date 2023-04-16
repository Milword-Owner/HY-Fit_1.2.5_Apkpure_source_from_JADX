package com.mob.commons.dialog.entity;

@Deprecated
public class InternalPolicyUi extends BaseEntity {
    private String contentText;
    private String negativeBtnText;
    private String positiveBtnText;
    private String titleText;

    private InternalPolicyUi(Builder builder) {
        this.titleText = builder.titleText;
        this.contentText = builder.contentText;
        this.positiveBtnText = builder.positiveBtnText;
        this.negativeBtnText = builder.negativeBtnText;
    }

    public String getTitleText() {
        return this.titleText;
    }

    public String getContentText() {
        return this.contentText;
    }

    public String getPositiveBtnText() {
        return this.positiveBtnText;
    }

    public String getNegativeBtnText() {
        return this.negativeBtnText;
    }

    public static class Builder extends BaseEntity {
        /* access modifiers changed from: private */
        public String contentText;
        /* access modifiers changed from: private */
        public String negativeBtnText;
        /* access modifiers changed from: private */
        public String positiveBtnText;
        /* access modifiers changed from: private */
        public String titleText;

        public InternalPolicyUi build() {
            return new InternalPolicyUi(this);
        }

        public Builder setTitleText(String str) {
            this.titleText = str;
            return this;
        }

        public Builder setContentText(String str) {
            this.contentText = str;
            return this;
        }

        public Builder setPositiveBtnText(String str) {
            this.positiveBtnText = str;
            return this;
        }

        public Builder setNegativeBtnText(String str) {
            this.negativeBtnText = str;
            return this;
        }
    }
}
