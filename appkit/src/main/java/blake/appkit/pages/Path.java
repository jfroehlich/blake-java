package blake.appkit.pages;

import blake.appkit.application.Context;
import java.io.Serializable;
import java.util.regex.Pattern;

public class Path implements Serializable {

    protected final Pattern pattern;
    protected final Class<? extends Page> pageClass;
    protected final Context context;

    public Path(String pattern, Class<? extends Page> pageClass, Context context) {
        this.pattern = Pattern.compile(pattern);
        this.pageClass = pageClass;
        this.context = context;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Class<? extends Page> getPageClass() {
        return pageClass;
    }
    
    public Context getContext() {
        return context;
    }
}
