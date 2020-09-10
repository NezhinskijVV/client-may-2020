package ru.itsjava.service;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientService {
    public static final String HOST = "localhost";
    public static final int PORT = 8081;
    private final MessageInputService consoleInputService = new MessageInputService(System.in);

    @SneakyThrows
    public void start() {
        Socket socket = new Socket(HOST, PORT);
        if (socket.isConnected()) {
            System.out.println("I'm connected");

            new Thread(new SocketRunnable(socket)).start();

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());

            System.out.println("Привет, Дорогой! \nВведи свой логин:");
            String login = consoleInputService.readMessage();
            System.out.println("Введи свой пароль:");
            String password = consoleInputService.readMessage();
            serverWriter.println("!@#$" + login + ":" + password);
            serverWriter.flush();

            String consoleInput;
            while ((consoleInput = consoleInputService.readMessage()) != null) {
                serverWriter.println(consoleInput);
                serverWriter.flush();
            }
        }
    }
}
