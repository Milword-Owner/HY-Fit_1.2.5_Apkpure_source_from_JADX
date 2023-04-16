package androidx.core.p003os;

import androidx.annotation.Nullable;

/* renamed from: androidx.core.os.OperationCanceledException */
public class OperationCanceledException extends RuntimeException {
    public OperationCanceledException() {
        this((String) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OperationCanceledException(@Nullable String str) {
        super(str == null ? "The operation has been canceled." : str);
    }
}
