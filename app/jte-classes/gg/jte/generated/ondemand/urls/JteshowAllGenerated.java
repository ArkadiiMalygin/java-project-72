package gg.jte.generated.ondemand.urls;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.urls.UrlsPage;
public final class JteshowAllGenerated {
	public static final String JTE_NAME = "urls/showAll.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,6,6,12,12,16,16,16,19,19,19,19,19,19,19,19,19,19,19,19,22,22,22,25,25,29,29,29,29,29,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"mx-auto p-4 py-md-5\">\r\n        <div>\r\n            <h1>All urls</h1>\r\n\r\n            <table class=\"table table-striped\">\r\n                ");
				for (var entry : page.getUrlsWithCheckPairs().entrySet()) {
					jteOutput.writeContent("\r\n\r\n                    <tr>\r\n                        <td>\r\n                            ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(entry.getKey().getId());
					jteOutput.writeContent("\r\n                        </td>\r\n                        <td>\r\n                            <a");
					var __jte_html_attribute_0 = NamedRoutes.urlPath(entry.getKey().getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(entry.getKey().getName());
					jteOutput.writeContent("</a>\r\n                        </td>\r\n                        <td>\r\n                            ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(entry.getValue().getStatusCode());
					jteOutput.writeContent("\r\n                        </td>\r\n                    </tr>\r\n                ");
				}
				jteOutput.writeContent("\r\n            </table>\r\n        </div>\r\n    </div>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}