@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.urls.UrlsPage
@param UrlsPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <div>
            <h1>All urls</h1>

            <table class="table table-striped">
                @for(var url : page.getUrls())
                    <tr>
                        <td>
                            ${url.getId()}
                        </td>
                        <td>
                            ${url.getName()}
                        </td>
                    </tr>
                @endfor
            </table>
        </div>
    </div>
`
)