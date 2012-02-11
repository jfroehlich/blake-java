package blake.appkit.responder;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import java.io.Serializable;

/**
 * Defines a base class for objects that respond to request events.
 * 
 * Usually responders handle requests for pages on web sites or resources of 
 * an API.
 * 
 * 
 * @author jfroehlich
 */
public abstract class Responder implements Serializable {
    
    protected final Configuration settings;
    
    public Responder(Configuration settings) {
        this.settings = settings;
    }
    
    public abstract Response process(Request request, Context context);
}
