const fs = require("fs");

module.exports = {
    devServer: {
        https: {
            cert: fs.readFileSync("/etc/letsencrypt/live/k3a506.p.ssafy.io/fullchain.pem"),
            key: fs.readFileSync("/etc/letsencrypt/live/k3a506.p.ssafy.io/privkey.pem"),
        }
    },
    transpileDependencies: [
        'vuetify'
    ],
    pwa: {
        workboxPluginMode: 'InjectManifest',
        workboxOptions: {
            swSrc: 'src/service-worker.js'
        }
    }
};