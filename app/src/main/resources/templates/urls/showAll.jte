@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.urls.UrlsPage
@param UrlsPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <div>
            <h1>All urls</h1>

            <table class="table table-striped">
                @for(var entry : page.getUrlsWithCheckPairs().entrySet())

                    <tr>
                        <td>
                            ${entry.getKey().getId()}
                        </td>
                        <td>
                            <a href="${NamedRoutes.urlPath(entry.getKey().getId())}">${entry.getKey().getName()}</a>
                        </td>
                        <td>
                            ${entry.getValue().getStatusCode()}
                        </td>
                    </tr>
                @endfor
            </table>
        </div>
    </div>
`
)