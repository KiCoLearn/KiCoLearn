importScripts('https://www.gstatic.com/firebasejs/8.0.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.0.0/firebase-messaging.js');

firebase.initializeApp({
    apiKey: "AIzaSyAhMXF1P6tp68I6pKXkLoiqKEywnDH824w",
    messagingSenderId: "630",
    projectId: "kikolearn",
    appId: "1:181470434968:web:c012ef80998bb6d6fbe2ed",
});

const messaging = firebase.messaging();

messaging.setBackgroundMessageHandler((payload) => {
    console.info('[info] Firebase 알림 : ', payload);

    const notificationTitle = 'Background Message Title'
    const notificationOptions = {
        body: 'Background Message body.',
        icon: '/firebase-logo.png'
    }

    return self.registration.showNotification(
        notificationTitle,
        notificationOptions
    );
});