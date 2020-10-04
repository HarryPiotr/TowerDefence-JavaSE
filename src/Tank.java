import java.awt.*;

import javax.swing.ImageIcon;

/**
 * Ka�dy z przeciwnik�w jest obiektem tej klasy. Zawiera atrybuty, takie jak pr�dko��, wsp�rz�dne,
 * a tak�e metody obs�uguj�ce ruch po planszy, bycie trafionym przez wie��
 *
 * @author Piotr Anu�kiewicz I7Y1S1
 */

public class Tank {

    public boolean isAlive = false;
    private int myX, myY;
    private Point myMiddle;
    private int myDirection = 1;
    private int part = 0;
    private int health;
    private int myType;
    private int myReward;
    private int mySpeed;
    private String myPath[] = new String[4];

    /**
     * W konstruktorze ustawiamy atrybuty przeciwnika zgodnie z danymi zapisanymi w atrybutach statycznych klasy {@link List}
     *
     * @param type typ przeciwnika - ma wp�yw na atrybuty takie jak pr�dko��, nagroda za pokonanie itp.
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
     * Metoda przemieszczaj�ca przeciwnika o zadany wektor [x, y]
     */
    public void moveTank(int x, int y) {
        myX += x;
        myY += y;

        myMiddle.translate(x, y);
    }

    /**
     * Metoda obs�uguj�ca otrzymanie obra�e� przez przeciwnika.
     * W przypadku utraty wszystkich punkt�w zdrowia wyp�aca r�wnie� nagrod� i
     * usuwa przeciwnika.
     *
     * @param dmg ilo�� otrzymanych obra�e�
     */
    public void getShot(int dmg) {
        health -= dmg;
        if (health <= 0) {
            //tutaj obs�uga �mierci adwersarza
            health = 0;
            isAlive = false;
            Panel.bd.incrementMoney(myReward);
        }
    }

    /**
     * Metoda rysuj�ca przeciwnika. Rysowani s� tylko "�ywi" przeciwnicy, zwr�ceni w odpowiednim kierunku.
     * Ponadto nad ka�dym przeciwnikiem rysowany jest jego pasek zdrowia.
     */
    public void drawTank(Graphics graphics) {

        if (isAlive) {
            switch (myDirection) {
                case 0:
                    graphics.drawImage(new ImageIcon(myPath[0]).getImage(), myX, myY, 80, 80, null);
                    break;
                case 1:
                    graphics.drawImage(new ImageIcon(myPath[1]).getImage(), myX, myY, 80, 80, null);
                    break;
                case 2:
                    graphics.drawImage(new ImageIcon(myPath[2]).getImage(), myX, myY, 80, 80, null);
                    break;
				case 3:
					graphics.drawImage(new ImageIcon(myPath[3]).getImage(), myX, myY, 80, 80, null);
					break;
            }
        }

        //rysujemy paski �ycia
        graphics.setColor(Color.RED);
        graphics.fillRect(myX + 15, myY, 50, 5);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(myX + 15, myY, (int) ((50.0 / (double) (List.enemyHealthList[myType])) * (double) health), 5);

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
