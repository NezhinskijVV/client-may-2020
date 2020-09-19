package ru.itsjava.service;

import lombok.SneakyThrows;

import java.net.Socket;

public class SocketRunnable implements Runnable {
    private final MessageInputService messageInputService;
    private final Socket socket;

    @SneakyThrows
    public SocketRunnable(Socket socket) {
        this.socket = socket;
        this.messageInputService = new MessageInputService(socket.getInputStream());
    }

    @SneakyThrows
    @Override
    public void run() {
        String messageFromServer;
        while ((messageFromServer = messageInputService.readMessage()) != null) {
            if (messageFromServer.equals("401")){
                socket.close();
                System.exit(-1);
            }
            System.out.println(messageFromServer);
        }
    }
}

//HW:
//1. Тесты + assertAll() через лямбду
//2. Выделить MessageOutputService и на него тоже написать тесы
//3. Для сервера сделать тоже самое + тесты