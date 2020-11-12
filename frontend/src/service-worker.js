workbox.core.setCacheNameDetails({prefix: "pwa"});

self.addEventListener('message', (event) => {
    if (event.data && event.data.type === 'SKIP_WAITING') {
        self.skipWaiting();
    }
});

workbox.routing.registerRoute(/.*(?:devcodeits)*$/,
    new workbox.strategies.NetworkFirst({
        cacheName:'devcodeits'
    })
);

self.__precacheManifest = [].concat(self.__precacheManifest || []);
workbox.precaching.precacheAndRoute(self.__precacheManifest, {});