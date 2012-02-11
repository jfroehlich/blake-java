package blake.appkit.application;

import blake.appkit.responder.Responder;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Maps a responder object to a path.
 * 
 * @author jfroehlich
 */
public class Location implements Serializable {

    protected final Pattern pattern;
    protected final Class<? extends Responder> responderClass;
    protected final Context context;

    public Location(String pattern, Class<? extends Responder> responderClass, Context context) {
        this.pattern = Pattern.compile(pattern);
        this.responderClass = responderClass;
        this.context = context;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Class<? extends Responder> getResponderClass() {
        return responderClass;
    }
    
    public Context getContext() {
        return context;
    }
}
