package gg.jte.generated.ondemand;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,2,2,5,6,7,8,9,10,11,11,11,11,11,11,11,11};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <h1 class=\"text-body-emphasis\">Hello World!</h1>\r\n        <p>Javalin + jte</p>\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n");
				jteOutput.writeContent("\r\n    ");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
