import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Panel, czyli interfejs u¿ytkownika i plansza gry
 * 
 * @author Piotr Anuœkiewicz I7Y1S1
 */

public class Panel extends JPanel implements Runnable{
	
	public static Frame fr;
	public static Board bd;
	public static List ls;
	
	private static Wave crWv;
	private Wave wave[] = new Wave[11];
	private int waitTime = 200;
	private static Point mouse = new Point(0, 0);
	private static Point click = new Point(0, 0);
	private static int selectedTower = 5;
	
	public static ArrayList<Tower> tower = new ArrayList<>();
	
	/**
	 * W konstruktorze tworzymy planszê, interfejs u¿ytkownika, 
	 * dodajemy obs³ugê myszki przy u¿yciu utworzonej przez nas klasy MouseHandler,
	 * definiujemy kolejne fale przeciwników w grze, a tak¿e uruchamiamy nowy w¹tek, który
	 * bêdzie wysy³a³ kolejne fale przeciwników i rysowa³ interfejs oraz planszê
	 * 
	 * @param f ramka, w której zawiera siê panel
	 */
	
	public Panel(Frame f) {
		fr = f;
		
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseHandler());
		
		bd = new Board();
		ls = new List(307, 490, 80, 80);
			
		wave[0] = new Wave(bd, 10, 0, 0);
		wave[1] = new Wave(bd, 0, 20, 0);
		wave[2] = new Wave(bd, 10, 10, 0);
		wave[3] = new Wave(bd, 0, 0, 5);
		wave[4] = new Wave(bd, 20, 0, 0);
		wave[5] = new Wave(bd, 20, 20, 0);
		wave[6] = new Wave(bd, 0, 40, 0);
		wave[7] = new Wave(bd, 10, 0, 10);
		wave[8] = new Wave(bd, 20, 20, 5);
		wave[9] = new Wave(bd, 0, 0, 20);
		wave[10] = null;
		
		crWv = wave[0];
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * wzywamy funkcje rysuj¹ce poszczególne komponenty interfejsu graficznego,
	 * takie jak plansza i interfejs gracza
	 */
	public void paintComponent(Graphics graphics) {
		graphics.clearRect(0, 0, getWidth(), getHeight());
		bd.drawBoard(graphics);
		ls.drawList(graphics);
		crWv.drawWave(graphics);
		for(int i = 0; i < tower.size(); i++) tower.get(i).drawTower(graphics);
		bd.drawHealthBar(graphics);
		bd.drawMoneyStatus(graphics);
		bd.drawSignature(graphics);
	}
	
	/**
	 * W tej metodzie w¹tek obs³uguj¹cy rozgrywkê wysy³a kolejne fale (kiedy poprzednia siê skoñczy³a),
	 * a tak¿e koñczy rozgrywkê, je¿eli gracz wygra³ lub przegra³
	 */
	public void run() {
		long frameCounter = 0;
		int waveNumber = 0;
		while(true) {
			
			if(waitTime == 0) {
				frameCounter = crWv.forward(frameCounter);
				
				if(crWv.hasWaveEnded()) {
					System.out.println(">> FALA " + (waveNumber + 1) + " POKONANA!");
					
					waveNumber++;
					
					if(waveNumber == 10) {
						JOptionPane.showMessageDialog(null, "ZWYCIEZYLES!", "Gratulacje", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					
					waitTime = 200;
					crWv = wave[waveNumber];
				}
				
				if(bd.getHealth() == 0) {
					JOptionPane.showMessageDialog(null, "GAME OVER!", "Niestety", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				
				for(int i = 0; i < tower.size(); i++) tower.get(i).seekEnemies(crWv);				
			}
			else waitTime--;
			
			try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
			frameCounter++;
			repaint();
		}
		System.exit(0);
	}
		
	public static void setSelectedTower(int target) { 
		selectedTower = target;
	}
	
	public static int getSelectedTower() { 
		return selectedTower;
	}
	
	/**
	 * @return ostatnia pozycja kursora myszki na interfejsie (panelu)
	 */
	public static Point getMouse() {
		return mouse;
	}
	
	public static void setMouse(Point x) {
		mouse = x;
	}
	
	/**
	 * @return ostatnia pozycja klikniêcia myszki na interfejsie (panelu)
	 */
	public static Point getClick() {
		return click;
	}
	
	public static void setClick(Point x) {
		click = x;
	}
}
