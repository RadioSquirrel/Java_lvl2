import Lesson_8.client.Client;
import Lesson_8.server.MServer;
import javafx.application.Application;

/**
 * Java Level 2. Lesson 8. Homework 8.
 * @author Maya Plieva
 * @version Dec 15 2018
 */

public class Runner {
    public static void main(String[] args) {
        new Thread(() -> new MServer()).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        new Thread(() -> Application.launch(Client.class, args)).start();
    }
}