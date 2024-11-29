const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/learnlink', // URI base para o front-end
        createProxyMiddleware({
            target: "http://localhost:8080", // Endereço do servidor backend
            changeOrigin: true, // Necessário para evitar problemas de CORS
        })
    );
};
