import java.awt.*;
import java.util.*;

/**
 * Klasa, której obiekty reprezentuj¹ kolejne fale przeciwników. 
 * Obs³uguje "wpuszczanie" kolejnych przeciwników na planszê oraz odnajdywanie œcie¿ki przez przeciwników
 * 
 * @author Piotr Anuœkiewicz I7Y1S1
 */

public class Wave {
	
	private volatile boolean tanksAlive = false;
	private volatile boolean waveEnded = false;
	private volatile boolean allSpawned = false;
	private Board bd;
	private ArrayList<Tank> tank = new ArrayList<>();	
	private int tanksSpawned = 0;
	private int frameDelay;
	
	/**
	 * Konstruktor definiuj¹cy falê przeciwników
	 * 
	 * @param b referencja na obiekt planszy
	 * @param arg0 liczba zwyk³ych przeciwników
	 * @param arg1 liczba szybkich przeciwników
	 * @param arg2 liczba ciê¿kich przeciwników
	 */
	public Wave(Board b, int arg0, int arg1, int arg2) {
		bd = b;
		int x = List.enemySpawningPoints[bd.myType][0];
		int y = List.enemySpawningPoints[bd.myType][1];
		for(int i = 0; i < arg2; i++) tank.add(new Tank(2, x, y));
		for(int i = 0; i < arg1; i++) tank.add(new Tank(1, x, y));
		for(int i = 0; i < arg0; i++) tank.add(new Tank(0, x, y));
		
		frameDelay = List.enemyDelay[tank.get(0).getMyType()];
	}
		
	public void drawWave(Graphics graphics) {
		for(int i = 0; i < tank.size(); i++) {
			if(tank.get(i).isAlive) tank.get(i).drawTank(graphics);
		}
	}
	
	/**
	 * Metoda obs³uguje ruch pojazdu po planszy na zasadzie punktów kontrolnych
	 */
	public void handleTank(Tank t) {
		if(t.isAlive) {
			switch(bd.myType) {
			case 0:
				switch(t.getPart()) {
				case 0:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 2 * bd.getTileSize()) {
						t.turnTank(0);
						t.setPart(1);
					}
					break;
				case 1:
					t.moveTank(0, -(t.getSpeed()));
					if(t.getMyY() <= bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(2);
					}
					break;
				case 2:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 4 * bd.getTileSize()) {
						t.turnTank(2);
						t.setPart(3);
					}
					break;
				case 3:
					t.moveTank(0, t.getSpeed());
					if(t.getMyY() >= 4 * bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(4);
					}
					break;
				case 4:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 6 * bd.getTileSize()) {
						t.turnTank(0);
						t.setPart(5);
					}
					break;
				case 5:
					t.moveTank(0, -(t.getSpeed()));
					if(t.getMyY() <= 2 * bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(6);
					}
					break;
				case 6:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 7 * bd.getTileSize()) {
						bd.takeCasualties();
						t.isAlive = false;
						t.setPart(7);
					}
					break;
				}
				break;
			case 1:
				switch(t.getPart()) {
				case 0:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= bd.getTileSize()) {
						t.turnTank(2);
						t.setPart(1);
					}
					break;
				case 1:
					t.moveTank(0, t.getSpeed());
					if(t.getMyY() >= 4 * bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(2);
					}
					break;
				case 2:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 3 * bd.getTileSize()) {
						t.turnTank(0);
						t.setPart(3);
					}
					break;
				case 3:
					t.moveTank(0, -(t.getSpeed()));
					if(t.getMyY() <= bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(4);
					}
					break;
				case 4:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 5 * bd.getTileSize()) {
						t.turnTank(2);
						t.setPart(5);
					}
					break;
				case 5:
					t.moveTank(0, t.getSpeed());
					if(t.getMyY() >= 4 * bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(6);
					}
					break;
				case 6:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 7 * bd.getTileSize()) {
						bd.takeCasualties();
						t.isAlive = false;
						t.setPart(7);
					}
					break;
				}
				break;
			case 2:
				switch(t.getPart()) {
				case 0:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 1 * bd.getTileSize()) {
						t.turnTank(2);
						t.setPart(1);
					}
					break;
				case 1:
					t.moveTank(0, t.getSpeed());
					if(t.getMyY() >= bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(2);
					}
					break;
				case 2:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 2 * bd.getTileSize()) {
						t.turnTank(2);
						t.setPart(3);
					}
					break;
				case 3:
					t.moveTank(0, t.getSpeed());
					if(t.getMyY() >= 2 * bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(4);
					}
					break;
				case 4:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 3 * bd.getTileSize()) {
						t.turnTank(2);
						t.setPart(5);
					}
					break;
				case 5:
					t.moveTank(0, t.getSpeed());
					if(t.getMyY() >= 3 * bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(6);
					}
					break;
				case 6:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 4 * bd.getTileSize()) {
						t.turnTank(2);
						t.setPart(7);
					}
					break;
				case 7:
					t.moveTank(0, t.getSpeed());
					if(t.getMyY() >= 4 * bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(8);
					}
					break;
				case 8:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 5 * bd.getTileSize()) {
						t.turnTank(2);
						t.setPart(9);
					}
					break;
				case 9:
					t.moveTank(0, t.getSpeed());
					if(t.getMyY() >= 5 * bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(10);
					}
					break;
				case 10:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 7 * bd.getTileSize()) {
						bd.takeCasualties();
						t.isAlive = false;
						t.setPart(11);
					}
					break;
				}
				break;
			case 3:
				switch(t.getPart()) {
				case 0:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 3 * bd.getTileSize()) {
						t.turnTank(0);
						t.setPart(1);
					}
					break;
				case 1:
					t.moveTank(0, -(t.getSpeed()));
					if(t.getMyY() <= 2 * bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(2);
					}
					break;
				case 2:
					t.moveTank(-(t.getSpeed()), 0);
					if(t.getMyX() <= bd.getTileSize()) {
						t.turnTank(2);
						t.setPart(3);
					}
					break;
				case 3:
					t.moveTank(0, t.getSpeed());
					if(t.getMyY() >= 4 * bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(4);
					}
					break;
				case 4:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 5 * bd.getTileSize()) {
						t.turnTank(0);
						t.setPart(5);
					}
					break;
				case 5:
					t.moveTank(0, -(t.getSpeed()));
					if(t.getMyY() <= 2 * bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(6);
					}
					break;
				case 6:
					t.moveTank(-(t.getSpeed()), 0);
					if(t.getMyX() <= 3 * bd.getTileSize()) {
						t.turnTank(2);
						t.setPart(7);
					}
					break;
				case 7:
					t.moveTank(0, t.getSpeed());
					if(t.getMyY() >= 4 * bd.getTileSize()) {
						t.turnTank(1);
						t.setPart(8);
					}
					break;
				case 8:
					t.moveTank(t.getSpeed(), 0);
					if(t.getMyX() >= 7 * bd.getTileSize()) {
						bd.takeCasualties();
						t.isAlive = false;
						t.setPart(9);
					}
					break;
				}
				break;
			}
							
		}
	}
	
	/**
	 * Metoda obs³uguje ruch "¿yj¹cych" przeciwników po planszy, a tak¿e "wpuszczanie" na ni¹ kolejnych
	 */
	public long forward(long frameCounter) {
				
		if(frameCounter >= frameDelay && tanksSpawned < tank.size()) {
			frameCounter = 0;
			frameDelay = List.enemyDelay[tank.get(tanksSpawned).getMyType()];
			tank.get(tanksSpawned).isAlive = true;
			tanksSpawned++;
			if(tanksSpawned == tank.size()) allSpawned = true;
			tanksAlive = true;
		}
		
		for(int i = 0; i < tanksSpawned; i++) handleTank(tank.get(i));
		
		if(tanksAlive) {
			for(int i = 0; i < tank.size(); i++) {
				if(tank.get(i).isAlive) break;
				if(i == tank.size() - 1) {
					tanksAlive = false;
					if(allSpawned) waveEnded = true;
				}
			}
		}
		return frameCounter;
	}
	
	public boolean hasWaveEnded() {
		return waveEnded;
	}
	
	public ArrayList<Tank> getTankList() {
		return tank;
	}
}
