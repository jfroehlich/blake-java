package blake.appkit.responder;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.responder.general.TemplateResponder;

/**
 * Responds with a default welcome page.
 * 
 * This responder is used to render a welcome page when the web application 
 * has no URL mappings.
 *
 */
public class DefaultResponder extends TemplateResponder {
        
    public DefaultResponder(Configuration settings) {
        super(settings);
    }

    @Override
    public Response respond(Request request, Context context) throws Exception{
        context.put(TEMPLATE_PATH, "blake/templates/default.html");
        return super.respond(request, context);
    }
}
