package kotlin.p016io;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, mo21895d2 = {"<anonymous>", "Ljava/nio/charset/CharsetDecoder;", "kotlin.jvm.PlatformType", "invoke"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: kotlin.io.ConsoleKt$decoder$2 */
/* compiled from: Console.kt */
final class ConsoleKt$decoder$2 extends Lambda implements Function0<CharsetDecoder> {
    public static final ConsoleKt$decoder$2 INSTANCE = new ConsoleKt$decoder$2();

    ConsoleKt$decoder$2() {
        super(0);
    }

    public final CharsetDecoder invoke() {
        return Charset.defaultCharset().newDecoder();
    }
}
