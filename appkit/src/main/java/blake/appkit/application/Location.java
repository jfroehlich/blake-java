package blake.appkit.application;

import blake.appkit.responder.Responder;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Maps a resource object to a path.
 * 
 * @author jfroehlich
 */
public class Location implements Serializable {

    protected final Pattern pattern;
    protected final Class<? extends Responder> pageClass;
    protected final Context context;

    public Location(String pattern, Class<? extends Responder> pageClass, Context context) {
        this.pattern = Pattern.compile(pattern);
        this.pageClass = pageClass;
        this.context = context;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Class<? extends Responder> getResourceClass() {
        return pageClass;
    }
    
    public Context getContext() {
        return context;
    }
}
