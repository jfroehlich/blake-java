package blake.appkit;

import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.pages.DefaultPage;
import blake.appkit.pages.Page;
import blake.appkit.pages.PageResolver;
import blake.appkit.pages.Path;
import blake.appkit.pages.errors.ErrorPage;
import blake.appkit.pages.errors.NotFoundPage;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application implements Serializable {
    private static final Logger log = Logger.getLogger(Application.class.toString());
    protected final Configuration settings;
    protected final PageResolver pages;
    
    public Application(Configuration settings) {
        this.settings = settings;
        pages = new PageResolver(settings.getPages(), settings.getContextPath());
    }

    public Response respond(Request request) {
        if (pages.isEmpty()) {
            return processPage(request, DefaultPage.class, new Context());
        }

        // TODO Run pre processing code.

        Path location = pages.resolve(request);
        if (location == null) {
            Context ctx = new Context();
            location = new Path(request.getPath(), NotFoundPage.class, ctx);
        }
        Response response = processPage(request, location.getPageClass(), location.getContext());

        // TODO Run post processing code.

        return response;
    }

    protected Response processPage(Request request, Class<? extends Page> pageClass, Context context) {
        try {
            Constructor c = pageClass.getConstructor(Configuration.class);
            Page page = (Page) c.newInstance(settings);
            return page.process(request, context);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to process the request!", e);
            
            // TODO Move the error message into the Errorpage
            StringBuilder builder = new StringBuilder();
            if (e.getMessage() != null) {
                builder.append(e.getMessage()).append("\n");
            }
            for (StackTraceElement element: e.getStackTrace()) {
                builder.append(element.getClassName())
                        .append(".").append(element.getMethodName())
                        .append(" (").append(element.getFileName())
                        .append(":").append(element.getLineNumber())
                        .append(")\n");
            }
            context.put(ErrorPage.ERROR_MESSAGE_KEY, builder.toString());
            return new ErrorPage(settings).process(request, context);
        }
    }
}