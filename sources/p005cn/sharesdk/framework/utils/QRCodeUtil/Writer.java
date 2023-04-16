package p005cn.sharesdk.framework.utils.QRCodeUtil;

import java.util.Map;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.Writer */
public interface Writer {
    C0785c encode(String str, C0776a aVar, int i, int i2) throws WriterException;

    C0785c encode(String str, C0776a aVar, int i, int i2, Map<C0787e, ?> map) throws WriterException;
}
