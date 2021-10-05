/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import control.Globales;
import static control.Globales.ALTO_FRAME;
import static control.Globales.ANCHO_FRAME;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import models.Car;
import models.Enemies;
import models.Enemy;
import models.Guardrail;
import models.LeftArea;
import models.Street;

/**
 *
 * @author Danielm2402
 */
public class RoadFighter extends JFrame implements Globales, KeyListener {

    private BufferedImage imgBuffered;
    private Image backGroundImage = null;
    private Graphics graficos;
    private Stroke defaultStroke;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean isStop = false;
    private int points = 0;
    private boolean isCity = true;
    private boolean updateHit = false;

    private Car car = new Car(286, 730, 4);
    private Street street = new Street(301, -800, 0);
    private LeftArea leftArea = new LeftArea(0);
    private Guardrail guardrail = new Guardrail(160, -800, 0);
    private Enemies enemies = new Enemies();

    public RoadFighter() {
        addKeyListener(this);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.imgBuffered = new BufferedImage(ANCHO_FRAME, ALTO_FRAME, BufferedImage.TYPE_INT_RGB);
        graficos = imgBuffered.createGraphics();
        this.setBackground(Color.black);
        this.setSize(ANCHO_FRAME, ALTO_FRAME);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
        paint(graficos);
    }

    public void startGame() {
        CompletableFuture.delayedExecutor(1000 / 30, TimeUnit.MICROSECONDS).execute(() -> {
            this.update();
            this.startGame();
        });
    }

    public void update() {
        repaint();
    }

    public void paint(Graphics g) {
        imgBuffered = new BufferedImage(ANCHO_FRAME, ALTO_FRAME,
                BufferedImage.TYPE_INT_RGB);
        graficos = imgBuffered.createGraphics();
        this.setBackground(Color.black);
        this.setSize(ANCHO_FRAME, ALTO_FRAME + 70);
        this.setLocationRelativeTo(this);
        this.setVisible(true);

        Graphics2D g2d = (Graphics2D) graficos;
        this.defaultStroke = g2d.getStroke();
        g2d.getStroke();
        drawLayouts();
        drawLines();
        drawCar();
        drawLeftArea();
        drawGuardrail();
        if (!this.isStop) {
            speedControl();
        }

        verifyCrahs();
        drawEnemies();
        checkOvertake();
        drawPointsArea();
        verifyHit();
        updateHit();
        g.drawImage(imgBuffered, 0, 0, this);

    }

    public void drawLayouts() {
        graficos.setColor(Color.decode(STREET_COLOR));
        graficos.fillRect(0, 70, 28, ALTO_FRAME + 70);

        graficos.setColor(Color.decode(this.isCity ? GREEN : BEACH));
        graficos.fillRect(28, 70, 138, ALTO_FRAME + 70);

        graficos.setColor(Color.decode(STREET_COLOR));
        graficos.fillRect(166, 70, 270, ALTO_FRAME + 70);

        graficos.setColor(Color.decode(this.isCity ? GREEN : BLUE));
        graficos.fillRect(436, 70, 110, ALTO_FRAME + 70);

    }

    public void drawLines() {
        float dash[] = {50, 110};
        Graphics2D g2 = (Graphics2D) graficos;

        g2.setPaint(Color.decode(STREET_LINE));
        g2.setStroke(new BasicStroke(9, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash, 0.0f));
        g2.drawLine(301, (int) this.street.getY(), 301, 800);
        this.street.setY(this.street.getY() + this.street.getSpeed() >= 0 ? -800 : this.street.getY() + this.street.getSpeed());

    }

    public void drawCar() {

        if (this.left) {
            this.car.setX(this.car.getX() - this.car.getSpeed());
        }
        if (this.right) {
            this.car.setX(this.car.getX() + this.car.getSpeed());
        }
        graficos.drawImage(this.car.getCarImage(), (int) this.car.getX(), (int) this.car.getY(), null);
    }

    public void drawLeftArea() {
        graficos.drawImage(this.leftArea.getTrees(), (int) this.leftArea.getTreesX(), (int) this.leftArea.getTreesY(), null);
        graficos.drawImage(this.leftArea.getTree(), (int) this.leftArea.getTreeX(), (int) this.leftArea.getTreeY(), null);
        graficos.drawImage(this.leftArea.getHouse(), (int) this.leftArea.getHouseX(), (int) this.leftArea.getHouseY(), null);
        graficos.drawImage(this.leftArea.getTree(), (int) this.leftArea.getTree2X(), (int) this.leftArea.getTree2Y(), null);

        if (this.leftArea.getTreesY() < 800) {
            this.leftArea.setTreesY(this.leftArea.getTreesY() + this.leftArea.getSpeed());
            this.leftArea.setTreeY(this.leftArea.getTreeY() + this.leftArea.getSpeed());
            this.leftArea.setHouseY(this.leftArea.getHouseY() + this.leftArea.getSpeed());
            this.leftArea.setTree2Y(this.leftArea.getTree2Y() + this.leftArea.getSpeed());
        } else {
            this.leftArea.setTreesY(-800);
            this.leftArea.setTreeY(-350);
            this.leftArea.setHouseY(-250);
            this.leftArea.setTree2Y(0);
        }

    }

    public void drawGuardrail() {
        float dash[] = {8, 20};
        Graphics2D g2 = (Graphics2D) graficos;

        g2.setPaint(Color.decode(STREET_LINE));
        g2.setStroke(defaultStroke);
        g2.setStroke(new BasicStroke(4));
        g2.drawLine((int) this.guardrail.getX(), (int) this.guardrail.getY(), (int) this.guardrail.getX(), 800);
        g2.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash, 0.0f));
        g2.drawLine(156, (int) this.guardrail.getY(), (int) 156, 800);
        g2.setPaint(Color.BLACK);
        g2.drawLine(164, (int) this.guardrail.getY(), (int) 164, 800);
        this.guardrail.setY(this.guardrail.getY() + this.guardrail.getSpeed() >= this.guardrail.getSpeed() ? -800 : this.guardrail.getY() + this.guardrail.getSpeed());
    }

    public void speedControl() {
        if (this.up) {
            this.street.setSpeed(this.street.getSpeed() >= this.street.getMaximumSpeed() ? this.street.getSpeed() : this.street.getSpeed() + this.street.getAcceleration());
            this.leftArea.setSpeed(this.leftArea.getSpeed() >= this.leftArea.getMaximumSpeed() ? this.leftArea.getSpeed() : this.leftArea.getSpeed() + this.leftArea.getAcceleration());
            this.guardrail.setSpeed(this.guardrail.getSpeed() >= this.guardrail.getMaximumSpeed() ? this.guardrail.getSpeed() : this.guardrail.getSpeed() + this.guardrail.getAcceleration());

        } else {
            this.street.setSpeed(this.street.getSpeed() <= 0 ? 0 : this.street.getSpeed() - this.street.getAcceleration());
            this.leftArea.setSpeed(this.leftArea.getSpeed() <= 0 ? 0 : this.leftArea.getSpeed() - this.leftArea.getAcceleration());
            this.guardrail.setSpeed(this.guardrail.getSpeed() <= 0 ? 0 : this.guardrail.getSpeed() - this.guardrail.getAcceleration());
        }
    }

    public void verifyCrahs() {

        if ((this.car.getX() <= 166 || this.car.getX() >= 408) && !this.car.isCrahs()) {
            this.street.setSpeed(0);
            this.leftArea.setSpeed(0);
            this.guardrail.setSpeed(0);
            this.car.setCrahs(true);
            this.isStop = true;
            this.left = false;
            this.right = false;
            this.up = false;
        }
        if (this.isStop) {
            restartGame();
        }
    }

    public void restartGame() {
        this.isStop = false;
        CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> {
            this.car.setCrahs(false);
            this.car.setX(286);
        });

    }

    public void drawEnemies() {
        Enemies.addEnemy(this.street);
        Enemies.enemiesList.forEach((enemy) -> {
            graficos.drawImage(enemy.getCarImage(), (int) enemy.getX(), (int) enemy.getY(), null);

        });
        Enemies.updateEnemy(this.street);
    }

    public void checkOvertake() {
        for (int i = 0; i < Enemies.enemiesList.size(); i++) {
            Enemy enemy = Enemies.enemiesList.get(i);
            if ((enemy.getY() > this.car.getY()) && (!enemy.isOvertake())) {
                this.points = this.points + 50;
                enemy.setOvertake(true);
                Enemies.enemiesList.set(i, enemy);

            }
        }

    }

    public void drawPointsArea() {

        int cifras = 0;    //esta variable es el contador de cifras
        int n = this.points;
        while (n != 0) {             //mientras a n le queden cifras
            n = n / 10;         //le quitamos el último dígito
            cifras++;          //sumamos 1 al contador de cifras
        }

        int total = 6 - cifras;
        String strpoints = "";
        for (int i = 0; i < total; i++) {
            strpoints = strpoints + "0";
        }
        strpoints = strpoints + String.valueOf(this.points);
        graficos.setColor(Color.BLACK);
        graficos.fillRect(0, 0, ANCHO_FRAME, 70);

        graficos.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        graficos.setColor(Color.WHITE); // Here
        graficos.drawString(strpoints, 446, 50);

    }

    public void verifyHit() {

        double carx0 = this.car.getX();
        double cary0 = this.car.getY();
        double carx1 = carx0 + 30;
        double cary1 = this.car.getY();
        if (!this.car.isIsHit()) {
            for (int i = 0; i < Enemies.enemiesList.size(); i++) {
                Enemy enemy = Enemies.enemiesList.get(i);
                double x0 = enemy.getX();
                double y0 = enemy.getY();
                double x1 = x0 + 29;
                double y1 = y0 + 40;
                if ((carx0 > x0 && carx0 < x1) && (cary0 > y0 && cary0 < y1)) {
                    this.car.setIsHit(true);
                    this.updateHit = true;
                    this.car.setHitDirection(2);
                    this.street.setSpeed(this.street.getSpeed() - 5);
                }
                if ((carx1 > x0 && carx1 < x1) && (cary1 > y0 && cary1 < y1)) {
                    this.updateHit = true;
                    this.car.setIsHit(true);
                    this.car.setHitDirection(-2);
                    this.street.setSpeed(this.street.getSpeed() - 5);

                }
            }
        }
    }

    public void updateHit() {
        if (this.car.isIsHit()) {
            this.car.setX(this.car.getX() + this.car.getHitDirection());
        }
        if (this.updateHit) {
            this.updateHit = false;
            CompletableFuture.delayedExecutor(700, TimeUnit.MILLISECONDS).execute(() -> {
                this.car.setIsHit(false);
            });
        }
    }

    public static void main(String[] args) {
        RoadFighter rf = new RoadFighter();
        rf.startGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
            this.isCity = this.isCity ? false : true;
        }
        if (!this.car.isCrahs() && !this.car.isIsHit()) {

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                this.left = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                this.up = true;

            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!this.car.isCrahs()) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                this.left = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.right = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                this.up = false;

            }
        }

    }

}
