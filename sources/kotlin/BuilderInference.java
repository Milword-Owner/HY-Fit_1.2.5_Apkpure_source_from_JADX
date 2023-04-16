package kotlin;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;
import kotlin.experimental.ExperimentalTypeInference;

@SinceKotlin(version = "1.3")
@Target(allowedTargets = {AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY})
@Retention(AnnotationRetention.BINARY)
@ExperimentalTypeInference
@java.lang.annotation.Target({ElementType.METHOD, ElementType.PARAMETER})
@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, mo21895d2 = {"Lkotlin/BuilderInference;", "", "kotlin-stdlib"}, mo21896k = 1, mo21897mv = {1, 1, 16})
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
/* compiled from: Inference.kt */
public @interface BuilderInference {
}
