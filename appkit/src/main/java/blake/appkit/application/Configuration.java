package blake.appkit.application;

import blake.appkit.loaders.ArchiveFileLoader;
import blake.appkit.loaders.FileLoader;
import blake.appkit.renderer.Renderer;
import blake.appkit.renderer.SimpleRenderer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;

/**
 * 
 * 
 * @author jfroehlich
 */
public abstract class Configuration implements Serializable {
    
    public static final String CONTEXT_PATH_KEY = "context_path";

    private boolean debug = false;
    
    protected String contextPath = "/";
    protected String mediaURL = "";
    protected String staticURL = "";
    
    protected boolean appendSlash = true;
    
    protected List<Location> locations = null;
    protected Renderer renderer = null;
    protected FileLoader resourceLoader = new ArchiveFileLoader();
    
    public Configuration(ServletContext servletContext) {
        if (servletContext  != null) {
            String path = servletContext.getContextPath();
            contextPath = path.endsWith("/") ? path : String.format("%s/", path);
            // loader = new ServletResourceLoader(servletContext);
        }
        this.renderer = new SimpleRenderer(this);
        this.locations = new ArrayList<Location>();
    }

    public boolean isDebug() {
        return debug;
    }

    public String getApplicationRoot() {
        return contextPath;
    }

    public List<Location> getLocations() {
        if (locations == null) {
            locations = new ArrayList<Location>();
        }
        return locations;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public String getStaticURL() {
        return staticURL;
    }
    
    public Renderer getRenderer() {
        return renderer;
    }
    
    public FileLoader getResourceLoader() {
        return resourceLoader;
    }
    
    public boolean appendSlash() {
        return appendSlash;
    }
}
