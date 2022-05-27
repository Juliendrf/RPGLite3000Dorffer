package com.isep.rpg;
import java.util.List;

public abstract class Hero {
    public int lifePoints;  // Chaque héros possède  des points de vie, une arme et une armure
    public int armor;
    public int weaponDamage;

    public List<Potion> potions; // Il peut consommer de la nourriture ou des potions
    public List<Food> lembas;



    public void setLifePoints(int lifePoints){
        this.lifePoints = lifePoints;
    }
    public int getLifePoints(){
        return lifePoints;
    }
    public int getWeaponDamage(){
        return weaponDamage;
    }
    public void setWeaponDamage(int weaponDmg){
        this.weaponDamage = weaponDmg;
    }
    public void setArmor(int armor){
        this.armor = armor;
    }
    public int getArmor(){
        return armor;
    }

    public void heroAttack(Enemy enemy, int weaponDamage, Hero hero ){
        int currentLifePoints = enemy.getLifePoints();
        enemy.setLifePoints(enemy.getLifePoints() - weaponDamage);
        /*boolean deadHero = hero.receiveHeroAttack(hero.getWeaponDamage(), enemy); //On construit une méthode attack spécifique au héros
        return deadHero;*/
    }

    //public void receiveHeroAttack(int damageReceived, Enemy enemy){
        //enemy.setLifePoints(getLifePoints() - damageReceived);
        //return this.lifePoints <= 0; //On vérifie que si le héros est mort
    }

