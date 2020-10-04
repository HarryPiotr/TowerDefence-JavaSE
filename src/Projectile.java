import java.awt.*;
import java.awt.geom.*;

/**
 * Obiekty tej klasy to graficzne reprezentacje strza��w oddawanych przez wie�e.
 * Zale�nie od typu wie�y, z kt�rej ten strza� pochodzi, pocisk ma inn� grubo��,
 * inny kolor i inny czas wy�wietlania. Ka�dy pocisk ma atrybuty b�d�ce wsp�rz�dnymi
 * �r�d�a i celu pocisku.
 * 
 * @author Piotr Anu�kiewicz I7Y1S1
 */

public class Projectile {
	
	private int myX, myY, myTargetX, myTargetY;
	private int myTTL;
	private int myThickness;
	private Color myColor;
	
	/**
	 * W parametrach konstruktora okre�lamy atrybuty pocisku
	 * @param x jego wsp�rz�dna X
	 * @param y jego wsp�rz�dna Y
	 * @param targetX wsp�rz�dna X celu
	 * @param targetY wsp�rz�dna Y celu
	 * @param time czas wy�wietlania pocisku
	 * @param size grubo�� pocisku
	 * @param c kolor pocisku
	 */
	public Projectile(int x, int y, int targetX, int targetY, int time, int size, Color c) {
		myX = x; myY = y; myTargetX = targetX; myTargetY = targetY;
		myTTL = time;
		myThickness = size;
		myColor = c;
	}
	
	/**
	 * Funkcja rysowania pocisku, wywo�ywana przez obiekt typu Tower
	 * @param graphics abstrakcyjny obiekt graficzny podany przez nadrz�dn� metod� rysuj�c�
	 */
	public void drawProjectile(Graphics graphics) {
		graphics.setColor(myColor);
		Graphics2D graphics2d = (Graphics2D)graphics;
		graphics2d.setStroke(new BasicStroke((float)myThickness));
		graphics2d.draw(new Line2D.Float(myX, myY, myTargetX, myTargetY));
		myTTL--;
	}
	
	public int getTTL() {
		return myTTL;
	}
}
