package blake.appkit.renderer;

import blake.appkit.Context;
import java.io.InputStream;
import java.util.Map.Entry;

public class SimpleRenderer implements Renderer {

    @Override
    public String render(Context context) {
        assert context.containsKey("template_path"):
                "The context must contain a field called 'template'!";
        
        Template template = new Template(context.get("template"));
        return template.render(context);
    }
    
    private class Template {
        private String str = "";

        public Template(String str) {
            this.str = str;
        }

        public String render(Context context) {
            StringBuilder builder = new StringBuilder(str);
            for (Entry<String, String> entry : context.entrySet()) {
                String pattern = "{{" + entry.getKey() + "}}";
                
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
