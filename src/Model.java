import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class Model extends JComponent{
	JFrame f;
	
	public Model(JFrame f) {
		this.f = f;
	}
	
	  public void paintComponent(Graphics g) {
	      if(g instanceof Graphics2D){
	    	  	
	    	  	int x, y,x2, y2, offSetX, offSetY;
	    	  	double angle, angle2; 
	        Graphics2D g2 = (Graphics2D)g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);

	        angle = 360/(Main.test.molecule.size()-1);
	        offSetX = 5;
	        offSetY = -5;
	        		
	        //draws surrounding atoms
	        for (int i=1; i<Main.test.molecule.size(); i++) {
	        		Atom temp = Main.test.molecule.get(i);
	        	
	        		x = (int)(f.getWidth()/2+(Math.cos(Math.toRadians(angle*(i-1)))*50));
	        		y = (int)(f.getHeight()/2+(Math.sin(Math.toRadians(angle*(i-1)))*50));
	       
	        		g2.setColor(Color.BLACK);
	        		if(temp.bondNum>1) {
	        			offSetX = 8;
	        			offSetY = -8;
		        		for (int b=1; b<=temp.bondNum; b++) {
		        			g2.drawLine(f.getWidth()/2+(offSetX/temp.bondNum)*b, f.getHeight()/2+(offSetY/temp.bondNum)*b, x+(offSetX/temp.bondNum)*b, y+(offSetY/temp.bondNum)*b);
		        		}
	        		}
	        		else g2.drawLine(f.getWidth()/2+offSetX, f.getHeight()/2+offSetY, x+offSetX, y+offSetY);
	        		
	        		g2.fillOval(x-10, y-19, 30, 30);
	        		g2.setColor(Color.white);
	        		g2.drawString(temp.element.symbol, x, y);
	        		g2.setColor(Color.yellow);
	        		
	        		//Electrons
	        		for (int j=0; j<temp.electrons; j++) {
	        			//System.out.println("Drawing electron for "+Main.test.molecule.get(i).element.symbol);
	        			angle2 = (360/temp.electrons);
	        			x2 = (int)(x+2+(Math.cos(Math.toRadians(angle2*(j-1)))*10));
		        		y2 = (int)(y-7+(Math.sin(Math.toRadians(angle2*(j-1)))*10));
		        		g2.fillOval(x2, y2, 5, 5);
	        		}  		
	        		
	        }
	        
	        //Draws center atom
	        g2.setColor(Color.black);
	        g2.fillOval(f.getWidth()/2-10, f.getHeight()/2-19, 30, 30);
	        g2.setColor(Color.white);
	        g2.drawString(Main.test.central.element.symbol,f.getWidth()/2,f.getHeight()/2);
	        g2.setColor(Color.YELLOW);
	        //Electrons
	        for (int j=0; j<Main.test.central.electrons; j++) {
	    			//System.out.println("Drawing electron");
	    			angle2 = (360/Main.test.central.electrons);
	    			x2 = (int)(f.getWidth()/2+2+(Math.cos(Math.toRadians(angle2*(j-1)))*10));
	        		y2 = (int)(f.getHeight()/2-7+(Math.sin(Math.toRadians(angle2*(j-1)))*10));
	        		g2.fillOval(x2, y2, 5, 5);
    			}
	        
	      }
	  }
	  


}
