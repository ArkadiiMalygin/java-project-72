package gg.jte.generated.ondemand;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.urls.BuildUrlPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,6,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,26,26,26,26,26,26,26,26,26,30,30,30,30,30,30,30,30,30,36,36,36,36,36,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, BuildUrlPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n        <div class=\"mx-auto p-4 py-md-5\">\r\n            <form");
				var __jte_html_attribute_0 = NamedRoutes.urlsPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\">\r\n                <div class=\"mb-3\">\r\n                    <label class=\"form-label\">\r\n                        Your URl\r\n                        <input type=\"url\" class=\"form-control\" name=\"name\" required");
				var __jte_html_attribute_1 = page.getName();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent("/>\r\n                    </label>\r\n                </div>\r\n                <input type=\"submit\" class=\"btn btn-primary\" value=\"Сохранить\" />\r\n            </form>\r\n        </div>\r\n    ");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		BuildUrlPage page = (BuildUrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
