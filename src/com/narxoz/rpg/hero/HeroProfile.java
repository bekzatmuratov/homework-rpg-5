package com.narxoz.rpg.hero;

public class HeroProfile {
    private final String name;
    private int health;

    public HeroProfile(String name, int health) {
        this.name = name;
        this.health = Math.max(0, health);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int amount) {
        if (!isAlive()) {
            return;
        }
        health = Math.max(0, health - Math.max(0, amount));
    }

    public boolean isAlive() {
        return health > 0;
    }
}