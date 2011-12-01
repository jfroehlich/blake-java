package blake.appkit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Configuration implements Serializable {

    private boolean debug = false;
    private String contextPath = "/";
    private List<List<Object>> pages = null;
    private String mediaURL = "";
    private String staticURL = "";

    public Configuration(Map<String, String> context) {
        if (context.containsKey("contextPath")) {
            contextPath = context.get("contextPath");
        }
        this.pages = new ArrayList<List<Object>>();
    }

    public boolean isDebug() {
        return debug;
    }

    public String getContextPath() {
        return contextPath;
    }

    public List<List<Object>> getPages() {
        if (pages == null) {
            pages = new ArrayList<List<Object>>();
        }
        return pages;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public String getStaticURL() {
        return staticURL;
    }
}
