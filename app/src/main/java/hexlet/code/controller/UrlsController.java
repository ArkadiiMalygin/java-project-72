package hexlet.code.controller;

import hexlet.code.dto.urls.BuildUrlPage;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void build(Context ctx) {
        var page = new BuildUrlPage();
        ctx.render("index.jte", model("page", page));
    }

    public static void create(Context ctx) {
        try {
            var name = ctx.formParam("name");
            var urlData = new URL(name);
            var uriData = urlData.toURI();
            var url = new Url(uriData.toString());
            UrlsRepository.save(url);
            ctx.redirect(NamedRoutes.urlsPath());


        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            var page = new BuildUrlPage(name);
            page.setValidationErrors(e.getErrors());
            ctx.render("index.jte", model("page", page)).status(422);
        } catch (URISyntaxException e) {
            var name = ctx.formParam("name");
            var page = new BuildUrlPage(name);
            page.setErrors("Некорректный URL");
            ctx.render("index.jte", model("page", page)).status(422);
        } catch (SQLException e) {
            var name = ctx.formParam("name");
            var page = new BuildUrlPage(name);
            page.setErrors("DB has not process ur ULR");
            ctx.render("index.jte", model("page", page)).status(418);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = UrlsRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new UrlPage(post);
        ctx.render("urls/show.jte", model("page", page));
    }

    public static void showAll(Context ctx) throws SQLException {
        var urls = UrlsRepository.getEntities();
        var page = new UrlsPage(urls);
        ctx.render("urls/showAll.jte", model("page", page));
    }
}
