package blake.appkit.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Interface to load files from anywhere.
 * 
 * @author jfroehlich
 */
public interface FileLoader extends Serializable {
    
    /**
     * Loads a file and returns it as a string.
     * 
     * This is to load a template from somewhere.
     * 
     * @param path  The path to the file.
     * @return      The file as a string object.
     * @throws IOException
     */
    public String load(String path) throws IOException ;
    
    /**
     * Load the file as stream object to read images, excel files, pdf files
     * or anything else.
     * 
     * @param path  The path to the file.
     * @return      The stream object to the file.
     */
    public InputStream getStream(String path);    
}
