
import java.util.ArrayList;

//to test the Molecule implementation
public class TestMolecule {

    //helper methods//
    //create an arraylist of atoms of the appropriate type
    public static  ArrayList<Atom> atomList(int len, String type){
        ArrayList<Atom> theAtoms = new ArrayList<Atom>();
        for(int i=0;i<len;i++) theAtoms.add(new Atom(type));//supply of atoms of the specified type
        return theAtoms;

    }

    //these are so that I have some stored, nothing to do with the actual implementation
    public static Molecule createMolecule(String name, ArrayList<Atom> carbonAtoms, ArrayList<Atom> oxygenAtoms, ArrayList<Atom> nitrogenAtoms) {


        Molecule mol = new TreeMolecule(carbonAtoms.get(0));//all molecules contain an initial carbon atom

        if(name.equals("methane")) return mol;
        if(name.equals("ethane")) {
            mol.addBond(carbonAtoms.get(0), carbonAtoms.get(1), 1);
            return mol;
        }

        if(name.equals("ethene")) {
            mol.addBond(carbonAtoms.get(0), carbonAtoms.get(1), 2);
            return mol;
        }

        if(name.equals("ethyne")) {
            mol.addBond(carbonAtoms.get(0), carbonAtoms.get(1), 3);
            return mol;
        }

        if(name.equals("dimethyl ether")) {
            mol.addBond(carbonAtoms.get(0), oxygenAtoms.get(0), 1);
            mol.addBond(oxygenAtoms.get(0), carbonAtoms.get(1), 1);

            return mol;
        }

        if(name.equals("ethanol")) {
            mol.addBond(carbonAtoms.get(0), carbonAtoms.get(1), 1);
            mol.addBond(carbonAtoms.get(1), oxygenAtoms.get(0), 1);

            return mol;
        }

        if(name.equals("acetaldehyde")) {
            mol.addBond(carbonAtoms.get(0), carbonAtoms.get(1), 1);
            mol.addBond(carbonAtoms.get(1), oxygenAtoms.get(0), 2);

            return mol;
        }

        if(name.equals("hydrogen cyanide")) {
            mol.addBond(carbonAtoms.get(0), nitrogenAtoms.get(0), 3);
            return mol;
        }

        if(name.equals("isobutyl alcohol")) {
            mol.addBond(carbonAtoms.get(0), carbonAtoms.get(1), 1);
            mol.addBond(carbonAtoms.get(1), carbonAtoms.get(2), 1);
            mol.addBond(carbonAtoms.get(1), carbonAtoms.get(3), 1);
            mol.addBond(carbonAtoms.get(3), oxygenAtoms.get(0), 1);

            return mol;
        }

        if(name.equals("5-amino-4-methylpentanamide")) {
            mol.addBond(carbonAtoms.get(0), carbonAtoms.get(1), 1);
            mol.addBond(carbonAtoms.get(1), carbonAtoms.get(2), 1);
            mol.addBond(carbonAtoms.get(2), carbonAtoms.get(3), 1);
            mol.addBond(carbonAtoms.get(3), carbonAtoms.get(4), 1);

            mol.addBond(carbonAtoms.get(4), oxygenAtoms.get(0), 2);
            mol.addBond(carbonAtoms.get(4), nitrogenAtoms.get(0), 1);
            mol.addBond(carbonAtoms.get(1), carbonAtoms.get(5), 1);
            mol.addBond(carbonAtoms.get(5), nitrogenAtoms.get(1), 1);

            return mol;
        }

        // Self-made Molecules
        ArrayList<Atom> boronAtoms = atomList(10, "B");
        ArrayList<Atom> fluorineAtoms = atomList(10, "F");
        ArrayList<Atom> phosphorousAtoms = atomList(10, "P");
        ArrayList<Atom> sulphurAtoms = atomList(10, "S");
        ArrayList<Atom> ChlorineAtoms = atomList(10, "Cl");
        ArrayList<Atom> bromineAtoms = atomList(10, "Br");

        if(name.equals("C(#B)(C(=O)(Fl))")) {
            mol.addBond(carbonAtoms.get(0), boronAtoms.get(0), 3); // C(0): 4, B(0): 3, Strength: 3, C(0) left: 1
            mol.addBond(carbonAtoms.get(0), carbonAtoms.get(1), 1);// C(0): 1, C(1): 4, Strength: 1, C(1) left: 3
            mol.addBond(carbonAtoms.get(1), oxygenAtoms.get(0), 2);// C(1): 3, O(0): 2, Strength: 2, C(1) left: 1
            mol.addBond(carbonAtoms.get(1), fluorineAtoms.get(0), 1);// C(1): 1, Fl(0): 1, Strength: 1

            // Fl - S this bond will not appear
            boolean result = mol.addBond(fluorineAtoms.get(0), sulphurAtoms.get(0), 2);// Fl(0): 0, S(0): 2, Strength: 2 // False
            System.out.println("Fl - S add bond result : " + result);
            return mol;
        }

        if(name.equals("PH(Cl)(C(=O)(CH(Br)(CH2NH2)))")) {
            mol = new TreeMolecule(phosphorousAtoms.get(0));
            mol.addBond(phosphorousAtoms.get(0), ChlorineAtoms.get(0), 1); // P(0): 3, Cl(0): 1, Strength: 1, P(0) left: 2
            mol.addBond(phosphorousAtoms.get(0), carbonAtoms.get(0), 1);// P(0): 2, C(0): 4, Strength: 1, P(0) left: 1, C(0) left: 3
            mol.addBond(carbonAtoms.get(0), oxygenAtoms.get(0), 2);// C(0): 3, O(0): 2, Strength: 2, C(0) left: 1
            mol.addBond(carbonAtoms.get(0), carbonAtoms.get(1), 1);// C(0): 1, C(1): 4, Strength: 1, C(1) left: 3
            mol.addBond(carbonAtoms.get(1), bromineAtoms.get(0), 1);// C(1): 3, Br(0): 1, Strength: 1, C(1) left: 2
            mol.addBond(carbonAtoms.get(1), carbonAtoms.get(2), 1);// C(1): 2, C(2): 4, Strength: 1, C(1) left: 1, C(2) left: 3
            mol.addBond(carbonAtoms.get(2), nitrogenAtoms.get(0), 1);// C(2): 3, N(0): 3, Strength: 1, C(2) left: 2, N(0) left: 2

            // N - C this bond will not appear
            boolean result = mol.addBond(nitrogenAtoms.get(0), carbonAtoms.get(3), 3);// N(0): 20, C(3): 4, Strength: 3 // False
            System.out.println("N - C add bond result : " + result);
            return mol;
        }

        return null;

    }

    //choose a molecule, build it and output strings
    public static void main(String[] args) {

//        System.out.println(Atom.createMap().get("H"));

        //create a supply of atoms - amend as necessary
        ArrayList<Atom> carbonAtoms = atomList(10, "C");//supply of carbon atoms
        ArrayList<Atom> oxygenAtoms = atomList(10, "O");//supply of oxygen atoms
        ArrayList<Atom> nitrogenAtoms = atomList(10, "N");//supply of nitrogen atoms

        String name = "PH(Cl)(C(=O)(CH(Br)(CH2NH2)))";
        Molecule mol = createMolecule(name, carbonAtoms, oxygenAtoms, nitrogenAtoms);

        System.out.println(name + " has smiles string " + mol.smilesString());
        System.out.println(name + " has structural formula " + mol.structuralFormula());

        boolean containsAtom = mol.contains(carbonAtoms.get(2));
        String output = "";
        if(containsAtom) output+=" contains ";
        else output+=" doesn't contain ";
        output+="the atom in question";
        System.out.println(name+ "" + output);


    }




}
