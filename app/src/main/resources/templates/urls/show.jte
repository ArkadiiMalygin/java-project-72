@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.urls.UrlPage
@param UrlPage page

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <h1>${page.getUrl().toString()}</h1>
        </div>
    @if(page.getUrlCheck().isPresent())
        <div class="mx-auto p-4 py-md-5">
            <h1>${page.getUrlCheck().toString()}</h1>
        </div>
    @endif
        <div class="mx-auto p-4 py-md-5">
            <form action="${NamedRoutes.urlPathChecks(page.getUrl().getId())}" method="post">
                <input type="submit" class="btn btn-primary" value="Check URl" />
            </form>
        </div>
    `
)
