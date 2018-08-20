import java.awt.Container;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

public class Main {
	
	static int allElectrons = 0;
	static Hashtable<String, Element> periodicTable = new Hashtable<String, Element>();
	public static Molecule test = new Molecule();

	public static void main(String[] args) {
		JFrame f = new JFrame("Model");
		f.setSize(500, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		Container cp = f.getContentPane();
		
		
		periodicTable.put("H", new Element("H", 1, 1, 10.00, 2));
		periodicTable.put("B", new Element("B", 5, 3, 2.04, 8));
		periodicTable.put("C", new Element("C", 6, 4, 2.55, 8));
		periodicTable.put("N", new Element("N", 7, 5, 3.04, 8));
		periodicTable.put("O", new Element("O", 8, 6, 3.44, 8));
		periodicTable.put("F", new Element ("F", 9, 7, 3.98, 8));
		
		periodicTable.put("Al", new Element ("Al", 13, 3, 1.61, 8));
		periodicTable.put("Si", new Element ("Si", 14, 4, 1.90, 8));
		periodicTable.put("P", new Element("P", 15, 5, 2.19, 20));
		periodicTable.put("S", new Element("S", 16, 6, 2.58, 8));
		periodicTable.put("Cl", new Element("Cl", 17, 7, 3.16, 8));
		
		periodicTable.put("As", new Element("As", 33, 5, 2.18, 8));
		periodicTable.put("Se", new Element("Se", 34, 6, 2.55, 8));
		periodicTable.put("Br", new Element("Br", 35, 7, 2.96, 8));
		
		periodicTable.put("I", new Element("I", 53, 7, 2.66, 8));
		
		getInput();
		
		test.central = test.getCentral();
		
		Structure.makeLewis(test);
		test.printMolecule();
		
		
		Model model = new Model(f);
		cp.add(model);
		
		f.setVisible(true);
		
		//clearMolecule(test);
		
		System.out.println("----------------------------------------------------");
		
	}
	
	public static void clearMolecule(Molecule clear) {
		for (Atom temp : clear.molecule) {
			temp.electrons = 0;
			temp.bondNum = 0;
		}
		clear.molecule.clear();
		clear.bonds.clear();
		clear.startCharge = 0;
		
		clear.most_electronegative = new Atom(clear.fake);
		clear.least_electronegative = new Atom(clear.fake1);
	}
	
	public static void getInput() {
		System.out.print("Enter a molecular formula: ");
		
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		
		Pattern pattern = Pattern.compile("[a-zA-Z][A-Z]");
		Matcher match = pattern.matcher(input);
		
		while(match.find()) {
			input = input.substring(0, match.start()+1)+"1"+input.substring(match.start()+1);
			match = pattern.matcher(input);
		}
		
		if (!Character.isDigit(input.charAt(input.length()-1))) input = input +"1";
		
		//System.out.print(input);
		makeMolecule(input);
		
		System.out.print("Enter charge: ");
		test.startCharge = sc.nextInt();
		
		sc.close();
		
		
	}
	
	public static void makeMolecule(String input) {
		
		Scanner SCname = new Scanner(input);
		Scanner SCamount = new Scanner(input);
		SCamount.useDelimiter("\\D*");
		SCname.useDelimiter("\\d");

		while (SCname.hasNext()) {
			String name = SCname.next();
			int amount = SCamount.nextInt();
			
			for (int i=0; i<amount; i++) {
			test.addAtom(new Atom(periodicTable.get(name)));
			}
		
		}
		
		SCname.close();
		SCamount.close();
	
	}

}
