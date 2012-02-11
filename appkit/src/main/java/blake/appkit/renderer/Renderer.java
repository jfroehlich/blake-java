package blake.appkit.renderer;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import java.io.Serializable;

/**
 * The renderer used by appkit resources.
 * 
 * Renderes can implement a template language, or may be used as wrappers to
 * control a template engine.
 * 
 * A renderer is instantiated by the application and may be used for multiple
 * templates and resources. Templates may be cached but must not be be used to
 * store data since this is considered bad practice and may introduce 
 * security problems.
 * 
 * The configuration may contain additional settings for the renderer instance.
 * 
 * 
 * @author jfroehlich
 */
public abstract class Renderer implements Serializable {
    
    protected final Configuration settings;
    
    public Renderer(Configuration settings) {
        this.settings = settings;
    }
    
    /**
     * Finds a template, compiles it, renders it to a String and returns it.
     * 
     * @param template      The name of the template file. 
     * @param context       The context of the current page.
     * @return              The String returned by the Response.
     */
    public abstract String render(String template, Context context);
}
