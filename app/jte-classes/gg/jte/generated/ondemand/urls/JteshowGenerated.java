package gg.jte.generated.ondemand.urls;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.urls.UrlPage;
public final class JteshowGenerated {
	public static final String JTE_NAME = "urls/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,6,6,8,8,8,10,10,12,12,12,14,14,16,16,16,16,16,16,16,16,16,20,20,20,21,21,21,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <div class=\"mx-auto p-4 py-md-5\">\r\n            <h1>");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().toString());
				jteOutput.writeContent("</h1>\r\n        </div>\r\n    ");
				if (page.getUrlCheck().isPresent()) {
					jteOutput.writeContent("\r\n        <div class=\"mx-auto p-4 py-md-5\">\r\n            <h1>");
					jteOutput.setContext("h1", null);
					jteOutput.writeUserContent(page.getUrlCheck().toString());
					jteOutput.writeContent("</h1>\r\n        </div>\r\n    ");
				}
				jteOutput.writeContent("\r\n        <div class=\"mx-auto p-4 py-md-5\">\r\n            <form");
				var __jte_html_attribute_0 = NamedRoutes.urlPathChecks(page.getUrl().getId());
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\">\r\n                <input type=\"submit\" class=\"btn btn-primary\" value=\"Check URl\" />\r\n            </form>\r\n        </div>\r\n    ");
			}
		});
		jteOutput.writeContent("\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
