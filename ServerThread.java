import java.net.Socket;

public class ServerThread extends Thread {
    private Socket clientSocket;

    public ServerThread(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        // Communication avec le client
    }
}