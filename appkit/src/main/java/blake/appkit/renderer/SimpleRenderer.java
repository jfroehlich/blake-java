package blake.appkit.renderer;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.loaders.FileLoader;
import java.io.IOException;
import java.util.Map.Entry;

public class SimpleRenderer extends Renderer {

    public static final String TEMPLATE_PATH_KEY = "template_path";

    public SimpleRenderer(Configuration settings) {
        super(settings);
    }

    @Override
    public String render(Context context) {
        assert context.containsKey(TEMPLATE_PATH_KEY) :
                String.format("The context must contain a field called %s!", TEMPLATE_PATH_KEY);

        try {
            FileLoader loader = settings.getResourceLoader();
            String str = loader.load(context.get(TEMPLATE_PATH_KEY));
            return new Template(str).render(context);
        } catch (IOException ex) {
            throw new Error("Could not load template!");
        }
    }

    private class Template {

        private String str = "";

        public Template(String str) {
            this.str = str;
        }

        public String render(Context context) {
            StringBuilder builder = new StringBuilder(str);
            for (Entry<String, String> entry : context.entrySet()) {
                String pattern = "{{ " + entry.getKey() + " }}";

                int start = -1;
                while ((start = builder.indexOf(pattern)) != -1) {
                    String value = entry.getValue().toString();
                    builder.replace(start, start + pattern.length(), value);
                }
            }
            return builder.toString();
        }
    }
}
