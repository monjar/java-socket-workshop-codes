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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String targetName){


    }

    public String getMessage(){


    }

}
