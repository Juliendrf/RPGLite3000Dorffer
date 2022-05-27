package com.isep.rpg;
// Il existe des ennemis "boss" pendant les combats
public class Boss extends Enemy{

    public Boss(){
        this.setLifePoints(15);                 // Les boss sont plus forts que les ennemis basiques !
        this.setWeaponDamage(4);
    }
}
