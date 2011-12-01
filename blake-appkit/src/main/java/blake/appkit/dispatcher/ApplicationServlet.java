package blake.appkit.dispatcher;

import blake.appkit.Application;
import blake.appkit.Configuration;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplicationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ApplicationServlet.class.toString());
    private static final String SETTINGS_PARAMETER_NAME = "settings";
    private Application application;

    @Override
    public void init()
            throws ServletException {

        Map<String, String> context = new HashMap<String, String>();
        context.put("contextPath", getServletContext().getContextPath());

        String className = getServletConfig().getInitParameter(SETTINGS_PARAMETER_NAME);
        ClassLoader loader = this.getClass().getClassLoader();
        Configuration settings = null;
        try {
            Class<?> objClass = loader.loadClass(className);
            Constructor c = objClass.getConstructor(Map.class);
            settings = (Configuration) c.newInstance(context);
        } catch (ClassNotFoundException e) {
            throw new Error("Settings class could not be found.", e);
        } catch (Exception e) {
            throw new Error("Could not instantiate settings class.", e);
        }
        log.info("Running application with settings: " + className);
        application = new Application(settings);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Request request = new Request(req.getRequestURI());
        Response response = application.respond(request);

        resp.setStatus(response.getStatus().getCode());
        PrintWriter writer = resp.getWriter();
        writer.append(response.getBody());
        writer.flush();
    }
}
