package top.zibin.luban;

import java.io.IOException;
import java.io.InputStream;

public interface InputStreamProvider {
    String getPath();

    InputStream open() throws IOException;
}
