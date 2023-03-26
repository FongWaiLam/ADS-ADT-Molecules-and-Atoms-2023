// Fong Wai Lam (2604026L)

// and import statements

import java.util.*;

public class Atom  {

    private String element;
    private List<Bond> bonds = new ArrayList<Bond>();//the bonds to child atoms
    private int valency;


    //this map has been included to help you look up valencies
    //but you can ignore it (and delete it) if you want to use something else

    private static final Map<String, Integer> VALENCY_MAP = createMap();

    public Atom() {
    }

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

    //now add all of the Atom constructors and methods you require


//    public int countHBond() {
////        System.out.println("Start counting H bond for element: " + element);
//        int countOfHBond = 0;
//        for (Bond bond : bonds) {
//            if (bond.getChild().element.equals("H")) {
////                System.out.println("Found Free bond + 1");
//                countOfHBond++;
//            }
//        }
////        System.out.println("H bond No. for element: " + element + " is " + countOfHBond);
//        return countOfHBond;
//    }

    public void removeBond(String element){
        for (Bond bond : bonds) {
            if (bond.getChild().element.equals(element)) {
                bonds.remove(bond);
                break;
            }
        }
    }

    public void addBond(Atom atom, int strength) {
        for (int i = 0; i < strength; i++) {
            this.removeBond("H");
//            System.out.println(this.element + " has one H bond removed.");
            atom.removeBond("H");
//            System.out.println(atom.element + " has one H bond removed.");
        }
        bonds.add(new Bond(atom, strength));
    }


//    public boolean contains(Atom a) {
//        if (this.equals(a)) {
//            return true;
//        } else {
//            for (Bond bond : bonds) {
//                if (bond.getChild().contains(a)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

//    public String traverseWithoutBracket(String printType) {
//        String print = "";
//        String bondChildPrint = "";
//        for (Bond bond : bonds) {
//            if ("smiles".equals(printType)) {
//                bondChildPrint = bond.getChild().smiles();
//            } else if ("structural".equals(printType)) {
//                bondChildPrint = bond.getChild().structuralFormula();
//            }
//            if (!"".equals(bondChildPrint)) {
//                System.out.println("Bond: " + element + " and " + bond.getChild().element + " bond weight: " + bond.getWeight());
//                if (bond.getWeight() == 1) {
//                    print += bondChildPrint;
//                } else if (bond.getWeight() == 2) {
//                    print += ("=" + bondChildPrint);
//                } else if (bond.getWeight() == 3) {
//                    print += ("#" + bondChildPrint);
//                }
//            }
//        }
//        return print;
//    }
//    public String traverseWithBracket(String printType) {
//        String print = "";
//        String bondChildPrint = "";
//        for (Bond bond : bonds) {
//            System.out.println("Bond: " + element + " has " + bond.getChild().element);
//            if ("smiles".equals(printType)) {
//                bondChildPrint = bond.getChild().smiles();
//            } else if ("structural".equals(printType)) {
//                bondChildPrint = bond.getChild().structuralFormula();
//            }
//            if (!"".equals(bondChildPrint)) {
//                System.out.println("Bond: " + element +" and " + bond.getChild().element + " bond weight: " + bond.getWeight() );
//                if (bond.getWeight() == 1) {
//                    print += ("(" + bondChildPrint + ")");
//                } else if (bond.getWeight() == 2) {
//                    print += ("(=" + bondChildPrint + ")");
//                } else if (bond.getWeight() == 3) {
//                    print += ("(#" + bondChildPrint + ")");
//                }
//            }
//        }
//        return print;
//    }

//    public String smiles() {
//        String smiles = "";
//        if (!"H".equals(element)) {
//            smiles += element;
//        } else if ("H".equals(element)) {
//            return "";
//        }
//        // Only 1 bond
//        if (bonds.size() - this.countHBond() <= 1) {
//            smiles += traverseWithoutBracket("smiles");
//            // More than 1 bond
//        } else if (bonds.size() - this.countHBond() > 1) {
//            smiles += traverseWithBracket("smiles");
//        }
//        return smiles;
//    }

//    public String structuralFormula() {
//        String structuralFormula = "";
//        String hydrogen = "H";
//        if (!"H".equals(element)) {
//            if (this.countHBond() == 1) {
//                structuralFormula += (element + hydrogen);
//            } else if (this.countHBond() > 1) {
//                structuralFormula += (element + hydrogen + this.countHBond());
//            } else {
//                structuralFormula += element;
//            }
//        } else if ("H".equals(element)) {
//            return "";
//        }
//        // Only 1 bond
//        if (bonds.size() - this.countHBond() <= 1) {
//            structuralFormula += traverseWithoutBracket("structural");
//            // More than 1 bond
//        } else if (bonds.size() - this.countHBond() > 1) {
//            structuralFormula += traverseWithBracket("structural");
//        }
//        return structuralFormula;
//    }

}
