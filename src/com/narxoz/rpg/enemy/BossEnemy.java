package com.narxoz.rpg.enemy;

public class BossEnemy {
    private final String name;
    private int health;
    private final int attackPower;

    public BossEnemy(String name, int health, int attackPower) {
        this.name = name;
        this.health = Math.max(0, health);
        this.attackPower = Math.max(0, attackPower);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
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