package com.isep.rpg;
// Le joueur peut choisir d'incarner un héros mage
public class Mage extends Spellcaster{

    public int manaCost = 20;
    public int damageFireball =  4;
    public Mage(){
        this.setManaPoints(30);
        this.setLifePoints(5);
        this.setWeaponDamage(1);
    }

    public void fireball(Hero hero, Enemy enemy){
        int currentMP = getManaPoints();
        if(currentMP >= manaCost) {
            this.setManaPoints(currentMP - manaCost);
            heroAttack(enemy, damageFireball, hero);
        }
        else {
            System.out.println("Pas assez de mana");
            heroAttack(enemy, getWeaponDamage(), hero);
            System.out.println("Votre héros a attaqué normalement");
        }

    }

}
