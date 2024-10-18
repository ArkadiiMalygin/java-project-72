package gg.jte.generated.ondemand;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,2,2,4,4,7,7,8,8,9,9,9,10,10,11,11,14,14,16,16,16,16,16,16,16,16,16,20,20,20,20,20,20,20,20,20,26,26,26,26,26,26,26,26,26,32,32,32,32,32,32,32,32};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <h1 class=\"text-body-emphasis\">Hello World!</h1>\r\n        ");
				if (page.getErrors() != null) {
					jteOutput.writeContent("\r\n            <div class=\"mb-3\">\r\n                <ul>\r\n                    ");
					for (var validator : page.getErrors().values()) {
						jteOutput.writeContent("\r\n                        ");
						for (var error : validator) {
							jteOutput.writeContent("\r\n                            <li>");
							jteOutput.setContext("li", null);
							jteOutput.writeUserContent(error.getMessage());
							jteOutput.writeContent("</li>\r\n                        ");
						}
						jteOutput.writeContent("\r\n                    ");
					}
					jteOutput.writeContent("\r\n                </ul>\r\n            </div>\r\n        ");
				}
				jteOutput.writeContent("\r\n        <div class=\"mx-auto p-4 py-md-5\">\r\n            <form");
				var __jte_html_attribute_0 = NamedRoutes.productsPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\">\r\n                <div class=\"mb-3\">\r\n                    <label class=\"form-label\">\r\n                        Название\r\n                        <input type=\"text\" class=\"form-control\" name=\"title\"");
				var __jte_html_attribute_1 = page.getTitle();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" />\r\n                    </label>\r\n                </div>\r\n                <div class=\"mb-3\">\r\n                    <label class=\"form-label\">\r\n                        Цена\r\n                        <input type=\"text\" class=\"form-control\" name=\"price\"");
				var __jte_html_attribute_2 = page.getPrice();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_2);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" />\r\n                    </label>\r\n                </div>\r\n                <input type=\"submit\" class=\"btn btn-primary\" value=\"Сохранить\" />\r\n            </form>\r\n        </div>\r\n    ");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
