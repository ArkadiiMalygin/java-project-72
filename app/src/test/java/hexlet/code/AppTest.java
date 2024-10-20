package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlsRepository;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    Javalin app;

    @BeforeEach
    public final void setUp() throws SQLException, IOException, ClassNotFoundException {
        app = App.getApp();
    }

    @Test
    public void testIndexPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Your URl");
        });
    }

    @Test
    public void testShowAllPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("All urls");
        });
    }

    @Test
    public void testCreateUrlGarbageData() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "search?q";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(422);
            assertThat(response.body().string()).contains("Некорректный URL");
        });
    }

    @Test
    public void testCreateUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "name= https://www.google.com/search?q";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("google.com");
        });
    }

    @Test
    public void testUrlPage() throws SQLException {
        var url = new Url("https://www.google.com");
        UrlsRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testUrlPageNonexistent() throws SQLException {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/999999");
            assertThat(response.code()).isEqualTo(404);
        });
    }

    @Test
    public void testCheckUrl() throws SQLException {
        var url = new Url("https://www.google.com");
        UrlsRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/urls/" + url.getId() + "/checks");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("title");
        });
    }
}
