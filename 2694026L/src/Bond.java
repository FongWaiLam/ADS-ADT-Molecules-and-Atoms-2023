// Name: Fong Wai Lam (GUID: 2694026L)


/**
 *  This class bond is a pair (Atom, weight).
 *  It provides getters and setters for its members' operation.
 */
public class Bond {

    private Atom child;
    private int weight;

    //add constructor, getters setters
    //and any other methods you require (if any)


    public Bond(Atom child, int weight) {
        this.child = child;
        this.weight = weight;
    }

    public Atom getChild() {
        return child;
    }

    public void setChild(Atom child) {
        this.child = child;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
