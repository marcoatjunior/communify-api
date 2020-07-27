package com.communify.api.contracts;

public interface INotificationService {

    void send(String accessToken, String email);
}
