package dev.francode.pushNotifications.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import dev.francode.pushNotifications.dto.PushNotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class FirebaseNotificationService {

    public void sendNotification(PushNotificationRequest request) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(request.getTitle())
                        .setBody(request.getMessage())
                        .build())
                .setToken(request.getToken())
                .build();

        FirebaseMessaging.getInstance().send(message);
    }
}