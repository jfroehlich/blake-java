package blake.appkit.pages;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Path implements Serializable {

    protected final Pattern pattern;
    protected final Class<? extends Page> pageClass;

    public Path(String pattern, Class<? extends Page> pageClass) {
        this.pattern = Pattern.compile(pattern);
        this.pageClass = pageClass;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Class<? extends Page> getPageClass() {
        return pageClass;
    }
}
