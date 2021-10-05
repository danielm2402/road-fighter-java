/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author User
 */
public class Enemies {

    public static ArrayList<Enemy> enemiesList = new ArrayList<>();
    private static boolean timeRespawn = false;
    private static boolean ready = true;

    public Enemies() {

    }

    public static ArrayList getList() {
        return enemiesList;
    }

    public static void addEnemy(Street street) {

        if (street.getMaximumSpeed() <= street.getSpeed() && street.getY() >= -400 && !timeRespawn && ready) {
            Random rand = new Random();
            int x = rand.nextInt((408 - 166) + 1) + 166;
            int y = rand.nextInt((400 - 0) + 1) + 0;
            timeRespawn = true;
            Enemy car = new Enemy(x, -y);
            enemiesList.add(car);
        }
        if (timeRespawn) {
            timeRespawn = false;
            ready = false;
            CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(() -> {
                ready = true;
            });
        }
    }

    public static void updateEnemy(Street street) {
        for (int i = 0; i < enemiesList.size(); i++) {
            Enemy elemento = enemiesList.get(i);
            elemento.setY(street.getMaximumSpeed() <= street.getSpeed() ? elemento.getY() + elemento.getSpeed() : elemento.getY() - elemento.getSpeed());
            enemiesList.set(i, elemento);
            if (elemento.getY() >= 800 || elemento.getY() < -400) {
                enemiesList.remove(i);
            }
        }

    }
}
