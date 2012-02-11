package blake.appkit.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Loads files from the current web archive or java archive.
 * 
 * This loader finds files in the current war or jar and is very useful to load
 * templates and static files included with the application like default
 * error pages.
 * 
 * @author jfroehlich
 * 
 */
public class ResourceLoader implements Loader {

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
        return loader.getResourceAsStream(path);
    }
    
}
