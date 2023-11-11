import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final String host; // L'adresse du serveur à laquelle se connecter
    private final int port;    // Le port sur lequel le serveur écoute

    /**
     * Constructeur de la classe Client.
     * @param host Adresse IP ou nom d'hôte du serveur.
     * @param port Port du serveur.
     */
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void startClient() {
        System.out.println("Tentative de connexion au serveur " + host + " sur le port " + port + "...");

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connecté au serveur. Vous pouvez commencer à taper des messages. Tapez 'bye' pour quitter.");
            
            // Thread pour lire les messages du serveur
            new Thread(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = in.readLine()) != null) {
                        System.out.println("Serveur: " + serverResponse);
                    }
                } catch (IOException e) {
                    System.out.println("Erreur lors de la lecture des messages du serveur: " + e.getMessage());
                }
            }).start();
            ///////////////////////////////////////////////////////
            // Boucle pour envoyer des messages au serveur
            String userInput;
            while (!(userInput = stdIn.readLine()).equalsIgnoreCase("bye")) {
                out.println(userInput);
            }

        } catch (IOException e) {
            System.out.println("Impossible de se connecter au serveur: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Client déconnecté.");
    }
}
