package animals;
/**
 * Hen class
 * @author Maya Plieva
 * @version Nov 22 2018
 */
public class Hen extends Animal implements Jumpable {
    private final int RUN_LIMIT = 100;
    private final float JUMP_LIMIT = 10f;
    private float jump_limit;

    public Hen(String name) {
        this.name = name;
        this.run_limit = RUN_LIMIT;
        jump_limit = JUMP_LIMIT;
    }
    public Hen(String name, int run_limit, float jump_limit) {
        this.name = name;
        this.run_limit = run_limit;
        this.jump_limit = jump_limit;
    }

    @Override
    public String voice() {
        return "pok";
    }

    @Override
    public boolean jump(float height) {
        return jump_limit >= height;
    }
    @Override
    public float getJumpLimit(){
        return jump_limit;
    }
}