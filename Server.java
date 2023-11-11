import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public void startServer() {
        try {
            serverSocket = new ServerSocket(6666);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ServerThread(clientSocket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}