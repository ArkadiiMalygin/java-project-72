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
import java.util.Comparator;
import java.util.HashMap;

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
        var urlsChecks = UrlChecksRepository.getEntities();
        HashMap<Url, UrlCheck> urlAndItsLastCheck = new HashMap<>();
        for (var url : urls) {
            var urlsCheck = new UrlCheck();
            var mayBeUrlsCheck = urlsChecks.stream().filter(uc -> uc.getUrlId() == url.getId())
                    .toList().stream().max(Comparator.comparing(UrlCheck::getCreatedAt));
            if (mayBeUrlsCheck.isPresent()) {
                urlsCheck = mayBeUrlsCheck.get();
            }
            urlAndItsLastCheck.put(url, urlsCheck);
        }
        var page = new UrlsPage(urlAndItsLastCheck);
        ctx.render("urls/showAll.jte", model("page", page));
    }

    public static void check(Context ctx) throws SQLException, IOException {
        var urlId = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlsRepository.find(urlId)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));
        HttpResponse<String> response = Unirest.get(url.getName()).asString();
        Document doc = Jsoup.parse(response.getBody());
        var statusCode = response.getStatus();
        var title = doc.title();

        var h1Temp = doc.selectFirst("h1");
        var h1 = h1Temp == null ? "" : h1Temp.text();

        var descriptionTemp = doc.selectFirst("meta[name=description]");
        var description = descriptionTemp == null ? "" : descriptionTemp.attr("content");

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
