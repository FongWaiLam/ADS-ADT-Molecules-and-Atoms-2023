//Fong Wai Lam (2694026L)

//add import statement



public class TreeMolecule implements Molecule {

    private Atom first;

    public TreeMolecule(Atom atom) {
        first = atom;
    }

    @Override
    public boolean addBond(Atom a1, Atom a2, int strength) {
//        System.out.println("Add " + a2.getElement() + "(a2) to " + a1.getElement() + " (a1).");
        int a1HBondNo = countHBond(a1);
        int a2HBondNo = countHBond(a2);
        if (a1HBondNo >= strength && a2HBondNo >= strength) {
//            System.out.println(a1.getElement() + "(a1) has enough hydrogen bond for strength " + strength);
            a1.addBond(a2, strength);
            return true;
        }
//        System.out.println(a1.getElement() + " (a1) does not enough hydrogen bond for strength " + strength);
        return false;
    }

    // Helper Method to count the H bond number
    public int countHBond(Atom atom) {
//        System.out.println("Start counting H bond for element: " + element);
        int countOfHBond = 0;
        for (Bond bond : atom.getBonds()) {
            if (bond.getChild().getElement().equals("H")) {
//                System.out.println("Found Free bond + 1");
                countOfHBond++;
            }
        }
//        System.out.println("H bond No. for element: " + element + " is " + countOfHBond);
        return countOfHBond;
    }
    @Override
    public boolean contains(Atom target) {
        return contains(first, target);
    }

    // Helper method for recursively changing the current atom
    public boolean contains(Atom curr, Atom target) {
        if (curr == null) {
            return false;
        }
        if (curr.equals(target)) {
            return true;
        } else {
            for (Bond bond: curr.getBonds()) {
                if (contains(bond.getChild(), target)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String smilesString() {
        String smiles = "";
        smiles = smiles(first);
        return smiles;
    }

    // Helper method for recursively changing the current atom
    public String smiles(Atom curr) {
        String smiles = "";
        if (!"H".equals(curr.getElement())) {
            smiles += curr.getElement();
        } else if ("H".equals(curr.getElement())) {
            return "";
        }
        // Only 1 bond
        if (curr.getBonds().size() - countHBond(curr) <= 1) {
            smiles += traverseWithoutBracket("smiles", curr);
            // More than 1 bond
        } else if (curr.getBonds().size() - countHBond(curr) > 1) {
            smiles += traverseWithBracket("smiles", curr);
        }
        return smiles;
    }

    @Override
    public String structuralFormula() {
        String structuralFormula = "";
        structuralFormula = structuralFormula(first);
        return structuralFormula;
    }

    // Helper method for recursively changing the current atom
    public String structuralFormula(Atom curr) {
        String structuralFormula = "";
        String hydrogen = "H";
        if (!"H".equals(curr.getElement())) {
            if (countHBond(curr) == 1) {
                structuralFormula += (curr.getElement() + hydrogen);
            } else if (countHBond(curr) > 1) {
                structuralFormula += (curr.getElement() + hydrogen + countHBond(curr));
            } else {
                structuralFormula += curr.getElement();
            }
        } else if ("H".equals(curr.getElement())) {
            return "";
        }
        // Only 1 bond
        if (curr.getBonds().size() - countHBond(curr) <= 1) {
            structuralFormula += traverseWithoutBracket("structural", curr); // StringFormat()
            // More than 1 bond
        } else if (curr.getBonds().size() - countHBond(curr) > 1) {
            structuralFormula += traverseWithBracket("structural", curr);
        }
        return structuralFormula;
    }

    // Helper method for traversing each atom in the bond
    // and recursively calling targeted method
    public String traverseWithoutBracket(String printType, Atom curr) {
        String print = "";
        String bondChildPrint = "";
        if (curr == null) {return "";}
        for (Bond bond : curr.getBonds()) {
            if ("smiles".equals(printType)) {
                bondChildPrint = smiles(bond.getChild());
            } else if ("structural".equals(printType)) {
                bondChildPrint = structuralFormula(bond.getChild());
            }
            if (!"".equals(bondChildPrint)) {
//                System.out.println("Bond: " + curr.getElement() + " and " + bond.getChild().getElement() + " bond weight: " + bond.getWeight());
                if (bond.getWeight() == 1) {
                    print += bondChildPrint;
                } else if (bond.getWeight() == 2) {
                    print += ("=" + bondChildPrint);
                } else if (bond.getWeight() == 3) {
                    print += ("#" + bondChildPrint);
                }
            }
        }
        return print;
    }

    // Helper method for traversing each atom in the bond
    // and recursively calling targeted method
    public String traverseWithBracket(String printType, Atom curr) {
        String print = "";
        String bondChildPrint = "";
        if (curr == null) {return "";}
        for (Bond bond : curr.getBonds()) {
//            System.out.println("Bond: " + curr.getElement() + " has " + bond.getChild().getElement());
            if ("smiles".equals(printType)) {
                bondChildPrint = smiles(bond.getChild());
            } else if ("structural".equals(printType)) {
                bondChildPrint = structuralFormula(bond.getChild());
            }
            if (!"".equals(bondChildPrint)) {
//                System.out.println("Bond: " + curr.getElement() +" and " + bond.getChild().getElement() + " bond weight: " + bond.getWeight() );
                if (bond.getWeight() == 1) {
                    print += ("(" + bondChildPrint + ")");
                } else if (bond.getWeight() == 2) {
                    print += ("(=" + bondChildPrint + ")");
                } else if (bond.getWeight() == 3) {
                    print += ("(#" + bondChildPrint + ")");
                }
            }
        }
        return print;
    }

}
