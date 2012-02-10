package blake.appkit.resources;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import java.io.Serializable;

/**
 * This is processing a request and returns something beautiful.
 * 
 * A Resource represents an object, list, file, or something else on your
 * web site. Resources can be specific like a login form of an application, or
 * general like a list of stories, books, cars, etc.
 * 
 * Resources are independent of locations on your site. They can be mapped or
 * moved to whatever location you wish.
 * 
 * @author jfroehlich
 */
public abstract class Resource implements Serializable {
    
    protected final Configuration settings;
    
    public Resource(Configuration settings) {
        this.settings = settings;
    }
    
    public abstract Response process(Request request, Context context);
}
