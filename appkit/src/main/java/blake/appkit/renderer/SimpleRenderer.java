package blake.appkit.renderer;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import java.util.Map.Entry;

public class SimpleRenderer extends Renderer {

    public SimpleRenderer(Configuration settings) {
        super(settings);
    }

    @Override
    public String render(String template, Context context) {
        return new Template(template).render(context);
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
