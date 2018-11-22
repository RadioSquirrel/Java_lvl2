package obstances;
import animals.*;
/**
 * Wall class
 * @author Maya Plieva
 * @version Nov 22 2018
 */
public class Wall implements Let {
    private float height;

    public Wall(float height) {
        this.height = height;
    }
    @Override
    public boolean doIt(Animal animal) {
        if (animal instanceof Jumpable)
            return ((Jumpable) animal).jump(height);
        else
            return false;
    }
    public float getHeight(){
        return height;
    }
}