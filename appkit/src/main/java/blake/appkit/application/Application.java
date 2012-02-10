package blake.appkit.application;

import blake.appkit.http.PermanentRedirectResponse;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.resources.DefaultResource;
import blake.appkit.resources.Resource;
import blake.appkit.resources.errors.ErrorResource;
import blake.appkit.resources.errors.NotFoundResource;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The web application object.
 * 
 * This object is instantiated by the dispatcher and may persist over multiple
 * requests. On every request the application is called by the dispatcher to
 * respond to a request.
 * 
 * @author jfroehlich
 */
public class Application implements Serializable {

    private static final Logger log = Logger.getLogger(Application.class.toString());
    protected final Configuration settings;
    protected final Router pages;

    public Application(Configuration settings) {
        this.settings = settings;
        pages = new Router(settings.getLocations(), settings.getApplicationRoot());
    }
    
    public Response respond(Request request) {
        if (pages.isEmpty()) {
            return processRequest(request, DefaultResource.class, new Context());
        }

        // TODO Run pre processing code.

        Location location = pages.route(request.getPath());
        if (location == null) {
            if (!request.getPath().endsWith("/")) {
                String newPath = request.getPath() + "/";
                location = pages.route(newPath);
                if (location != null) {
                    return new PermanentRedirectResponse(newPath);
                }
            }
            Context ctx = new Context();
            location = new Location(request.getPath(), NotFoundResource.class, ctx);
        }
        Response response = processRequest(request, location.getResourceClass(), location.getContext());

        // TODO Run post processing code.

        return response;
    }

    /**
     * Hands the request and context to a resource and returns a response.
     * 
     * 
     * 
     * @param request   The request object from a dispatcher.
     * @param resourceClass The class of a resource object which will be instantiated.
     * @param context   The request context with parameters from preprocessors.
     * @return  The response object generated by the resource.
     */
    protected Response processRequest(Request request, Class<? extends Resource> resourceClass, Context context) {
        try {
            Constructor c = resourceClass.getConstructor(Configuration.class);
            Resource page = (Resource) c.newInstance(settings);
            return page.process(request, context);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to process the request!", e);

            // TODO Move the error message into the Errorpage
            StringBuilder builder = new StringBuilder();
            if (e.getMessage() != null) {
                builder.append(e.getMessage()).append("\n");
            }
            for (StackTraceElement element : e.getStackTrace()) {
                builder.append(element.getClassName()).append(".").append(element.getMethodName()).append(" (").append(element.getFileName()).append(":").append(element.getLineNumber()).append(")\n");
            }
            context.put(ErrorResource.ERROR_MESSAGE_KEY, builder.toString());
            return new ErrorResource(settings).process(request, context);
        }
    }
}