package com.centropyge92.log4aqua.service;

import com.centropyge92.log4aqua.api.PushNotificationMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {

    @Autowired
    PushNotificationMessaging pushNotificationMessaging;


    public void sendNotificationToDevice(String deviceToken, String title, String message) {
        pushNotificationMessaging.sendPushNotification(deviceToken, title, message);
    }


}
