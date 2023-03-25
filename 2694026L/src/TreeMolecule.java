//Fong Wai Lam (2694026L)

//add import statement



public class TreeMolecule implements Molecule {

    private Atom first;

    public TreeMolecule(Atom atom) {
        first = atom;
    }

    @Override
    public boolean addBond(Atom a1, Atom a2, int strength) {
        System.out.println("Add " + a2.getElement() + "(a2) to " + a1.getElement() + " (a1).");
        int hBondNo = a1.countHBond();
        if (hBondNo >= strength) {
//            System.out.println(a1.getElement() + "(a1) has enough hydrogen bond for strength " + strength);
            a1.addAtom(a2, strength);
            return true;
        }
//        System.out.println(a1.getElement() + " (a1) does not enough hydrogen bond for strength " + strength);
        return false;
    }

    @Override
    public boolean contains(Atom a) {
        return first.contains(a);
    }

    @Override
    public String smilesString() {
        String smiles = "";
        smiles = first.smiles(smiles);
        return smiles;
    }

    @Override
    public String structuralFormula() {
        String structuralFormula = "";
        structuralFormula = first.structuralFormula(structuralFormula);
        return structuralFormula;
    }


    //add constructor, implemented methods and any helper methods you require




}
