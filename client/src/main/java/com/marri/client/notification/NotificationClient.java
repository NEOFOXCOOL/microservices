package com.marri.client.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(
        name = "notification",
        url = "${client.notification.url}"
)
public interface NotificationClient {
    @PostMapping(path = "api/v1/notification/send")
    public void sendNotification(@RequestBody NotificationRequest request);
}
