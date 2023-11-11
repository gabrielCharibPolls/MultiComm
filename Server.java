import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;





public class Server {
    private ServerSocket serverSocket;
    private final List<ServerThread>clients = new ArrayList<>();


    public void startServer() {
        try {
            serverSocket = new ServerSocket(6666);
            System.out.println("Server is running on port 6666");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
                //new ServerThread(clientSocket).start();
                ServerThread clientThread = new ServerThread(clientSocket, this);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (Exception e) {
            System.out.println("SERVER EXECTION");
            e.printStackTrace();
        }
    }

    //envoyer un message aux clients 
    public void broadcastMessage(String message){
        for(ServerThread client :clients){
            client.sendMessage(message);

        }
    }

    public void removeClient(ServerThread clienThread){
        clients.remove(clienThread);
        System.out.println("Client disconnected: " + clientThread.getClientSocket().getInetAddress().getHostAddress());
    }


}