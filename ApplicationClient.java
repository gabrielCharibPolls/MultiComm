// ApplicationClient.java
public class ApplicationClient {
    public static void main(String[] args) {
        Client client = new Client("localhost", 6666);
        client.startClient();
    }
}