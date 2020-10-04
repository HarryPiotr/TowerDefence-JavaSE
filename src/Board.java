import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;

/**
 * Plansza o wymiarach 8x6, sk�adaj�ca si� z kwadratowych p�l
 * pola dziel� si� na: traw� (na kt�rej mo�na stawia� wie�e), drog� (po kt�rej wrogie pojazdy jad� do hangaru),hangar (kt�rego nale�y broni� przed wrogimi pojazdami)
 * 
 * @author Piotr Anu�kiewicz I7Y1S1
 */

public class Board {

	private int plan[][][] = 
		{
				{
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 1, 1, 1, 0, 0, 0},
					{0, 0, 1, 0, 1, 0, 1, 2},
					{1, 1, 1, 0, 1, 0, 1, 0},
					{0, 0, 0, 0, 1, 1, 1, 0},
					{0, 0, 0, 0, 0, 0, 0, 0}
				},
				{
					{0, 0, 0, 0, 0, 0, 0, 0},
					{1, 1, 0, 1, 1, 1, 0, 0},
					{0, 1, 0, 1, 0, 1, 0, 0},
					{0, 1, 0, 1, 0, 1, 0, 0},
					{0, 1, 1, 1, 0, 1, 1, 2},
					{0, 0, 0, 0, 0, 0, 0, 0}
				},
				{
					{1, 1, 0, 0, 0, 0, 0, 0},
					{0, 1, 1, 0, 0, 0, 0, 0},
					{0, 0, 1, 1, 0, 0, 0, 0},
					{0, 0, 0, 1, 1, 0, 0, 0},
					{0, 0, 0, 0, 1, 1, 0, 0},
					{0, 0, 0, 0, 0, 1, 1, 2}
				},
				{
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 1, 1, 1, 1, 1, 0, 0},
					{0, 1, 0, 1, 0, 1, 0, 0},
					{1, 1, 1, 1, 1, 1, 1, 2},
					{0, 0, 0, 0, 0, 0, 0, 0}
				}				
		};
	private int health = 10;
	private int money = 120;
	private Tile tiles[][] = new Tile[8][6];
	private int tileSize = 80;
	public  int myType;
	
	/**
	 * W konstruktorze ustawiamy tekstury na odpowiednie pola, wed�ug
	 * macierzy zdefiniowanej jako atrybut klasy
	 */
	public Board() {
				
		Random r = new Random();
		myType = r.nextInt(4);
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 6; j++) {
				tiles[i][j] = new Tile(i * tileSize, j * tileSize, tileSize, plan[myType][j][i]);
				if(plan[myType][j][i] == 0) tiles[i][j].setImage("imgs/images/grass.png");
				else 
				if(plan[myType][j][i] == 1) tiles[i][j].setImage("imgs/images/road.png");
				else
				if(plan[myType][j][i] == 2) tiles[i][j].setImage("imgs/images/fortress.png");
			}
		}
	}
	
	public void incrementMoney(int plus) {
		money += plus;
	}
	
	public boolean decrementMoney(int minus) {
		if( money < minus) return false;
		else {
			money -= minus;
			return true;
		}
	}
	
	public int getMoney() {
		return money;
	}
	
	public void takeCasualties() {
		health--;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	/**
	 * Metoda rysuj�ca plansz� tak naprawd� rysuje po kolei wszystkie pola
	 * @param graphics abstrakcyjny obiekt graficzny podany przez nadrz�dn� metod� rysuj�c�
	 */
	public void drawBoard(Graphics graphics) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 6; j++) {
				tiles[i][j].drawTile(graphics);
			}
		}
	}
	
	/**
	 * Metoda rysuj�ca pasek �ycia hangaru
	 * @param graphics abstrakcyjny obiekt graficzny podany przez nadrz�dn� metod� rysuj�c�
	 */
	public void drawHealthBar(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(1, 490, 300, 30);
		
		graphics.setColor(Color.RED);
		graphics.fillRect(6, 495, (290 / 10) * health, 20);
		
		graphics.setColor(Color.WHITE);
		graphics.drawString("" + health + "/10", 11, 510);
	}
	
	/**
	 * Metoda wypisuj�ca ilo�� pozosta�ych pieni�dzy
	 * @param graphics abstrakcyjny obiekt graficzny podany przez nadrz�dn� metod� rysuj�c�
	 */
	public void drawMoneyStatus(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(1, 530, 50, 30);
		
		graphics.setColor(Color.YELLOW);
		graphics.fillRect(6, 535, 40, 20);
		
		graphics.setColor(Color.BLACK);
		graphics.drawString("$" + money, 11, 550);
	}
	
	public void drawSignature(Graphics graphics) {
		graphics.setColor(Color.BLACK);		
		graphics.drawString("Piotr Anuskiewicz, I7Y1S1", 100, 550);
	}	
}
