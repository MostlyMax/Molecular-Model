import java.util.ArrayList;

/**
 * These are the "elements" that can be modified.
 * Did this so that we could have multiple of the same element have different instances.
 *
 */
public class Atom {
	
	Element element;

	int bondNum;
	int electrons;
	
	//ArrayList<Electron> electrons = new ArrayList<>();
	
	public Atom(Element element) {
		this.element = element;
	}
	
	public void printAtom() {
		System.out.print(element.symbol+":");
		System.out.print("Number of VE-"+electrons+", # of bonds-"+bondNum+",");
		
		
		
		
	}

}
