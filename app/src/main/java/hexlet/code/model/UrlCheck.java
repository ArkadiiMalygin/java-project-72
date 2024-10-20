package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UrlCheck {

    private Long id;

    private int statusCode;

    private String title;

    private String h1;

    private String description;

    private Long urlId;

    private Timestamp createdAt;

    @Override
    public String toString() {
        return "Check" +
                "id=" + id +
                ", statusCode=" + statusCode +
                ", title='" + title + '\'' +
                ", h1='" + h1 + '\'' +
                ", description='" + description + '\'' +
                ", urlId=" + urlId +
                ", createdAt=" + createdAt;
    }
}
