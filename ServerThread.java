import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket clientSocket;
    private final Server server;
    private PrintWriter writer;

    public ServerThread(Socket socket,Server server) {
        this.clientSocket = socket;
        this.server = server;
    }



    public void run() {
        try{
            InputStream Input = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(Input));
            OutputStream output = clientSocket.getOutputStream();
            writer = new PrintWriter(output, true);

           String clientMessage ;

           do {
            clientMessage = reader.readLine();
            server.broadcastMessage(clientMessage);
           }while(!clientMessage.equals("QUIT"));

           server.removeClient(this);
           clientSocket.close();


        }catch (IOException e){
            System.out.println("ServerThread exception: " + e.getMessage());
            e.printStackTrace();
        }

      
    }
    public void sendMessage(String message) {
        writer.println(message);
    }

    public Socket getClientSocket(){
        return clientSocket;
    }
}