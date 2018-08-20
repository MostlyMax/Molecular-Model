import java.util.ArrayList;

/**
 * Stores all atoms and bonds. Also contains general input/output functions.
 * 
 *
 */
public class Molecule {

	ArrayList<Atom> molecule = new ArrayList<>();
	ArrayList<Bond> bonds = new ArrayList<>();
	
	Element fake = new Element("x",0,0,0,0);
	Element fake1 = new Element("x1",1000,1000,1000,1000);
	
	Atom central;
	Atom most_electronegative = new Atom(fake);
	Atom least_electronegative = new Atom(fake1);
	int startCharge=0;
	
	int totalElectrons;
	
	public Molecule() {
		
	}
	
	public void addAtom(Atom add) {
		this.molecule.add(add);
	}
	
	/**
	 * Binds 2 atoms together and subtracts 2 electrons from each.
	 * @param temp
	 * @param temp2
	 */
	public void bind(Atom temp, Atom temp2) {
		
		System.out.println("Bonding "+temp.element.symbol+" to "+temp2.element.symbol);
		
		bonds.add(new Bond(temp, temp2));
		temp.bondNum++;
		temp2.bondNum++;
		
			
	}
	
	/**
	 * Counts the number of electrons in the molecule.
	 * @return
	 */
	public int countElectrons() {
		int retVal = 0;
		
		for (Atom temp : molecule) {
			retVal+=temp.electrons;
			retVal+=temp.bondNum;
		}
		totalElectrons = retVal;
		return retVal;
	}
	
	public int countCharge() {
		int retVal = 0;
		
		for (Atom temp : molecule) {
			retVal+=(temp.electrons+temp.bondNum)-temp.element.valenceNum;
		}
		
		return retVal*-1;
	}
	
	/**
	 * Prints where each atom has a bond.
	 */
	public void printMolecule() {
		//System.out.println("total electrons:"+totalElectrons);
		for (Atom temp : molecule) {
			temp.printAtom();
			System.out.print("Bonded to:");
			for (Bond temp2 : bonds) {
				if (temp2.bondOut==temp) System.out.print(temp2.bondMain.element.symbol+" ");
				else if (temp2.bondMain==temp) System.out.print(temp2.bondOut.element.symbol+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Prints molecular formula (sort of)
	 */
	public void printMoleculeBetter() {
		for (Atom temp : molecule) {
			System.out.print(temp.element.symbol);
		}
	}

	/**
	 * Checks which atoms have the least number of bonds.
	 * @return
	 */
	public Atom getBondable() {
		Element fake = new Element("x",0,0,0,0);
		Atom retVal = new Atom(fake);
		
		retVal.electrons=2;
	
		
		for (Atom temp : molecule) {
			if (temp != central) {
				if (temp.electrons>=retVal.electrons) {
					retVal=temp;
					
				}
			}
			
		}
	
		return retVal;
	}
	
	/**
	 * Checks which atom has the lowest electronegativity (central atom).
	 * @return
	 */
	public Atom getCentral() {
		Atom retVal = molecule.get(0);
		
		for (Atom temp : molecule) {
			if (temp.element.electroNeg<retVal.element.electroNeg) retVal = temp;
		}
		
		molecule.remove(retVal);
		molecule.add(0,retVal);
		return retVal;
	}
	
	public void updateExtreme() {
		
		for (Atom temp : molecule) {
			if (temp.element.electroNeg>most_electronegative.element.electroNeg&&!temp.element.symbol.equals("H")) most_electronegative=temp;
			if (temp.element.electroNeg<least_electronegative.element.electroNeg&&!temp.element.symbol.equals("H")) least_electronegative=temp;
		}
		
		System.out.println("Most electronegative:"+most_electronegative.element.symbol);
		System.out.println("Least electronegative:"+least_electronegative.element.symbol);
	}
	
}
