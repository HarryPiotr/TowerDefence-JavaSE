import java.awt.*;

/**
 * Klasa zawiera przyciski na interfejsie u¿ytkownika, a tak¿e atrybuty (pola statyczne),
 * takie jak modele (tekstury) wie¿ i przeciwników, ró¿ne parametry wie¿, takie jak kolor pocisku, cena, obra¿enia, czêstotliwoœæ strza³u, a tak¿e parametry przeciwników, takie jak szybkoœæ, zdrowie, nagroda za pokonanie
 * 
 * @author Piotr Anuœkiewicz I7Y1S1
 */
public class List {
	
	private int myX, myY, mySizeX, mySizeY;
	public static String iconPaths[] = {"imgs/images/tower1shopIcon.png", "imgs/images/tower2shopIcon.png", "imgs/images/tower3shopIcon.png", "imgs/images/trashCan.png"};
	public static String modelPaths[][] = 
		{
				{"imgs/images/tower1.png", "imgs/images/tower2.png"},
				{"imgs/images/fastTower1.png", "imgs/images/fastTower2.png"},
				{"imgs/images/spreadTower1.png", "imgs/images/spreadTower2.png"},
				{"imgs/images/trashPerimeter.png", "imgs/images/trashCross.png"}
		};
	public static int towerPrices[] = {22, 30, 30};
	public static int towerRanges[][] = 
		{
				{125, 175},
				{165, 220},
				{125, 200}
		};
	public static int towerDamages[][] =
		{
				{13, 21},
				{7, 11},
				{8, 14}
		};
	public static int towerCooldowns[][] = 
		{
				{100, 70},
				{45, 30},
				{120, 100}
		};
	public static int towerUpgradeCosts[] = {38, 42, 55};
	public static Color towerBeamColors[] =
		{
				Color.YELLOW,
				new Color(153, 204, 255),
				new Color(255, 255, 255)
		};
	public static int towerBeamTTL[] = {13, 6, 16};
	public static int towerBeamThickness[] = {4, 2, 3};
	
	public static int enemySpawningPoints[][] = 
		{
				{-80, 240},
				{-80, 80},
				{-80, 0},
				{-80, 320}
		};
	public static int enemyHealthList[] = {50, 20, 200};
	public static int enemyRewards[] = {4, 2, 9};
	public static int enemySpeeds[] = {2, 5, 1};
	public static int enemyDelay[] = {45, 20, 70};
	public static String enemyModels[][] = 
		{
				{"imgs/images/tankUp.png", "imgs/images/tankRight.png", "imgs/images/tankDown.png"},
				{"imgs/images/bikeUp.png", "imgs/images/bikeRight.png", "imgs/images/bikeDown.png"},
				{"imgs/images/heavytankUp.png", "imgs/images/heavytankRight.png", "imgs/images/heavytankDown.png"}
		};
	public ListButton[] button = new ListButton[4];
	
	public List(int x, int y, int sizeX, int sizeY) {
		myX = x; myY = y; mySizeX = sizeX; mySizeY = sizeY;
		
		for(int i = 0; i < 4; i++) button[i] = new ListButton(myX + i * (mySizeX + 4), myY, mySizeX, mySizeY, i);
	}
	
	public void drawList(Graphics graphics) {
		for(int i = 0; i < 4; i++) button[i].drawButton(graphics);
	}

}
