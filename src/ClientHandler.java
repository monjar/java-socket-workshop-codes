import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String userName;
    ClientHandler(Socket socket, DataOutputStream dos, DataInputStream dis) {
        this.socket = socket;
        this.dos = dos;
        this.dis = dis;
    }


    @Override
    public void run() {
        super.run();
        try {
            String userName = this.dis.readUTF();
            this.userName = userName;
            System.out.println("USER "+ userName+ " CONNECTED!");

            while (true)
                getMessageFromClient(userName);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMessageFromClient(String userName) throws IOException {
        String message = dis.readUTF();
        String[] commands = message.split("->");
        String targetName = commands[0];
        String messageToTarget = commands[1];
        sendMessageToTargets(userName, targetName, messageToTarget);
    }

    private void sendMessageToTargets(String userName, String targetName, String messageToTarget) throws IOException {
        for (ClientHandler ch : Server.getHandlers()) {
            if (ch.getUserName().equals(targetName)){
                ch.getDos().writeUTF(userName+" SAYS: " +messageToTarget);
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public DataInputStream getDis() {
        return dis;
    }


}
