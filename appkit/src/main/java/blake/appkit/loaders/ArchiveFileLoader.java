package blake.appkit.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Loads files from the current web application.
 * 
 * This file loader can be used to load resources like templates from the
 * current web application.
 * 
 * @author jfroehlich
 * 
 * TODO Check to load files only from inside a specified location root.
 * TODO Test if this is the prevered way to load files inside servlet containers.
 */
public class ArchiveFileLoader implements FileLoader {

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
