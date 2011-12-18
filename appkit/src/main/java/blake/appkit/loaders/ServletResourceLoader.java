package blake.appkit.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import javax.servlet.ServletContext;

public class ServletResourceLoader implements ResourceLoader {
    
    private final ServletContext context;
    
    public ServletResourceLoader(ServletContext context) {
        this.context = context;
    }

    @Override
    public String load(String path) throws IOException {
        String resource = "";
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
        return context.getResourceAsStream(path);
    }
    
}
