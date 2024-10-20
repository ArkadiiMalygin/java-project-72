package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import io.javalin.validation.ValidationError;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class BuildUrlPage extends BasePage {
    private String name;

    private Map<String, List<ValidationError<Object>>> validationErrors;

    private String errors;

    public BuildUrlPage(String name) {
        this.name = name;
    }

}
