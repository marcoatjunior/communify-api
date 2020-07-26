package com.communify.api.service;

public interface ITaskNotificationService {

    void send(String accessToken, String email);
}
