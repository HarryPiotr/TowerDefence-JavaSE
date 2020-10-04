import java.awt.*;
import javax.swing.*;

/**
 * Ka¿dy z czterech przycisków na interfejsie jest obiektem poni¿szej klasy
 * 
 * @author Piotr Anuœkiewicz I7Y1S1
 */

public class ListButton extends Rectangle {
	
	private int myX, myY, mySizeX, mySizeY;
	private int myID;
		
	public ListButton(int x, int y, int sx, int sy, int index) {
		myX = x; myY = y; mySizeX = sx; mySizeY = sy;
		myID = index;
		setBounds(x, y, sx, sy);
	}

	/**
	 * Metoda rysuj¹ca przycisk obs³uguje tak¿e zaznaczenie (podœwietlenie) klikniêtego przycisku
	 * @param graphics abstrakcyjny obiekt graficzny podany przez nadrzêdn¹ metodê rysuj¹c¹
	 */
	public void drawButton(Graphics graphics) {
				
		if(this.contains(Panel.getClick())) {
			Panel.setClick(new Point(0,0));
			if(Panel.getSelectedTower() == myID) {
				Panel.setSelectedTower(5);
			}
			else {
				Panel.setSelectedTower(myID);
			}
		}
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(myX - 2, myY - 2, mySizeX + 4, mySizeY + 4);
		graphics.drawImage(new ImageIcon(List.iconPaths[myID]).getImage(), myX, myY, 80, 80, null);
		
		if(Panel.getSelectedTower() == myID) {
			graphics.setColor(new Color(255, 255, 255, 150));
			graphics.fillRect(myX, myY, 80, 80);
		}
	}
}
