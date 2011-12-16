package blake.appkit.pages.errors;

import blake.appkit.Configuration;
import blake.appkit.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.http.StatusCode;
import blake.appkit.pages.Page;
import blake.appkit.pages.Path;

public class NotFoundPage extends Page {
    
    public NotFoundPage(Configuration settings) {
        super(settings);
    }
    
    @Override
    public Response process(Request request, Context context) {
        StringBuilder builder = new StringBuilder();
        builder.append("404 File not found. \n\n");
        builder.append("Context path: ").append(settings.getContextPath()).append("\n");
        builder.append("Request path: ").append(request.getPath()).append("\n");
        for (Path path: settings.getPages()) {
            builder.append(path.getPattern().toString()).append(" \n");
        }
        return new Response(StatusCode.HTTP_404, builder.toString());
    }
}
