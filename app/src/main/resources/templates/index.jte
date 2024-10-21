@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.urls.BuildUrlPage
@param BuildUrlPage page

@template.layout.page(
    content = @`
        @if(page.getValidationErrors() != null)
            <div class="mb-3">
                <ul>
                    @for(var validator : page.getValidationErrors().values())
                        @for(var error : validator)
                            <li>${error.getMessage()}</li>
                        @endfor
                    @endfor
                </ul>
            </div>
            @elseif(page.getErrors() != null)
                <div class="mb-3">
                    <ul>
                        <li>${page.getErrors()}</li>
                    </ul>
                </div>
        @endif
        <div class="mx-auto p-4 py-md-5">
            <form action="${NamedRoutes.urlsPath()}" method="post">
                <div class="mb-3">
                    <label class="form-label">
                        Your URl
                        <input type="url" class="form-control" name="url" required value="${page.getName()}"/>
                    </label>
                </div>
                <input type="submit" class="btn btn-primary" value="Сохранить" />
            </form>
        </div>
    `
)