/**
 * Contains general information about an element.
 * Never modified.
 *
 */
public class Element {

	int atomicNum;
	
	int valenceNum;
	int octet;
	double electroNeg;
	
	String symbol;
	
	public Element(String symbol,int atomicNum, int valenceNum, double electroNeg, int octet) {
		this.symbol = symbol;
		this.atomicNum = atomicNum;
		
		this.valenceNum = valenceNum;
		this.electroNeg = electroNeg;
		
		this.octet = octet;

		
//		for (int i = 0; i<valenceNum; i++) {
//			electrons.add(new Electron(this));
//		}
		
	}
	
	
	
}
