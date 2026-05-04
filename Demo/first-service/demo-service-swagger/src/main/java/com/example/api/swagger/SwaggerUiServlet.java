package com.example.api.swagger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Serves Swagger UI static assets from the swagger-ui WebJar.
 *
 * The WebJar (org.webjars:swagger-ui) bundles the Swagger UI HTML, CSS,
 * and JavaScript inside the JAR as:
 *   /META-INF/resources/webjars/swagger-ui/{version}/...
 *
 * This servlet maps /swagger/* requests to those resources and patches
 * index.html on-the-fly to point the UI at our /api/openapi.json endpoint
 * instead of the default Petstore demo URL.
 *
 * URLs handled:
 *   GET /swagger/           → redirect to /swagger/index.html
 *   GET /swagger/index.html → Swagger UI with correct openapi.json URL
 *   GET /swagger/*.js       → JavaScript from the WebJar
 *   GET /swagger/*.css      → CSS from the WebJar
 *   GET /swagger/*.png      → images from the WebJar
 *
 * All imports use javax.servlet.* — correct for Tomcat 9.
 * Do NOT use jakarta.servlet.* which is for Tomcat 10+ only.
 */
@WebServlet("/swagger/*")
public class SwaggerUiServlet extends HttpServlet {

    /**
     * Must match the swagger-ui version in pom.xml exactly.
     * Update this constant whenever the dependency version changes.
     */
    private static final String SWAGGER_UI_VERSION = "5.17.14";

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException {

        String path = req.getPathInfo();

        // Redirect /swagger/ and /swagger (no trailing path) to the index page
        if (path == null || path.equals("/") || path.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/swagger/index.html");
            return;
        }

        // Serve index.html with the openapi.json URL injected
        if (path.equals("/index.html")) {
            serveIndex(req, resp);
            return;
        }

        // Serve swagger-initializer.js from our WAR root if present,
        // otherwise fall through to the WebJar version
        if (path.equals("/swagger-initializer.js")) {
            InputStream custom = getServletContext()
                .getResourceAsStream("/swagger/swagger-initializer.js");
            if (custom != null) {
                resp.setContentType("application/javascript");
                custom.transferTo(resp.getOutputStream());
                return;
            }
            // fall through to WebJar version below
        }

        // Serve all other static assets from the WebJar
        String resourcePath = "/META-INF/resources/webjars/swagger-ui/"
            + SWAGGER_UI_VERSION + path;
        InputStream is = getClass().getResourceAsStream(resourcePath);

        if (is == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                "Swagger UI asset not found: " + path);
            return;
        }

        resp.setContentType(contentTypeFor(path));
        is.transferTo(resp.getOutputStream());
    }

    /**
     * Reads the stock Swagger UI index.html from the WebJar and replaces
     * the default Petstore URL with the URL of our openapi.json endpoint.
     */
    private void serveIndex(HttpServletRequest req,
                            HttpServletResponse resp) throws IOException {

        // Build the absolute URL to our OpenAPI spec
        String specUrl = req.getScheme() + "://"
            + req.getServerName() + ":" + req.getServerPort()
            + req.getContextPath()
            + "/api/openapi.json";

        // Read the stock index.html from the WebJar
        String resourcePath = "/META-INF/resources/webjars/swagger-ui/"
            + SWAGGER_UI_VERSION + "/index.html";
        InputStream is = getClass().getResourceAsStream(resourcePath);

        if (is == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "swagger-ui WebJar not found — check the swagger-ui dependency in pom.xml");
            return;
        }

        String html = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        // Replace the default Petstore URL with our spec URL.
        // The stock index.html references the Petstore URL in swagger-initializer.js;
        // because we intercept swagger-initializer.js above, this replace handles
        // any inline references that also exist in the HTML.
        html = html.replace(
            "https://petstore.swagger.io/v2/swagger.json",
            specUrl
        );

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(html);
    }

    /** Returns a Content-Type header value for common static asset extensions. */
    private String contentTypeFor(String path) {
        if (path.endsWith(".js"))   return "application/javascript";
        if (path.endsWith(".css"))  return "text/css";
        if (path.endsWith(".html")) return "text/html";
        if (path.endsWith(".png"))  return "image/png";
        if (path.endsWith(".ico"))  return "image/x-icon";
        if (path.endsWith(".map"))  return "application/json"; // source maps
        return "application/octet-stream";
    }
}
