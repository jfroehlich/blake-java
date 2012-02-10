package blake.appkit.application;

import blake.appkit.loaders.ArchiveResourceLoader;
import blake.appkit.loaders.ResourceLoader;
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
    
    protected List<Location> pages = null;
    protected Renderer renderer = null;
    protected ResourceLoader resourceLoader = new ArchiveResourceLoader();
    
    public Configuration(ServletContext servletContext) {
        if (servletContext  != null) {
            String path = servletContext.getContextPath();
            contextPath = path.endsWith("/") ? path : String.format("%s/", path);
            // loader = new ServletResourceLoader(servletContext);
        }
        this.renderer = new SimpleRenderer(this);
        this.pages = new ArrayList<Location>();
    }

    public boolean isDebug() {
        return debug;
    }

    public String getApplicationRoot() {
        return contextPath;
    }

    public List<Location> getPages() {
        if (pages == null) {
            pages = new ArrayList<Location>();
        }
        return pages;
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
    
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
    
    public boolean appendSlash() {
        return appendSlash;
    }
}
