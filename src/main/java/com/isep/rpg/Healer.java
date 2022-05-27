package com.isep.rpg;
// Le joueur peut choisir d'incarner un soigneur
public class Healer extends Spellcaster {


    public int manaCost = 10;
    public int healAmount = 3;
    public final int maxHP = 7;

    public Healer() {
        this.setLifePoints(7);
        this.setManaPoints(40);
        this.setWeaponDamage(1);
    }

    public void heal(Hero hero, Enemy enemy) {
        int currentMP = getManaPoints();
        if (currentMP >= manaCost) {
            this.setManaPoints(getManaPoints() - manaCost);
            if (getLifePoints() + healAmount > maxHP) {
                this.setLifePoints(maxHP);
                System.out.println(getLifePoints());
                //return false;

            } else {
                this.setLifePoints(getLifePoints() + healAmount);
                System.out.println(getLifePoints());
                //return false;
            }

        } else {
            System.out.println("Pas assez de mana");
            System.out.println(getLifePoints());
            heroAttack(enemy, getWeaponDamage(),hero);
        }


    }
}