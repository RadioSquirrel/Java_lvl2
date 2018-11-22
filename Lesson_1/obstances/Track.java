package obstances;
import animals.*;
/**
 * Track class
 * @author Maya Plieva
 * @version Nov 22 2018
 */
public class Track implements Let {
    private int length;

    public Track(int length) {
        this.length = length;
    }
    @Override
    public boolean doIt(Animal animal) {
        return animal.run(length);
    }
    public int getLength(){
        return length;
    }
}