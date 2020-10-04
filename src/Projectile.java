import java.awt.*;
import java.awt.geom.*;

/**
 * Obiekty tej klasy to graficzne reprezentacje strza³ów oddawanych przez wie¿e.
 * Zale¿nie od typu wie¿y, z której ten strza³ pochodzi, pocisk ma inn¹ gruboœæ,
 * inny kolor i inny czas wyœwietlania. Ka¿dy pocisk ma atrybuty bêd¹ce wspó³rzêdnymi
 * Ÿród³a i celu pocisku.
 * 
 * @author Piotr Anuœkiewicz I7Y1S1
 */

public class Projectile {
	
	private int myX, myY, myTargetX, myTargetY;
	private int myTTL;
	private int myThickness;
	private Color myColor;
	
	/**
	 * W parametrach konstruktora okreœlamy atrybuty pocisku
	 * @param x jego wspó³rzêdna X
	 * @param y jego wspó³rzêdna Y
	 * @param targetX wspó³rzêdna X celu
	 * @param targetY wspó³rzêdna Y celu
	 * @param time czas wyœwietlania pocisku
	 * @param size gruboœæ pocisku
	 * @param c kolor pocisku
	 */
	public Projectile(int x, int y, int targetX, int targetY, int time, int size, Color c) {
		myX = x; myY = y; myTargetX = targetX; myTargetY = targetY;
		myTTL = time;
		myThickness = size;
		myColor = c;
	}
	
	/**
	 * Funkcja rysowania pocisku, wywo³ywana przez obiekt typu Tower
	 * @param graphics abstrakcyjny obiekt graficzny podany przez nadrzêdn¹ metodê rysuj¹c¹
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
