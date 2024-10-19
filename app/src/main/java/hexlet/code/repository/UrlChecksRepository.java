package hexlet.code.repository;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import hexlet.code.model.Url;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class UrlChecksRepository extends BaseRepository {
    public static void save(HttpResponse<JsonNode> jsonResponse, Long urlId) throws SQLException {
        var sql = "INSERT INTO url_checks (url_id, status_code, h1, title, description, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, urlId);
            preparedStatement.setLong(2, jsonResponse.getStatus());
            var response = jsonResponse.getBody().getObject();
            preparedStatement.setString(3, response.getString("h1"));
            preparedStatement.setString(4, response.getString("title"));
            preparedStatement.setString(5, response.getString("description"));
            var createdAt = Timestamp.valueOf(now());
            preparedStatement.setTimestamp(6, createdAt);

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                System.out.println(urlId + " -> " + generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB has not returned an id after saving an entity");
            }
        }
    }
}
