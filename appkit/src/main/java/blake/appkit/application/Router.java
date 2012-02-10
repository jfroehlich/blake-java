package blake.appkit.application;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * 
 * 
 * @author jfroehlich
 */
public class Router extends ArrayList<Location> {

    private String contextPath;

    public Router(List<Location> locations, String contextPath) {
        this.contextPath = contextPath == null ? "/" : contextPath;
        if (contextPath.isEmpty()) {
            throw new Error("Context path must not be empty. (default: '/')");
        }
        addAll(locations);
    }

    public Location route(String path) {
        if (! path.startsWith(contextPath)) {
            return null;
        }
        path = path.substring(contextPath.length(), path.length());
        
        for (Location location : this) {
            Matcher matcher = location.getPattern().matcher(path);
            if (matcher.matches()) {
                return location;
            }
        }
        return null;
    }
}
