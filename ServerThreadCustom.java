import java.net.Socket;


public class ServerThreadCustom extends ServerThread {
    public ServerThreadCustom(Socket socket ,Server server) {
        super(socket,server);
    }

    @Override
    public void run() {
        // Custom business logic for server thread
    }
}
