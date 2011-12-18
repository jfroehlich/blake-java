package blake.appkit.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public interface ResourceLoader extends Serializable {
    
    public String load(String path) throws IOException ;
    
    public InputStream getStream(String path);
    
}
