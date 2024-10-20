@import hexlet.code.model.UrlCheck
@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.urls.UrlPage
@param UrlPage page

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <h1>${page.getUrl().toString()}</h1>
        </div>
    @for(UrlCheck inst : page.getUrlCheck())
        <div class="mx-auto p-4 py-md-5">
            <h1>${inst.toString()}</h1>
        </div>
    @endfor
        <div class="mx-auto p-4 py-md-5">
            <form action="${NamedRoutes.urlPathChecks(page.getUrl().getId())}" method="post">
                <input type="submit" class="btn btn-primary" value="Check URl" />
            </form>
        </div>
    `
)
