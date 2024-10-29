package gg.jte.generated.ondemand.urls;
import hexlet.code.model.UrlCheck;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.urls.UrlPage;
public final class JteshowGenerated {
	public static final String JTE_NAME = "urls/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,5,5,7,7,9,9,9,11,11,13,13,13,15,15,17,17,17,17,17,17,17,17,17,21,21,21,22,22,22,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <div class=\"mx-auto p-4 py-md-5\">\r\n            <h1>");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().toString());
				jteOutput.writeContent("</h1>\r\n        </div>\r\n    ");
				for (UrlCheck inst : page.getUrlCheck()) {
					jteOutput.writeContent("\r\n        <div class=\"mx-auto p-4 py-md-5\">\r\n            <h1>");
					jteOutput.setContext("h1", null);
					jteOutput.writeUserContent(inst.toString());
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
