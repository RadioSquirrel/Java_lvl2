import java.io.IOException;
import java.util.*;
/**
 * Java Level 2. Lesson 3. Homework 3.
 * @author Maya Plieva
 * @version Nov 29 2018
 */

public class Homework_3 {
    static final String FILE_NAME1 = "C://Java_lvl2/Lesson_3/Java2Lesson3Homework3.txt";
    static final String FILE_NAME2 = "Java2Lesson3Homework3.txt";

    public static void main(String[] args) {
        List<String> wordArray = new ArrayList<String>();
        wordArray = ArrayOperation.fillTheArray(wordArray);
        System.out.println(wordArray + "\n");
        ArrayOperation.printUniqueWords(wordArray);
        wordArray.clear();
        try {
            wordArray = ArrayOperation.fillTheArrayFromFile(wordArray, FILE_NAME2);
        } catch (IOException e) {
            System.out.println("Failed to fill array from file!\n");
        }
        try {
            wordArray = ArrayOperation.fillTheArrayFromFile(wordArray, FILE_NAME1);
        } catch (IOException e) {
            System.out.println("Failed to fill array from file!\n");
        }
        System.out.println(wordArray + "\n");
        ArrayOperation.printUniqueWords(wordArray);

        Phonebook phonebook = new Phonebook();

        phonebook.add(2304578, "Ivanov");
        phonebook.add(5678794, "Ivanov");
        phonebook.add(3456783, "Petrov");
        phonebook.add(3456278, "Sidorov");
        phonebook.add(9786053, "Sidorov");
        phonebook.add(6475893, "Sidorov");

        phonebook.get("Ivanov");
        phonebook.get("Petrov");
        phonebook.get("Sidorov");
        phonebook.get("Gagarin");
    }
}