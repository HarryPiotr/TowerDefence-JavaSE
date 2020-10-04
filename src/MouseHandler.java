import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * W metodach tej klasy, implementuj¹cej interfejsy MouseMotionListener i MouseListener,
 * obs³ugugujemy akcje gracza wykonane na interfejsie przy u¿yciu myszki
 * 
 * @author Piotr Anuœkiewicz I7Y1S1
 */

public class MouseHandler implements MouseMotionListener, MouseListener {
	
	public void mousePressed(MouseEvent arg0) {
		Panel.setClick(new Point(arg0.getX(), arg0.getY()));
	}

	public void mouseMoved(MouseEvent arg0) {
		Panel.setMouse(new Point(arg0.getX(), arg0.getY()));
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
