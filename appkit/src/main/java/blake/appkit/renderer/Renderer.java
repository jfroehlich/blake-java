package blake.appkit.renderer;

import blake.appkit.Context;
import java.io.Serializable;

public interface Renderer extends Serializable {

    /**
     * Finds a template, compiles it, renders it to a String and returns it.
     * 
     * @param template  The name of the template file. 
     * @param context       The context of the current page.
     * @return              The String returned by the Response.
     */
    public String render(Context context);
}
