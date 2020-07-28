package com.communify.api.contract;

public interface INotificationService {

    void send(String accessToken, String email);
}
