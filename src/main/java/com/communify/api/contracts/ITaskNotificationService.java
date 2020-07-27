package com.communify.api.contracts;

public interface ITaskNotificationService {

    void send(String accessToken, String email);
}
