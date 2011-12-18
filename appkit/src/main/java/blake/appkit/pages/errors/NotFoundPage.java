package blake.appkit.pages.errors;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.pages.Path;
import blake.appkit.pages.SimplePage;

public class NotFoundPage extends SimplePage {
    
    public NotFoundPage(Configuration settings) {
        super(settings);
    }
    
    @Override
    public Response process(Request request, Context context) {
        context.put("template_path", "blake/templates/404.html");
        context.put("message", "The requested page could not be found.");
        context.put("application_root", settings.getApplicationRoot());
        context.put("request_path", request.getPath());
        
        StringBuilder pages = new StringBuilder();
        for (Path path: settings.getPages()) {
            pages.append("<li><code>")
                    .append(path.getPattern().toString())
                    .append("</code>: ")
                    .append(path.getPageClass().toString())
                    .append("</li>");
        }
        context.put("pages", pages.toString());
        
        return super.process(request, context);
    }
}
