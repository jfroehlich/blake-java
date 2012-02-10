package blake.appkit.dispatcher;

import blake.appkit.application.Application;
import blake.appkit.application.Configuration;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A dispatcher for JEE servlet containers.
 * 
 * This dispatcher creates the application for JEE5 containers like 
 * Apache Tomcat, Jetty or similar.
 * 
 * @author jfroehlich
 */
public class ApplicationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ApplicationServlet.class.toString());
    private static final String SETTINGS_PARAMETER_NAME = "settings";
    private Application application;

    @Override
    public void init() throws ServletException {
        String className = getServletConfig().getInitParameter(SETTINGS_PARAMETER_NAME);
        ClassLoader loader = this.getClass().getClassLoader();
        Configuration settings = null;
        
        try {
            Class<?> objClass = loader.loadClass(className);
            Constructor c = objClass.getConstructor(ServletContext.class);
            settings = (Configuration) c.newInstance(getServletContext());
        } catch (ClassNotFoundException e) {
            throw new Error("Settings class could not be found.", e);
        } catch (Exception e) {
            throw new Error("Could not instantiate settings class.", e);
        }
        
        log.info(String.format("Running application with settings: %s", className));
        application = new Application(settings);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Request request = new Request(req.getRequestURI());
        Response response = application.respond(request);

        resp.setStatus(response.getStatus().getCode());
        Map<String, String> headers = response.getHeaders();
        for (String header: headers.keySet()) {
            resp.addHeader(header, headers.get(header));
        }
        
        PrintWriter writer = resp.getWriter();
        writer.append(response.getContent());
        writer.flush();
    }
}
