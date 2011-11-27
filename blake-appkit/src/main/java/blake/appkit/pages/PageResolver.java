package blake.appkit.pages;

import blake.appkit.http.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class PageResolver extends ArrayList<Path> {

    private final String contextPath;

    public PageResolver(List<List<Object>> locations, String contextPath) {
        if (contextPath == null || contextPath.isEmpty()) {
            throw new Error("Context path must not be empty.");
        }
        this.contextPath = contextPath;

        for (List<Object> location : locations) {
            if (location.size() != 3) {
                throw new Error("Path definition must have 3 elements.");
            }

            String pattern = (String) location.get(0);
            Class<?> pageClass = (Class<?>) location.get(1);
            try {
                Page page = (Page) pageClass.newInstance();
                add(new Path(pattern, page.getClass()));
            } catch (InstantiationException e) {
                throw new Error("Page could not be instantiated!");
            } catch (IllegalAccessException e) {
                throw new Error("No permission to access this page class.");
            }
        }
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
