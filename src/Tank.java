import java.awt.*;

import javax.swing.ImageIcon;

/**
 * Ka¿dy z przeciwników jest obiektem tej klasy. Zawiera atrybuty, takie jak prêdkoœæ, wspó³rzêdne,
 * a tak¿e metody obs³uguj¹ce ruch po planszy, bycie trafionym przez wie¿ê
 * 
 * @author Piotr Anuœkiewicz I7Y1S1
 */

public class Tank{
	
	public boolean isAlive = false;
	private int myX, myY;
	private Point myMiddle;
	private int myDirection = 1;
	private int part = 0;
	private int health;
	private int myType;
	private int myReward;
	private int mySpeed;
	private String myPath[] = new String[3];
	
	/**
	 * W konstruktorze ustawiamy atrybuty przeciwnika zgodnie z danymi zapisanymi w atrybutach statycznych klasy {@link List}
	 * 
	 * @param type typ przeciwnika - ma wp³yw na atrybuty takie jak prêdkoœæ, nagroda za pokonanie itp.
	 */
	public Tank(int type, int x, int y) {
		myType = type;
		health = List.enemyHealthList[myType];
		myReward = List.enemyRewards[myType];
		mySpeed = List.enemySpeeds[myType];
		myPath = List.enemyModels[myType];
		myX = x;
		myY = y;
		myMiddle = new Point(myX + 40, myY + 40);
	}
	
	/**
	 * Metoda przemieszczaj¹ca przeciwnika o zadany wektor [x, y]
	 */
	public void moveTank(int x, int y) {
		myX += x;
		myY += y;
		
		myMiddle.translate(x, y);
	}
	
	/**
	 * Metoda obs³uguj¹ca otrzymanie obra¿eñ przez przeciwnika. 
	 * W przypadku utraty wszystkich punktów zdrowia wyp³aca równie¿ nagrodê i 
	 * usuwa przeciwnika.
	 * 
	 * @param dmg iloœæ otrzymanych obra¿eñ
	 */
	public void getShot(int dmg) {
		health -= dmg;
		if(health <= 0) {
			//tutaj obs³uga œmierci adwersarza
			health = 0;
			isAlive = false;
			Panel.bd.incrementMoney(myReward);
		}
	}
	
	/**
	 * Metoda rysuj¹ca przeciwnika. Rysowani s¹ tylko "¿ywi" przeciwnicy, zwróceni w odpowiednim kierunku.
	 * Ponadto nad ka¿dym przeciwnikiem rysowany jest jego pasek zdrowia.
	 */
	public void drawTank(Graphics graphics) {
		
		if(isAlive) {
			switch(myDirection) {
			case 0:
				graphics.drawImage(new ImageIcon(myPath[0]).getImage(), myX, myY, 80, 80, null);
				break;
			case 1:
				graphics.drawImage(new ImageIcon(myPath[1]).getImage(), myX, myY, 80, 80, null);
				break;
			case 2:
				graphics.drawImage(new ImageIcon(myPath[2]).getImage(), myX, myY, 80, 80, null);
				break;
			}
		}
		
		//rysujemy paski ¿ycia
		graphics.setColor(Color.RED);
		graphics.fillRect(myX + 15, myY, 50, 5);
		
		graphics.setColor(Color.GREEN);
		graphics.fillRect(myX + 15, myY, (int)((50.0 / (double)(List.enemyHealthList[myType])) * (double)health), 5);
		
	}
	
	public int getMyType() {
		return myType;
	}
	
	public Point getMiddle() {
		return myMiddle;
	}
	
	public void turnTank(int direction) {
		myDirection = direction;
	}
	
	public int getSpeed() {
		return mySpeed;
	}

	public void setSpeed(int speed) {
		this.mySpeed = speed;
	}

	public int getMyX() {
		return myX;
	}

	public void setMyX(int myX) {
		this.myX = myX;
	}

	public int getMyY() {
		return myY;
	}

	public void setMyY(int myY) {
		this.myY = myY;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}

	public int getMyDirection() {
		return myDirection;
	}

	public void setMyDirection(int myDirection) {
		this.myDirection = myDirection;
	}
}
