package dev.francode.pushNotifications.controller;

import dev.francode.pushNotifications.dto.PushNotificationRequest;
import dev.francode.pushNotifications.service.FirebaseNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private FirebaseNotificationService firebaseNotificationService;

    @PostMapping("/send")
    public String sendNotification(@RequestBody PushNotificationRequest request) {
        try {
            firebaseNotificationService.sendNotification(request);
            return "Notification sent successfully!";
        } catch (Exception e) {
            return "Error sending notification: " + e.getMessage();
        }
    }
}
