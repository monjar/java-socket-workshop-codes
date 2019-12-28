import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String userName = scanner.nextLine();
            Socket socket = new Socket("localhost",8080);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MessageListener extends Thread{
        DataInputStream dis;
        DataOutputStream dos;
        MessageListener(DataInputStream dis , DataOutputStream dos){
            this.dos = dos;
            this.dis = dis;
        }
        @Override
        public void run() {
            super.run();
            dis
        }
    }
}
