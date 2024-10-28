package hexlet.code.controller;

import hexlet.code.dto.urls.BuildUrlPage;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlChecksRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {

    public static final int LIMIT = 250;

    public static void build(Context ctx) {
        var page = new BuildUrlPage();
        ctx.render("index.jte", model("page", page));
    }

    public static void create(Context ctx) {
        try {
            var name = ctx.formParam("url");
            var urlData = new URL(name);
            var uriData = urlData.toURI();

            var normalizedUrl = getNormalizedURL(urlData);
            var url = new Url(normalizedUrl);
            UrlsRepository.save(url);
            ctx.redirect(NamedRoutes.urlsPath());


        } catch (ValidationException e) {
            var name = ctx.formParam("url");
            var page = new BuildUrlPage(name);
            page.setValidationErrors(e.getErrors());
            ctx.render("index.jte", model("page", page)).status(422);
        } catch (URISyntaxException | MalformedURLException e) {
            var name = ctx.formParam("url");
            var page = new BuildUrlPage(name);
            page.setErrors("Некорректный URL");
            ctx.render("index.jte", model("page", page)).status(422);
        } catch (SQLException e) {
            var name = ctx.formParam("url");
            var page = new BuildUrlPage(name);
            page.setErrors("DB has not process ur ULR");
            ctx.render("index.jte", model("page", page)).status(418);
        }
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlsRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));
        var urlCheck = UrlChecksRepository.find(id);

        var page = new UrlPage(url, urlCheck);
        ctx.render("urls/show.jte", model("page", page));
    }

    public static void showAll(Context ctx) throws SQLException {
        var urls = UrlsRepository.getEntities();
        var page = new UrlsPage(urls);
        ctx.render("urls/showAll.jte", model("page", page));
    }

    public static void check(Context ctx) throws SQLException, IOException {
        var urlId = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlsRepository.find(urlId)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));
//        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
//        System.out.println(Unirest.get("http://localhost:7070/urls/").asJson().getStatus());
//        System.out.println(Unirest.get("http://localhost:7070/urls/").asJson().getBody());
//        System.out.println("==============================================");
//        System.out.println(Unirest.get("http://localhost:7070/urls/").asJson().getHeaders());
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

//        Unirest.setObjectMapper(new ObjectMapper() {
//            com.fasterxml.jackson.databind.ObjectMapper mapper
//                    = new com.fasterxml.jackson.databind.ObjectMapper();
//
//            public String writeValue(Object value) {
//                return mapper.writeValueAsString(value);
//            }
//
//            public <T> T readValue(String value, Class<T> valueType) {
//                return mapper.readValue(value, valueType);
//            }
//        });
//        Unirest.get(url.getName()).header("User-Agent", "HttpClient").header("accept", "application/json").asJson();
//        HttpResponse<JsonNode> jsonResponse = Unirest.get(url.getName()).header("User-Agent", "HttpClient")
//        .header("accept", "application/json").asJson();
        //Working part
//        HttpResponse<String> jsonResponse = Unirest.get(url.getName()).header("User-Agent", "HttpClient")
//                .header("accept", "application/json").asString();
//        var check = new UrlCheck();
//        check.setStatusCode(jsonResponse.getStatus()); //todo change it
//        try {
//            Document doc = Jsoup.connect(url.getName()).get(); //todo change it
//            if (doc.select("h1").text().length() > LIMIT) {
//                check.setH1(doc.select("h1").text().substring(0, LIMIT) + "...");
//            } else {
//                check.setH1(doc.select("h1").text());
//            }
//            if (doc.title().length() > LIMIT) {
//                check.setTitle(doc.title().substring(0, LIMIT) + "...");
//            } else {
//                check.setTitle(doc.title());
//            }
//            check.setDescription(String.valueOf(doc.selectFirst("meta[name=description]")));
//        } catch (IOException ignored) {
//            if (jsonResponse.getStatus() == 200) {
//                check.setStatusCode(418); //todo change it
//            }
//        }
//        check.setUrlId(urlId);
        HttpResponse<String> response = Unirest.get(url.getName()).asString();
        Document doc = Jsoup.parse(response.getBody());
        var statusCode = response.getStatus();
        var title = doc.title();

        var h1Temp = doc.selectFirst("h1");
        var h1 = h1Temp == null ? "" : h1Temp.text();

        var descriptionTemp = doc.selectFirst("meta[name=description]");
        var description = descriptionTemp == null ? "" : descriptionTemp.text();

        var check = new UrlCheck();
        check.setStatusCode(statusCode);
        check.setTitle(title);
        check.setH1(h1);
        check.setDescription(description);
        check.setUrlId(urlId);
        UrlChecksRepository.save(check);

        ctx.redirect(NamedRoutes.urlPath(urlId));
    }

    public static String getNormalizedURL(URL url) {
        String protocol = url.getProtocol();
        String symbols = "://";
        String host = url.getHost();
        String colonBeforePort = url.getPort() == -1 ? "" : ":";
        String port = url.getPort() == -1 ? "" : String.valueOf(url.getPort());
        return protocol + symbols + host + colonBeforePort + port;
    }
}
