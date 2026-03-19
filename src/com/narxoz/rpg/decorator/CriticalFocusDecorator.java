package com.narxoz.rpg.decorator;

public class CriticalFocusDecorator extends ActionDecorator {
    public CriticalFocusDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return "Focused " + super.getActionName();
    }

    @Override
    public int getDamage() {
        return super.getDamage() + 10;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + ", increases critical precision";
    }
}