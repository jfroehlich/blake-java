package blake.appkit;

import blake.appkit.pages.Path;
import blake.appkit.renderer.Renderer;
import blake.appkit.renderer.SimpleRenderer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;

public abstract class Configuration implements Serializable {
    
    public static final String CONTEXT_PATH_KEY = "context_path";

    private boolean debug = false;
    private String contextPath = "/";
    protected List<Path> pages = null;
    private String mediaURL = "";
    private String staticURL = "";
    private Renderer renderer = new SimpleRenderer();
    
    public Configuration(ServletContext servletContext) {
        if (servletContext  != null) {
            String path = servletContext.getContextPath();
            contextPath = path.endsWith("/") ? path : String.format("%s/", path);
        }
        this.pages = new ArrayList<Path>();
    }

    public boolean isDebug() {
        return debug;
    }

    public String getContextPath() {
        return contextPath;
    }

    public List<Path> getPages() {
        if (pages == null) {
            pages = new ArrayList<Path>();
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
}
