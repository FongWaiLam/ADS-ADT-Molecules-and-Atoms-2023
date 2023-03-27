// Name: Fong Wai Lam (GUID: 2694026L)

/**
 * The Atom class holds the information of this atom, including the element, its valency,
 * and the bonds linking to the next atoms.
 *
 * To help the operations in Atom class, the following methods are created:
 * 1. Getters and Setters
 * 2. createMap()
 * 3. removeBond(String element)
 * 4. addBond(Atom atom, int strength)
 */

import java.util.*;

public class Atom  {

    private String element;
    private List<Bond> bonds = new ArrayList<Bond>();//the bonds to child atoms
    private int valency;

    //A static Map to help look up valencies
    private static final Map<String, Integer> VALENCY_MAP = createMap();

    public Atom() {
    }

    // When a free Atom is created, its list of bonds consist of no. of bonds of hydrogen atom
    // equals to valency and all bonds weights 1.
    public Atom(String type) {
        element = type;
        valency = createMap().get(type);
        for (int i = 0; i < valency; i++) {
            Atom hydrogen = new Atom();
            hydrogen.element = "H";
            hydrogen.valency = 1;
            bonds.add(new Bond(hydrogen, 1));
        }
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public List<Bond> getBonds() {
        return bonds;
    }

    public void setBonds(List<Bond> bonds) {
        this.bonds = bonds;
    }

    public int getValency() {
        return valency;
    }

    public void setValency(int valency) {
        this.valency = valency;
    }

    //A static Mapping method to help to look up valencies
    private static Map<String, Integer> createMap() {
        Map<String, Integer> result = new HashMap<>();
        result.put("H", 1);// hydrogen
        result.put("C", 4);// carbon
        result.put("B", 3);// boron
        result.put("N", 3);// nitrogen
        result.put("O", 2);// oxygen
        result.put("F", 1);// fluorine
        result.put("P", 3);// phosphorous
        result.put("S", 2);// sulphur
        result.put("Cl", 1);// chlorine
        result.put("Br", 1);// bromine

        return Collections.unmodifiableMap(result);
    }

    /**
     *  This removeBond() method traverse every bond of this atom
     *  to find the specific element atom bond to remove from this bonds list
     */
    public void removeBond(String element){
        for (Bond bond : bonds) {
            if (bond.getChild().element.equals(element)) {
                bonds.remove(bond);
                break;
            }
        }
    }

    /**
     *  This addBond() method repeat the removal of hydrogen bond of the current atom and a new atom to be bonded.
     *  This paired removal will be repeated for the no. of times equals to the strength of bond.
     *  Then, this new atom is added to the bonds list of current atom.
     */
    public void addBond(Atom atom, int strength) {
        for (int i = 0; i < strength; i++) {
            this.removeBond("H");
            atom.removeBond("H");
        }
        bonds.add(new Bond(atom, strength));
    }

}
