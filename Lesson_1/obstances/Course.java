package obstances;
import java.util.*;
import animals.*;
/**
 * Course class
 * @author Maya Plieva
 * @version Nov 22 2018
 */
public class Course {
    Let[] let = new Let[3];
    Random random = new Random();

    public Course() {
        Track track = new Track(random.nextInt(100));
        Wall wall = new Wall(random.nextFloat() * 10);
        Water water = new Water(random.nextInt(100));
        let[0] = (Let) track;
        let[1] = (Let) wall;
        let[2] = (Let) water;
    }

    public void printInformationAboutTheObstacle() {
        System.out.println("Length track: " + ((Track) let[0]).getLength());
        System.out.println("Height wall: " + ((Wall) let[1]).getHeight());
        System.out.println("Length water: " + ((Water) let[2]).getLength());
        System.out.println();
    }

    public void passObstacles(Team team){
        for(Animal animal : team.teamAnimal){
            for(Let l : let){
                if(!l.doIt(animal)){
                    animal.setPassing(l.doIt(animal));
                    break;
                }
                animal.setPassing(true);
            }
        }
    }
}