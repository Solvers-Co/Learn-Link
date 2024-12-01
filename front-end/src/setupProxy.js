const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/java-api', // URI base para o front-end
        createProxyMiddleware({
            target: 'http://177.8.164.141:8080', // Endereço do servidor backend
            changeOrigin: true, // Necessário para evitar problemas de CORS
        })
    );
};
