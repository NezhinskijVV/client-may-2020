import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientService {
    public static final String HOST = "localhost";
    public static final int PORT = 8081;

    @SneakyThrows
    public void start() {
        Socket socket = new Socket(HOST, PORT);
        if (socket.isConnected()) {
            System.out.println("Client connected");

            new Thread(new SocketRunnable(socket)).start();

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Привет, Дорогой! \nВведи свой логин:");
            String login = consoleReader.readLine();
            System.out.println("Введи свой пароль:");
            String password = consoleReader.readLine();
            serverWriter.println("!@#$" + login + ":" + password);
            serverWriter.flush();

            String consoleInput;
            while ((consoleInput = consoleReader.readLine()) != null) {
                serverWriter.println(consoleInput);
                serverWriter.flush();
            }
        }
    }
}
