package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private static final int MAX_ROUNDS = 15;
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();

        int rounds = 0;
        result.addLine("Battle started: " + hero.getName() + " vs " + boss.getName());

        while (hero.isAlive() && boss.isAlive() && rounds < MAX_ROUNDS) {
            rounds++;
            result.addLine("\n--- Round " + rounds + " ---");

            // HERO ATTACK
            int damage = action.getDamage();

            // небольшой крит
            if (random.nextInt(100) < 20) {
                damage += 5;
                result.addLine("Critical boost! +5 damage");
            }

            int bossHPBefore = boss.getHealth();
            boss.takeDamage(damage);
            result.addLine(hero.getName() + " uses " + action.getActionName() +
                    " dealing " + damage + " damage");

            result.addLine("Boss HP: " + bossHPBefore + " -> " + boss.getHealth());

            if (!boss.isAlive()) {
                break;
            }

            // BOSS ATTACK
            int heroHPBefore = hero.getHealth();
            hero.takeDamage(boss.getAttackPower());

            result.addLine(boss.getName() + " hits back for " + boss.getAttackPower());
            result.addLine("Hero HP: " + heroHPBefore + " -> " + hero.getHealth());
        }

        result.setRounds(rounds);

        if (hero.isAlive() && !boss.isAlive()) {
            result.setWinner(hero.getName());
        } else if (boss.isAlive() && !hero.isAlive()) {
            result.setWinner(boss.getName());
        } else {
            result.setWinner("Draw");
        }

        return result;
    }
}