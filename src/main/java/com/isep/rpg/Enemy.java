package com.isep.rpg;
// A chaque tour, le héros affronte des ennemis de différents types
public abstract class Enemy {
    private int lifePoints;
    private int weaponDamage;


    public int getLifePoints() {
        return lifePoints;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public void setWeaponDamage(int weaponDmg) {
        this.weaponDamage = weaponDmg;
    }

    public void enemyAttack(Hero hero, int weaponDamage, Enemy enemy) {   //L'ennemi peut attaquer un héros
        int currentLifePoints = hero.getLifePoints();
        hero.setLifePoints(hero.getLifePoints() - weaponDamage);
        //boolean deadHero = enemy.receiveEnemyAttack(enemy.getWeaponDamage(), hero);
        //return deadHero;
    }

    /*public boolean receiveEnemyAttack(int damageReceived, Hero hero){       // Le héros reçoit alors des dégats de l'ennemi
        hero.setLifePoints(getLifePoints()-damageReceived);
        return this.lifePoints <= 0; // On vérifie que l'ennemi est toujours vivant*/

}
