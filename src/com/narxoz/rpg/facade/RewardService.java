package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward";
        }

        if ("Draw".equals(battleResult.getWinner())) {
            return "Small consolation reward";
        }

        if (battleResult.getWinner().contains("Hero")) {
            return "Epic loot chest + 100 gold";
        }

        return "Defeat - no reward";
    }
}