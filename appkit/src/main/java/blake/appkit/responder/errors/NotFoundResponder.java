package blake.appkit.responder.errors;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.application.Location;
import blake.appkit.responder.general.TemplateResponder;

/**
 * Default resource for unsuccessful requests.
 * 
 * This resource is returned by default when a requested location can not be
 * resolved by the router. It responds with a HTTP 404 status (File not found) 
 * and a optional custom message.
 * 
 * @author jfroehlich
 */
public class NotFoundResponder extends TemplateResponder {
    
    public NotFoundResponder(Configuration settings) {
        super(settings);
    }
    
    @Override
    public Response respond(Request request, Context context) throws Exception {
        context.put(TEMPLATE_PATH, "blake/templates/404.html");
        context.put("message", "The requested page could not be found.");
        context.put("application_root", settings.getApplicationRoot());
        context.put("request_path", request.getPath());
        
        StringBuilder pages = new StringBuilder();
        for (Location path: settings.getLocations()) {
            pages.append("<li><code>")
                    .append(path.getPattern().toString())
                    .append("</code>: ")
                    .append(path.getResponderClass().toString())
                    .append("</li>");
        }
        context.put("pages", pages.toString());
        return super.respond(request, context);
    }
}
