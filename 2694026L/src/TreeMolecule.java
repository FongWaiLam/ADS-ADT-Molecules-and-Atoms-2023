// Name: Fong Wai Lam (GUID: 2694026L)

/**
 * The TreeMolecule class implements the Molecule interface and
 * therefore implements the following four methods:
 *  1. addBond(Atom a1, Atom a2, int strength)
 *  2. contains(Atom target)
 *  3. smilesString()
 *  4. structuralFormula()
 *
 *  There are six helper methods which help the implementation of above methods:
 *  1. countHBond(Atom atom)
 *  2. contains(Atom curr, Atom target)
 *  3. smiles(Atom curr)
 *  4. structuralFormula(Atom curr)
 *  5. traverseWithoutBracket(String printType, Atom curr)
 *  6. traverseWithBracket(String printType, Atom curr)
 */
public class TreeMolecule implements Molecule {

    private Atom first;

    public TreeMolecule(Atom atom) {
        first = atom;
    }

    /**
     *  A method implemented from interface Molecule
     *  to add a new bond between atom a1 in the molecule and a new atom a2
     */
    @Override
    public boolean addBond(Atom a1, Atom a2, int strength) {
        int a1HBondNo = countHBond(a1);
        int a2HBondNo = countHBond(a2);
        if (a1HBondNo >= strength && a2HBondNo >= strength) {
            a1.addBond(a2, strength);
            return true;
        }
        return false;
    }

    /**
     *  Helper Method to count the H bond number
     */
    public int countHBond(Atom atom) {
        int countOfHBond = 0;
        for (Bond bond : atom.getBonds()) {
            if (bond.getChild().getElement().equals("H")) {
                countOfHBond++;
            }
        }
        return countOfHBond;
    }

    /**
     *  A method implemented from interface Molecule
     *  to check whether target atom is part of this molecule
     */
    @Override
    public boolean contains(Atom target) {
        return contains(first, target);
    }

    /**
     *  Helper method for contains(Atom target)
     *  to recursively changing the current atom (curr)
     */
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

    /**
     *  A method implemented from interface Molecule
     *  to return a Smiles String of the molecule
     */
    @Override
    public String smilesString() {
        String smiles = "";
        smiles = smiles(first);
        return smiles;
    }

    /**
     *  Helper method for smilesString()
     *  to recursively changing the current atom (curr)
     */
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

    /**
     *  A method implemented from interface Molecule
     *  to return a Structural Formula of the molecule
     */
    @Override
    public String structuralFormula() {
        String structuralFormula = "";
        structuralFormula = structuralFormula(first);
        return structuralFormula;
    }

    /**
     *  Helper method for structuralFormula()
     *  to recursively changing the current atom (curr)
     */
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
            structuralFormula += traverseWithoutBracket("structural", curr);
            // More than 1 bond
        } else if (curr.getBonds().size() - countHBond(curr) > 1) {
            structuralFormula += traverseWithBracket("structural", curr);
        }
        return structuralFormula;
    }


    /**
     *  Helper method for traversing each atom in the bond
     *  and recursively calling targeted method according to the printType
     *
     *  The targeted methods are :
     *  - smiles(Atom curr)
     *  - structuralFormula(Atom curr)
     */
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


    /**
     *  Helper method for traversing each atom in the bond
     *  and recursively calling targeted method according to the printType
     *  The targeted methods are :
     *  - smiles(Atom curr)
     *  - structuralFormula(Atom curr)
     */
    public String traverseWithBracket(String printType, Atom curr) {
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
