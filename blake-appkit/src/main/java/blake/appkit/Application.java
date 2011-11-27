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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application implements Serializable {
    private static final Logger log = Logger.getLogger(Application.class.toString());
    
    protected final PageResolver pages;
    protected final Configuration settings;
    
    public Application(Configuration settings) {
	this.settings = settings;
	pages = new PageResolver(settings.getPages(), settings.getContextPath());
    }
    
    public Response respond(Request request) {
        if (pages.isEmpty()) 
            return processPage(request, DefaultPage.class);
		
        // TODO Run pre processing code.
		
        Path location = pages.resolve(request);
        if (location == null)
            location = new Path(request.getPath(), NotFoundPage.class);
        Response response = processPage(request, location.getPageClass());
		
	// TODO Run post processing code.
		
        return response;
    }
	
    protected Response processPage(Request request, Class<? extends Page> pageClass) {
        try {
            Page page = pageClass.newInstance();
            return page.render(request);
        } catch(Exception e) {
            log.log(Level.SEVERE, "Failed to process the request!", e);
            return new ErrorPage().render(request);
        }
    }
    
    public Configuration getSettings() {
        return settings;
    }
	
    public PageResolver getPageResolver() {
        return pages;
    }
}