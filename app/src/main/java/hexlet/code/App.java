package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import hexlet.code.controller.UrlsController;
import hexlet.code.repository.BaseRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class App {

    public static final String DEBUG = "true";

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

    private static String readResourceFile(String fileName) throws IOException {
        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    public static Javalin getApp() throws IOException, SQLException {
        var sql = readResourceFile("schema.sql");
        if (DEBUG.equals("true")) {
            var hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl("jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");

            var dataSource = new HikariDataSource(hikariConfig);

            try (var connection = dataSource.getConnection();
                 var statement = connection.createStatement()) {
                statement.execute(sql);
            }

            BaseRepository.dataSource = dataSource;
        } else {

            HikariConfig config = new HikariConfig();

            config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
            config.addDataSourceProperty("serverName", "xxxxxxxxxxxxxxxxxx");
            config.addDataSourceProperty("portNumber", "5432");
            config.addDataSourceProperty("databaseName", "xxxxxxxxxx");
            config.addDataSourceProperty("user", "xxxxxxxxxxxxxxxxxxxxxxxxxx");
            config.addDataSourceProperty("password", "xxxxxxxxxxxxx");

            // postgress configuration for Hikari
            HikariDataSource ds = new HikariDataSource(config);

            BaseRepository.dataSource = ds;

        }


        //log.info(sql);


        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

        app.get(NamedRoutes.rootPath(), UrlsController::build);

        app.post(NamedRoutes.urlsPath(), UrlsController::create);

        app.get(NamedRoutes.urlsPath(), UrlsController::showAll);

        app.get(NamedRoutes.urlPath("{id}"), UrlsController::show);

        app.post(NamedRoutes.urlPathChecks("{id}"), UrlsController::check);

        return app;
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Javalin app = getApp();
        app.start(getPort());
    }
}
