package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        HeroProfile hero = new HeroProfile("Hero Arlan", 120);
        BossEnemy boss = new BossEnemy("Ancient Dragon", 140, 18);

        AttackAction basic = new BasicAttack("Sword Strike", 12);
        AttackAction fireAttack = new FireRuneDecorator(basic);
        AttackAction poisonAttack = new PoisonCoatingDecorator(basic);
        AttackAction criticalAttack = new CriticalFocusDecorator(basic);

        AttackAction stackedAttack = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(
                                new BasicAttack("Sword Strike", 12)
                        )
                )
        );

        System.out.println("--- Decorator Preview ---");
        printActionDetails("Base attack", basic);
        printActionDetails("Fire attack", fireAttack);
        printActionDetails("Poison attack", poisonAttack);
        printActionDetails("Critical attack", criticalAttack);
        printActionDetails("Stacked attack", stackedAttack);

        System.out.println("\nDecorator proof:");
        System.out.println("1) Same base action wrapped in different decorators");
        System.out.println("2) One action wrapped by multiple decorators at runtime");
        System.out.println("3) No separate class was needed for every combination");

        System.out.println("\n--- Full Dungeon Run Through Facade ---");
        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, stackedAttack);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());

        System.out.println("\nBattle log:");
        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\n--- Final State ---");
        System.out.println(hero.getName() + " HP: " + hero.getHealth());
        System.out.println(boss.getName() + " HP: " + boss.getHealth());

        System.out.println("\n=== Demo Complete ===");
    }

    private static void printActionDetails(String label, AttackAction action) {
        System.out.println(label + ":");
        System.out.println("  Name: " + action.getActionName());
        System.out.println("  Damage: " + action.getDamage());
        System.out.println("  Effects: " + action.getEffectSummary());
        System.out.println();
    }
}