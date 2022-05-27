package com.isep.rpg;
// Les lanceurs de sorts sont une catégorie de héros que le joueur peut choisir
public abstract class Spellcaster extends Hero{
    private int manaPoints;

    public int getManaPoints(){
        return manaPoints;
    }

    public void setManaPoints(int currentManaPoints){
        this.manaPoints = currentManaPoints;
    }
}
