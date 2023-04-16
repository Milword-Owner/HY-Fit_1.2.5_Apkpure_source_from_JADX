package com.huayu.tzc.http;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u000b"}, mo21895d2 = {"com/huayu/tzc/http/Api$Companion$STRING$1", "Lcom/google/gson/TypeAdapter;", "", "read", "reader", "Lcom/google/gson/stream/JsonReader;", "write", "", "writer", "Lcom/google/gson/stream/JsonWriter;", "value", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: Api.kt */
public final class Api$Companion$STRING$1 extends TypeAdapter<String> {
    Api$Companion$STRING$1() {
    }

    @Nullable
    public String read(@NotNull JsonReader jsonReader) {
        Intrinsics.checkParameterIsNotNull(jsonReader, "reader");
        try {
            if (jsonReader.peek() != JsonToken.NULL) {
                return jsonReader.nextString();
            }
            jsonReader.nextNull();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void write(@NotNull JsonWriter jsonWriter, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(jsonWriter, "writer");
        if (str == null) {
            try {
                jsonWriter.nullValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            jsonWriter.value(str);
        }
    }
}
