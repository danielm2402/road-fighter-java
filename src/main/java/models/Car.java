/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import static control.Globales.CAR_IMG;
import static control.Globales.CRASH_CAR;
import java.awt.Image;

/**
 *
 * @author User
 */
public class Car {

    private Image carImage = new javax.swing.ImageIcon(CAR_IMG).getImage();
    private double x = 0;
    private double y = 0;
    private double speed = 0;
    private double acceleration = 1;
    private double MaximumSpeed = 5;
    private boolean isCrahs = false;
    private boolean isHit = false;
    private int hitDirection;

    public Car(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public int getHitDirection() {
        return hitDirection;
    }

    public void setHitDirection(int hitDirection) {
        this.hitDirection = hitDirection;
    }

    
    public boolean isIsCrahs() {
        return isCrahs;
    }

    public void setIsCrahs(boolean isCrahs) {
        this.isCrahs = isCrahs;
    }

    public boolean isIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isCrahs() {
        return isCrahs;
    }

    public void setCrahs(boolean parCrash) {
        this.isCrahs = parCrash;
        if (parCrash) {
            this.carImage = new javax.swing.ImageIcon(CRASH_CAR).getImage();
        } else {
            this.carImage = new javax.swing.ImageIcon(CAR_IMG).getImage();
        }
    }

    public double getMaximumSpeed() {
        return MaximumSpeed;
    }

    public void setMaximumSpeed(double MaximumSpeed) {
        this.MaximumSpeed = MaximumSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getX() {
        return x;
    }

    public Image getCarImage() {
        return carImage;
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

}
