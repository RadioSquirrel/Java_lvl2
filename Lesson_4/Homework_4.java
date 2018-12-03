import javax.swing.*;
/**
 * Java Level 2. Lesson 4. Homework 4.
 * @author Maya Plieva
 * @version Dec 3 2018
 */

public class Homework_4 {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyWindow();
            }
        });
    }
}