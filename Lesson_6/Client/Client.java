import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Java Level 2. Lesson 5. Homework 5.
 * @author Maya Plieva
 * @version Dec 10 2018
 */

public class Client {

    public static void main(String[] args) {

        Socket socket = null;

        try {
            socket = new Socket("localhost", 3443);
            Scanner in =  new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String msg = in.nextLine();
                        System.out.println("Server: " + msg);
                    }
                }
            }).start();

            while (true) {
                System.out.println("Write your msg");
                String msg = sc.nextLine();
                out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}