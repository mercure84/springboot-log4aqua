package com.centropyge92.log4aqua.api;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationMessaging {

    public void sendPushNotification(String deviceToken, String title, String message) {
        Message notificationMessage = Message.builder()
                .setToken(deviceToken)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(message)
                        .build())
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(notificationMessage);
            System.out.println("Notification envoyée : " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
}
