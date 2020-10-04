import javax.swing.*;
import java.awt.*;

/**
 * Tile to pojedyncze pole planszy. Je¿eli jest typu trawa, to mo¿e
 * zawieraæ wie¿ê lub nie.
 * 
 * @author Piotr Anuœkiewicz I7Y1S1
 */

public class Tile extends Rectangle{
	
	int myX, myY, mySize;
	int myType;
	public Tower myTower = null;
	String myPath;
	
	/**
	 * W konstruktorze ustalamy wspó³rzêdne, rozmiar i typ pola planszy
	 */
	public Tile(int x, int y, int size, int type) {
		myX = x; myY = y; mySize = size; myType = type;
		setBounds(x, y, size, size);
	}
	
	/**
	 * Metoda rysuj¹ca pole planszy. Obs³uguje tak¿e stawianie nowych budynków,
	 * usuwianie i ulepszanie ju¿ istniej¹cych
	 */
	public void drawTile(Graphics graphics) {
		graphics.drawImage(new ImageIcon(myPath).getImage(), myX, myY, mySize, mySize, null);
		graphics.drawRect(myX, myY, mySize, mySize);
		
		if(this.contains(Panel.getClick()) && Panel.getSelectedTower() == 5) {
			if(myTower != null && !(myTower.isMyTowerUpgraded()) && Panel.bd.decrementMoney(myTower.getUpgradeCost())) {
				Panel.setClick(new Point(0, 0));
				myTower.upgradeTower();
			}
			else Panel.setClick(new Point(0, 0));
		}
		
		if(this.contains(Panel.getMouse()) && Panel.getSelectedTower() != 5 && myType == 0) {
			
			if(Panel.getSelectedTower() == 3) {
				if(myTower != null) {
					graphics.drawImage(new ImageIcon(List.modelPaths[Panel.getSelectedTower()][0]).getImage(), myX, myY, 80, 80, null);
					if(this.contains(Panel.getClick())) {
						Panel.setClick(new Point(0, 0));
						Panel.tower.remove(myTower);
						myTower = null;
						Panel.setSelectedTower(5);
					}
				}
			}
			else {
				graphics.drawImage(new ImageIcon(List.modelPaths[Panel.getSelectedTower()][0]).getImage(), myX, myY, 80, 80, null);
				
				graphics.setColor(new Color(255, 255, 255, 150));
				graphics.fillRect(myX, myY, 80, 80);
				
				graphics.setColor(Color.BLACK);
				if(this.contains(Panel.getClick()) && this.myTower == null) {
					Panel.setClick(new Point(0, 0));
					if(Panel.bd.decrementMoney(List.towerPrices[Panel.getSelectedTower()])) {
						myTower = new Tower(myX, myY, Panel.getSelectedTower());
						Panel.tower.add(myTower);
						Panel.setSelectedTower(5);
					}
				}
					
			}
		}
		
		if(myTower != null && !(myTower.isMyTowerUpgraded()) && this.contains(Panel.getMouse())) {
			graphics.setColor(Color.YELLOW);
			graphics.drawString("" + myTower.getUpgradeCost(), myX + 2, myY + 14);
			graphics.setColor(Color.BLACK);
		}
	}
	
	public void setImage(String path) {
		myPath = path;
	}
}
