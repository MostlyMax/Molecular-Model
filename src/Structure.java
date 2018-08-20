/**
 * Calculations for Lewis structure algorithm and helper functions.
 * 
 *
 */
public class Structure {
	
	/**
	 * Binds all atoms to central atom.
	 * @param test
	 */
	public static void bindAll(Molecule test) {
		for (Atom temp : test.molecule) {
			if (temp != test.central) {
				System.out.print("bindAll: "+ test.central.element.symbol + " to "+ temp.element.symbol);
				test.bonds.add(new Bond(test.central, temp));
				temp.bondNum++;
				test.central.bondNum++;
				System.out.println();
			}
		}
		
		System.out.println("After binding:");
		test.printMolecule();
		
	}
	
	/**
	 * Gives each atom the number of electrons it needs for a full valence shell.
	 * @param test
	 */
	public static void fillValence(Molecule test) {
		
		for (Atom temp : test.molecule) {
			if (temp!=test.central) temp.electrons=temp.element.octet-temp.bondNum*2;
			
		}
		
		System.out.println("After filling:");
		test.printMolecule();
		
	}
	
	/**
	 * Main lewis structure creator.
	 * @param test
	 */
	public static void makeLewis(Molecule test) {
		int specialNum = 0;
		int adjustNum=0;
		System.out.print("Creating lewis structure for:");
		test.printMoleculeBetter();
		System.out.println();
		
		for (Atom temp : test.molecule) specialNum+=temp.element.valenceNum;
		specialNum+=-1*test.startCharge;
		
		test.updateExtreme();
		
		bindAll(test);
		fillValence(test);
		
		boolean fixer = false;
		int num = (specialNum-test.countElectrons());
		if (specialNum-test.countElectrons()<0) {
			fixer = true;
			int sad = num*-1;
			int j, scale;
			for (int i=0; i<sad; i++) {
				scale=i/test.molecule.size();
				j=i;
				if (i>=test.molecule.size()) j=i-test.molecule.size()*scale;
				if (test.molecule.get(j)!=test.central) test.molecule.get(j).electrons--;
				else sad++;
			}
		}
		else {
			System.out.println("Extra Electrons:"+(specialNum-test.countElectrons()));
			test.central.electrons=specialNum-test.totalElectrons;
		}
		
		if (fixer&&test.molecule.size()==2&&test.molecule.get(1).element.equals(test.central.element)) fixer=false;
		
		
		for (int i=0; i<2; i++) {
			if (test.central==test.least_electronegative) {
				System.out.println("Test 1."+i*5);
				if(test.startCharge>0) adjustNum=test.startCharge;
				if ((test.central.electrons+test.central.bondNum*2)<test.central.element.octet-adjustNum) {
					System.out.println("Test 2."+i*5);
					if (test.central.electrons+test.central.bondNum!=test.central.element.valenceNum) {
						System.out.println("Test 3."+i*5);
						Atom bondTemp = test.getBondable();
						if (!bondTemp.element.symbol.equals("x")) {
							System.out.println("Pass");
							test.bind(test.central, bondTemp);
							bondTemp.electrons=bondTemp.electrons-2;
						}
					}
				}
			}
		}
		
		System.out.println("----------------------------------------------------");
		System.out.println("Complete:");
		int temp = (test.central.electrons+test.central.bondNum)-test.central.element.valenceNum;
		if(fixer) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("This molecule requires a charge of "+(num+test.startCharge)+" as it is currently unstable");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			//test.central.electrons=0;
		}
		else if(temp!=0) {
			System.out.println("*****");
			System.out.print("This molecule is more stable with a ");
			System.out.println((temp+test.startCharge)+" charge.");
			System.out.println("*****");
		}
		
	}

	
}