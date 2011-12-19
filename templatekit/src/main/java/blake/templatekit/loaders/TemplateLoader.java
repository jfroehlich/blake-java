package blake.templatekit.loaders;

import blake.templatekit.Template;

public interface TemplateLoader {
    
    public Template loadTemplate(String templateName);
    
    public Template loadTemplate(String ... templateNames);
    
}
