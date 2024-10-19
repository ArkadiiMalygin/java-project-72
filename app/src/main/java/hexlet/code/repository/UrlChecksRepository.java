package hexlet.code.repository;


import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.LocalDateTime.now;

public class UrlChecksRepository extends BaseRepository {
    public static void save(UrlCheck check) throws SQLException {
        var sql = "INSERT INTO url_checks (url_id, status_code, h1, title, description, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, check.getUrlId());
            preparedStatement.setLong(2, check.getStatusCode());
            preparedStatement.setString(3, check.getH1());
            preparedStatement.setString(4, check.getTitle());
            preparedStatement.setString(5, check.getDescription());
            var createdAt = Timestamp.valueOf(now());
            preparedStatement.setTimestamp(6, createdAt);

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                System.out.println(check.getUrlId() + " -> " + generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB has not returned an id after saving an entity");
            }
        }
    }

    public static Optional<UrlCheck> find(Long urlId) throws SQLException {
        var sql = "SELECT * FROM url_checks WHERE url_id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, urlId);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var id = resultSet.getLong("id");
                var statusCode = resultSet.getInt("status_code");
                var h1 = resultSet.getString("h1");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at");

                var urlCheck = new UrlCheck(id, statusCode, title, h1, description, urlId, createdAt);

                return Optional.of(urlCheck);
            }
            return Optional.empty();
        }
    }
}
