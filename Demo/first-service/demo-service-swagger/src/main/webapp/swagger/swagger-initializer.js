/**
 * Custom Swagger UI initialiser for demo-service.
 *
 * This file overrides the swagger-initializer.js bundled in the swagger-ui
 * WebJar. It is served by SwaggerUiServlet when a request arrives for
 * /swagger/swagger-initializer.js — the WebJar version is not used.
 *
 * The URL of the OpenAPI spec is constructed dynamically from the current
 * window location so the UI works correctly regardless of host, port, or
 * context path.
 */
window.onload = function () {

  // Build the spec URL from the current page location.
  // Removes the trailing /swagger/... path and appends /api/openapi.json
  const basePath = window.location.pathname
    .replace(/\/swagger\/.*$/, '');

  window.ui = SwaggerUIBundle({
    url: window.location.origin + basePath + '/api/openapi.json',

    dom_id:  '#swagger-ui',
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    layout: 'StandaloneLayout',

    // ── UI options ────────────────────────────────────────────────────
    deepLinking:              true,  // each operation gets a shareable URL
    tryItOutEnabled:          true,  // pre-open the Try it out panel
    filter:                   true,  // show the search/filter box
    displayRequestDuration:   true,  // show response time in ms
    defaultModelsExpandDepth: 1,     // auto-expand response schema one level
    syntaxHighlight: {
      theme: 'monokai'              // code block colour theme
    }
  });
};
