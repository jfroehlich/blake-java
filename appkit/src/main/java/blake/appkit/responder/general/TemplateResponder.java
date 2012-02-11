package blake.appkit.responder.general;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.responder.Responder;

/**
 *
 * @author jfroehlich
 */
public class TemplateResponder extends Responder {
    
    public static final String TEMPLATE_PATH = "template_path";
    
    public TemplateResponder(Configuration settings) {
        super(settings);
    }

    @Override
    public Response respond(Request request, Context context) throws Exception {
        if (!context.containsKey("template_path")) {
            throw new Exception("Could not find field '" + TEMPLATE_PATH + "' in context.");
        }
        
        String template = settings.getLoader().load(context.get(TEMPLATE_PATH));
        String str = settings.getRenderer().render(template, context);
        return new Response(str);
    }
}
