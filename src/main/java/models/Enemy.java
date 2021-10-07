/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import static control.Globales.BLUE_CAR;
import static control.Globales.CAR_IMG;
import static control.Globales.ENEMIES;
import java.awt.Image;

/**
 *
 * @author User
 */
public class Enemy {

    private Image carImage = new javax.swing.ImageIcon(BLUE_CAR).getImage();
    private double x = 0;
    private double y = 0;
    private double speed = 9;
    private double acceleration = 0.1;
    private double MaximumSpeed = 15;
    private boolean overtake = false;

    public Enemy(double x, double y, int img) {
        this.x = x;
        this.y = y;
        this.carImage = new javax.swing.ImageIcon(ENEMIES[img]).getImage();
    }

    public boolean isOvertake() {
        return overtake;
    }

    public void setOvertake(boolean overtake) {
        this.overtake = overtake;
    }

    public Image getCarImage() {
        return carImage;
    }

    public void setCarImage(Image carImage) {
        this.carImage = carImage;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getMaximumSpeed() {
        return MaximumSpeed;
    }

    public void setMaximumSpeed(double MaximumSpeed) {
        this.MaximumSpeed = MaximumSpeed;
    }

}
