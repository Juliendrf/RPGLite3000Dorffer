package com.isep.rpg;
// Le joueur peut choisir d'incarner un héros chasseur
public class Hunter extends Hero {

    private int arrows;   // Le chasseur possède un nombre limité de flèches pour attaquer
    private int damageArrow = 3; //Les flèches infligent 3 points de dégâts

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    public int getArrows() {
        return arrows;
    }

    public Hunter() {
        this.setLifePoints(6);
        this.setWeaponDamage(2);
        this.setArrows(5);
    }

    public void shootArrows(Hero hero, Enemy enemy) {
        int currentArrows = getArrows();
        if (currentArrows >= 0) {
            this.setArrows(currentArrows - 1);
            heroAttack(enemy, damageArrow, hero);
        } else {
            System.out.println("Vous n'avez plus de flèches");
            heroAttack(enemy, getWeaponDamage(),hero);
        }

    }


}
