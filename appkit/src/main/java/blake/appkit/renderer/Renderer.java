package blake.appkit.renderer;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import java.io.Serializable;

public abstract class Renderer implements Serializable {
    
    protected final Configuration settings;
    
    public Renderer(Configuration settings) {
        this.settings = settings;
    }
    
    /**
     * Finds a template, compiles it, renders it to a String and returns it.
     * 
     * @param template  The name of the template file. 
     * @param context       The context of the current page.
     * @return              The String returned by the Response.
     */
    public abstract String render(Context context);
}
