import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Java Level 2. Lesson 6. Homework 6.
 * @author Maya Plieva
 * @version Dec 10 2018
 */

public class Server {


    public static void main(String[] args) {
        Server server = new Server();
    }

    public Server() {

        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(3443);
            System.out.println("Server is working");
            socket = server.accept();
            System.out.println("New Client");
            Scanner in =  new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("Write your msg");
                        String msg = sc.nextLine();
                        System.out.println("Msg has send");
                        out.println(msg);
                    }
                }
            }).start();
            while (true) {
                String msg = in.nextLine();
                if (msg.equals("/end")) break;
                System.out.println("Client: " + msg);
 //               out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                server.close();
                System.out.println("Closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}