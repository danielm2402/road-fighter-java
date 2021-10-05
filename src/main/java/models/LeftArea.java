/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import static control.Globales.CAR_IMG;
import static control.Globales.HOUSE_IMG;
import static control.Globales.TREES_IMG;
import static control.Globales.TREE_IMG;
import java.awt.Image;

/**
 *
 * @author User
 */
public class LeftArea {

    private Image house = new javax.swing.ImageIcon(HOUSE_IMG).getImage();
    private Image tree = new javax.swing.ImageIcon(TREE_IMG).getImage();
    private Image trees = new javax.swing.ImageIcon(TREES_IMG).getImage();
    private double speed = 0;

    private double treesX = 45;
    private double treesY = 133;

    private double treeX = 55;
    private double treeY = 370;

    private double houseX = 45;
    private double houseY = 450;

    private double tree2X = 55;
    private double tree2Y = 650;
 
    private double acceleration = 0.08;
    private double MaximumSpeed = 15;

    public LeftArea(double speed) {
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
    
    

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getTreeX() {
        return treeX;
    }

    public void setTreeX(double treeX) {
        this.treeX = treeX;
    }

    public double getTreeY() {
        return treeY;
    }

    public void setTreeY(double treeY) {
        this.treeY = treeY;
    }

    public double getHouseX() {
        return houseX;
    }

    public void setHouseX(double houseX) {
        this.houseX = houseX;
    }

    public double getHouseY() {
        return houseY;
    }

    public void setHouseY(double houseY) {
        this.houseY = houseY;
    }

    public double getTree2X() {
        return tree2X;
    }

    public void setTree2X(double tree2X) {
        this.tree2X = tree2X;
    }

    public double getTree2Y() {
        return tree2Y;
    }

    public void setTree2Y(double tree2Y) {
        this.tree2Y = tree2Y;
    }

    public double getTreesX() {
        return treesX;
    }

    public void setTreesX(double treesX) {
        this.treesX = treesX;
    }

    public double getTreesY() {
        return treesY;
    }

    public void setTreesY(double treesY) {
        this.treesY = treesY;
    }

    public Image getHouse() {
        return house;
    }

    public void setHouse(Image house) {
        this.house = house;
    }

    public Image getTree() {
        return tree;
    }

    public void setTree(Image tree) {
        this.tree = tree;
    }

    public Image getTrees() {
        return trees;
    }

    public void setTrees(Image trees) {
        this.trees = trees;
    }

}
