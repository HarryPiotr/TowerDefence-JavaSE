import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Ka�da wie�a jest obiektem poni�szej klasy. Ma swoje atrybuty (jak obra�enia, zasi�g),
 * kt�re definiowane s� w konstruktorze zgodnie z atrybutami statycznymi klasy List
 * 
 * @author Piotr Anu�kiewicz I7Y1S1
 */

public class Tower extends Rectangle {

	private int myX, myY;
	private Point myMiddle;
	private int myRange;
	private int myDamage;
	String myPath;
	private int cooldown = 0;
	private int myCooldownCap;
	private Projectile projectile;
	private ArrayList<Projectile> projectileList = new ArrayList<>();
	private boolean isUpgraded = false;
	private int upgradeCost;
	private int myType;
	
	public Tower(int x, int y, int type) {
		myX = x;
		myY = y;
		myMiddle = new Point(myX + 40, myY + 40);
		myType = type;
		myPath = List.modelPaths[type][0];
		myRange = List.towerRanges[type][0];
		myDamage = List.towerDamages[type][0];
		myCooldownCap = List.towerCooldowns[type][0];
		upgradeCost = List.towerUpgradeCosts[type];
		setBounds(myX, myY, 80, 80);
	}
	
	/**
	 * Metoda rysuj�ca wie��, a tak�e pocisk (lub pociski) wystrzelone przez t� wie��.
	 * Pociski maj� atrybut TTL, kt�ry okre�la, jak d�ugo jeszcze b�d� wy�wietlane.
	 */
	public void drawTower(Graphics graphics) {
		
		graphics.drawImage(new ImageIcon(myPath).getImage(), myX, myY, 80, 80, null);
		
		if(myType != 2 && projectile != null) {
			projectile.drawProjectile(graphics);
			if(projectile.getTTL() == 0) projectile = null;
		}
		else
		if(myType == 2) {
			for(int i = 0; i < projectileList.size(); i++) {
				projectileList.get(i).drawProjectile(graphics);
				if(projectileList.get(i).getTTL() == 0) projectileList.remove(i);
			}
		}
		
		if(this.contains(Panel.getMouse())) {
			graphics.setColor(new Color(255, 255, 255, 75));
			graphics.fillOval(myX + (40 - myRange), myY + (40 - myRange), myRange * 2, myRange * 2);
		}
		
		if(cooldown > 0) cooldown--;
	}
	
	/**
	 * Obs�uguje strza� do obiektu klasy Tank (czyli przeciwnika).
	 * Wie�a ma atrybut cooldown, kt�ry okre�la, ile klatek animacji musi jeszcze min�� do jej kolejnego wystrza�u.
	 * Jeden rodzaj wie�y (typu 1) mo�e strzeli� z jednego z czterech "dzia�" na swoim modelu.
	 * Pozosta�e strzelaj� ze swojego "�rodka".
	 * 
	 * Efektem strza�u jest ustawienie atrybutu projectile lub projectileList (zale�nie od typu wie�y), a tak�e trafienie przeciwnika.
	 * 
	 * @param startReloading okre�la, czy po strzale ustawi� atrybut cooldown
	 */
	public boolean shootAt(Tank tank, boolean startReloading) {
		if(cooldown == 0) {
			Random r = new Random();
			int spreadX = r.nextInt(20) - 10;
			int spreadY = r.nextInt(20) - 10;
			
			tank.getShot(myDamage);
			
			if(startReloading) cooldown = myCooldownCap;
			if(myType == 1) {
				switch(r.nextInt(4)) {
				case 0:
					projectile = new Projectile(myX + 23, myY + 23, tank.getMyX() + 40 + spreadX, tank.getMyY() + 40 + spreadY, List.towerBeamTTL[myType], List.towerBeamThickness[myType], List.towerBeamColors[myType]);
					break;
				case 1:
					projectile = new Projectile(myX + 23, myY + 56, tank.getMyX() + 40 + spreadX, tank.getMyY() + 40 + spreadY, List.towerBeamTTL[myType], List.towerBeamThickness[myType], List.towerBeamColors[myType]);
					break;
				case 2:
					projectile = new Projectile(myX + 56, myY + 23, tank.getMyX() + 40 + spreadX, tank.getMyY() + 40 + spreadY, List.towerBeamTTL[myType], List.towerBeamThickness[myType], List.towerBeamColors[myType]);
					break;
				case 3:
					projectile = new Projectile(myX + 56, myY + 56, tank.getMyX() + 40 + spreadX, tank.getMyY() + 40 + spreadY, List.towerBeamTTL[myType], List.towerBeamThickness[myType], List.towerBeamColors[myType]);
					break;
				}
			}
			else 
			if(myType == 2) {
				projectileList.add(new Projectile(myX + 40, myY + 40, tank.getMyX() + 40 + spreadX, tank.getMyY() + 40 + spreadY, List.towerBeamTTL[myType], List.towerBeamThickness[myType], List.towerBeamColors[myType]));
			}
			else projectile = new Projectile(myX + 40, myY + 40, tank.getMyX() + 40 + spreadX, tank.getMyY() + 40 + spreadY, List.towerBeamTTL[myType], List.towerBeamThickness[myType], List.towerBeamColors[myType]);
		return true;
		}
		else return false;
	}
		
	/**
	 * Wyszukuje przeciwnik�w, kt�rzy znajduj� si� w zasi�gu wie�y, a je�eli takowego znajdzie, oddaje do niego strza�
	 * (lub szuka kolejnego celu, jak w przypadku wie�y typu 2)
	 */
	public void seekEnemies(Wave wv) {
		
		ArrayList<Tank> tank = wv.getTankList();
		boolean didIShoot = false;
		
		if(myType == 2) {
			/**
			 * Najdro�sza wie�a wystrzeliwuje do 4 pocisk�w na raz
			 */
			int shotsLeft = 4;
			for(int i = 0; i < tank.size(); i++) {
				if(tank.get(i).isAlive && tank.get(i).getMiddle().distance(myMiddle) < (double) myRange) {
					didIShoot = shootAt(tank.get(i), false);
					shotsLeft--;
					if(shotsLeft == 0) break;
				}
			}
			if(didIShoot) {
				cooldown = myCooldownCap;
			}
		}
		else {
			/**
			 * Pozosta�e wie�e wystrzeliwuj� po jednym pocisku
			 */
			//System.out.println("(" + myMiddle.getX() + ", " + myMiddle.getY() + ")");
			for(int i = 0; i < tank.size(); i++) {
				if(tank.get(i).isAlive && tank.get(i).getMiddle().distance(myMiddle) < (double) myRange) {
					shootAt(tank.get(i), true);
				}
			}
		}		
	}
	
	public void upgradeTower() {
		myPath = List.modelPaths[myType][1];
		myRange = List.towerRanges[myType][1];
		myDamage = List.towerDamages[myType][1];
		myCooldownCap = List.towerCooldowns[myType][1];
		isUpgraded = true;
	}
	
	public boolean isMyTowerUpgraded() {
		return isUpgraded;
	}
	
	public int getUpgradeCost() {
		return upgradeCost;
	}
}
