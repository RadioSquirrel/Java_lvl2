import animals.*;
import obstances.*;
/**
 * Java Level 2. Lesson 1. Homework 1.
 * @author Maya Plieva
 * @version Nov 22 2018
 */
public class Main {

    public static void main(String[] args) {
        Team team1 = new Team("Team Liquid");
        team1.printInformationAboutTheTeam();
        Team team2 = new Team("NaVi");
        team2.printInformationAboutTheTeam();
        Course course = new Course();
        course.printInformationAboutTheObstacle();
        course.passObstacles(team1);
        course.passObstacles(team2);
        team1.passedTheDistance();
        team2.passedTheDistance();
    }
}