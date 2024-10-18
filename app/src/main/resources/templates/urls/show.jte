@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.urls.UrlPage
@param UrlPage page

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <h1>${page.getUrl().toString()}</h1>
        </div>
    `
)
