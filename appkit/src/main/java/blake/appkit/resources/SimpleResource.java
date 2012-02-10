package blake.appkit.resources;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;

/**
 * A very basic resource for default pages.
 * 
 * This resource renders a context to a response using the default renderer
 * from the given configuration.
 * 
 * @author jfroehlich
 */
public class SimpleResource extends Resource {
    
    public SimpleResource(Configuration settings) {
        super(settings);
    }

    @Override
    public Response process(Request request, Context context) {
        String str = settings.getRenderer().render(context);
        return new Response(str);
    }
}
