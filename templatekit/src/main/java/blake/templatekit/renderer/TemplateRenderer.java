package blake.templatekit.renderer;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.renderer.Renderer;

public class TemplateRenderer extends Renderer {

    TemplateRenderer(Configuration settings) {
        super(settings);
    }
    
    @Override
    public String render(Context context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
