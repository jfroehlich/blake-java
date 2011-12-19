package blake.templatekit;

import java.io.Serializable;
import java.util.Map;

public class Template implements Serializable {
    
    private String template;
    
    public Template(String template) {
        this.template = template;
    }
    
    public String render(Map<String, String> context) {
        return "";
    }
}
