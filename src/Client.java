import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8080);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            getUserNameAndSendToServer(dos, scanner);
            MessageListener messageListener = new MessageListener(dis);
            messageListener.start();
            getUserInput(dos, scanner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getUserNameAndSendToServer(DataOutputStream dos, Scanner scanner) throws IOException {
        String userName = scanner.nextLine();
        dos.writeUTF(userName);
    }

    private static void getUserInput(DataOutputStream dos, Scanner scanner) throws IOException {
        while (true) {
            String message = scanner.nextLine();
            dos.writeUTF(message);
            dos.flush();
        }
    }

}

class MessageListener extends Thread{
    DataInputStream dis;
    MessageListener(DataInputStream dis){
        this.dis = dis;
    }
    @Override
    public void run() {
        super.run();
        try {
            while (true)
                System.out.println( dis.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
