package blake.appkit.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class ArchiveResourceLoader implements ResourceLoader {

    @Override
    public String load(String path) throws IOException {
        InputStream stream = getStream(path);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            int n = -1;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            stream.close();
        }
        return writer.toString();
    }

    @Override
    public InputStream getStream(String path) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("LOADER: " + loader);
        System.out.println("TEMPLATE PATH: " + path);
        System.out.println("TEMPLATE URL: " + loader.getResource(path));
        return loader.getResourceAsStream(path);
    }
    
}
