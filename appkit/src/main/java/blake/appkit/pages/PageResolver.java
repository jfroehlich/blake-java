package blake.appkit.pages;

import blake.appkit.http.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class PageResolver extends ArrayList<Path> {

    private final String contextPath;

    public PageResolver(List<Path> locations, String contextPath) {
        if (contextPath == null || contextPath.isEmpty()) {
            throw new Error("Context path must not be empty.");
        }
        this.contextPath = contextPath;
        addAll(locations);
    }

    public Path resolve(Request request) {
        String path = request.getPath();
        if (!path.startsWith(contextPath)) {
            return null;
        }
        path = path.substring(contextPath.length(), path.length());

        for (Path location : this) {
            Matcher matcher = location.getPattern().matcher(path);
            if (matcher.matches()) {
                return location;
            }
        }
        return null;
    }
}
