package ru.itsjava.service;

import lombok.SneakyThrows;

import java.net.Socket;

public class SocketRunnable implements Runnable {
    private final MessageInputService messageInputService;

    @SneakyThrows
    public SocketRunnable(Socket socket) {
        this.messageInputService = new MessageInputService(socket.getInputStream());
    }

    @SneakyThrows
    @Override
    public void run() {
        String messageFromServer;
        while ((messageFromServer = messageInputService.readMessage()) != null) {
            System.out.println(messageFromServer);
        }
    }
}

//HW:
//1. Тесты + assertAll() через лямбду
//2. Выделить MessageOutputService и на него тоже написать тесы
//3. Для сервера сделать тоже самое + тесты