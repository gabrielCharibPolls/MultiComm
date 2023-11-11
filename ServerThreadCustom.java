public class ServerThreadCustom extends ServerThread {
    public ServerThreadCustom(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        // Custom business logic for server thread
    }
}