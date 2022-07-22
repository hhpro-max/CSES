package ClientSide;

public class ClientMain {
    public static void main(String[] args) {
        Client client = new Client();
        new Thread(client).start();
        MainFrame mainFrame = new MainFrame();
    }
}
