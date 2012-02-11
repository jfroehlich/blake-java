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
    
    public TemplateResponder(Configuration settings) {
        super(settings);
    }

    @Override
    public Response process(Request request, Context context) {
        String str = settings.getRenderer().render(context);
        return new Response(str);
    }
}
