import javax.swing.*;
import java.awt.*;

/**
 * Ramka, w której znajduje siê interfejs graficzny aplikacji
 * 
 * @author Piotr Anuœkiewicz I7Y1S1
 */

public class Frame extends JFrame {
	
	public Frame() {
		super("Tower Defence PAI7Y1");
		setSize(646, 610);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setLayout(new GridLayout(1, 1, 0, 0));
		
		Panel panel = new Panel(this);
		add(panel);
		
		setVisible(true);
	}
}
