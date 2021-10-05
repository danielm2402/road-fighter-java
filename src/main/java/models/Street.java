/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author User
 */
public class Street {

    private double x = 0;
    private double y = 0;
    private double speed = 0;
    private double acceleration = 0.1;
    private double MaximumSpeed = 20;

    public Street(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
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
