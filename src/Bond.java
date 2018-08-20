/**
 * Just keeps track of bonds, which atom is bonded to which.
 * Not really sure why I made this a separate class, will figure out later.
 *
 */
public class Bond {
	
	Atom bondMain;
	Atom bondOut;
	
	public Bond(Atom bondMain, Atom bondOut) {
		this.bondMain = bondMain;
		this.bondOut = bondOut;
	}
	

}
