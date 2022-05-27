package com.isep.rpg;

import com.isep.utils.InputParser;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static Scanner scanner = new Scanner(System.in);
    public Hero heros;
    public int playerTurn;
    public static ArrayList<Hero> heroes = new ArrayList<Hero>();
    public InputParser inputParser;

    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        System.out.println("Veuillez choisir votre type de héros : 1 = Mage / 2 = Hunter / 3 = Warrior / 4 = Healer");
        int choix = scanner.nextInt();
        switch (choix) {
            case 1:
                playGameasMage(new Mage());
                //heroes.add( new Mage());
                break;
            case 2:
                playGameasHunter(new Hunter());
                //heroes.add( new Hunter());
                break;
            case 3:
                playGameasWarrior(new Warrior());
                //heroes.add( new Warrior());
                break;
            case 4:
                playGameasHealer(new Healer());
                //heroes.add( new Healer());
                break;
        }
    }
        public static void playGameasMage(Mage mage) {     // On gère le cas où le joueur choisit de jouer un mage

        Enemy enemy = new BasicEnemy();
        System.out.println("Le combat va commencer !");
        System.out.println("PV du héros = " + mage.getLifePoints() + " / PV de l'ennemi " + enemy.getLifePoints() + " ! ");

        while(mage.getLifePoints()>0 && enemy.getLifePoints()>0){

            boolean heroTurn = true;
            System.out.println("Votre héros dispose de " + mage.getManaPoints() + " points de mana ! ");
            System.out.println("Choisissez votre attaque : 1 = Boule de feu / 2 = Attaque normale / 3 = Passer son tour");
            int choixAttackMage = scanner.nextInt();
            switch(choixAttackMage) {
                case 1:
                    mage.fireball(mage, enemy);
                    break;
                case 2:
                    mage.heroAttack(enemy, mage.getWeaponDamage(), mage);
                    break;
                case 3 :
                    break;
            }
            System.out.println("PV du héros = " + mage.getLifePoints() + " / PV de l'ennemi " + enemy.getLifePoints() +" !");
            heroTurn = false;
            if(enemy.getLifePoints()>0){
                enemy.enemyAttack(mage, enemy.getWeaponDamage(), enemy);
                System.out.println("L'ennemi a attaqué");
                System.out.println("PV du héros = " + mage.getLifePoints() + " / PV de l'ennemi " + enemy.getLifePoints() + " !");

             }
            else{
                break;
             }

         }
         if(mage.getLifePoints()<= 0){
             System.out.println("Vous avez perdu");
         }
         else{
             System.out.println("Vous avez gagné le combat !");
         }

    }

    public static void playGameasHunter(Hunter hunter) {

        Enemy enemy = new BasicEnemy();
        System.out.println("Le combat va commencer !");
        System.out.println("PV du héros = " + hunter.getLifePoints() + " / PV de l'ennemi " + enemy.getLifePoints() + " ! ");

        while(hunter.getLifePoints()>0 && enemy.getLifePoints()>0){

            boolean heroTurn = true;
            System.out.println("Votre héros dispose de " + hunter.getArrows() + " flèche(s) ! ");
            System.out.println("Choisissez votre attaque : 1 = Tirer une flèche / 2 = Attaque normale / 3 = Passer son tour");
            int choixAttackHunter = scanner.nextInt();
            switch(choixAttackHunter) {
                case 1:
                    hunter.shootArrows(hunter, enemy);
                    break;
                case 2:
                    hunter.heroAttack(enemy, hunter.getWeaponDamage(), hunter);
                    break;
                case 3 :
                    break;
            }
            System.out.println("PV du héros = " + hunter.getLifePoints() + " / PV de l'ennemi " + enemy.getLifePoints() +" !");
            heroTurn = false;
            if(enemy.getLifePoints()>0){
                enemy.enemyAttack(hunter, enemy.getWeaponDamage(), enemy);
                System.out.println("L'ennemi a attaqué");
                System.out.println("PV du héros = " + hunter.getLifePoints() + " / PV de l'ennemi " + enemy.getLifePoints() + " !");

            }
            else{
                break;
            }

        }
        if(hunter.getLifePoints()<= 0){
            System.out.println("Vous avez perdu");
        }
        else{
            System.out.println("Vous avez gagné le combat !");
        }

    }

    public static void playGameasHealer(Healer healer) {

        Enemy enemy = new BasicEnemy();
        System.out.println("Le combat va commencer !");
        System.out.println("PV du héros = " + healer.getLifePoints() + " / PV de l'ennemi " + enemy.getLifePoints() + " ! ");

        while(healer.getLifePoints()>0 && enemy.getLifePoints()>0){

            boolean heroTurn = true;
            System.out.println("Votre héros dispose de " + healer.getManaPoints() + " points de mana ! ");
            System.out.println("Choisissez votre attaque : 1 = Soin / 2 = Attaque normale / 3 = Passer son tour ");
            int choixAttackMage = scanner.nextInt();
            switch(choixAttackMage) {
                case 1:
                    healer.heal(healer, enemy);
                    break;
                case 2:
                    healer.heroAttack(enemy, healer.getWeaponDamage(), healer);
                    break;
                case 3:
                    break;
            }
            System.out.println("PV du héros = " + healer.getLifePoints() + " / PV de l'ennemi " + enemy.getLifePoints() +" !");
            heroTurn = false;
            if(enemy.getLifePoints()>0){
                enemy.enemyAttack(healer, enemy.getWeaponDamage(), enemy);
                System.out.println("L'ennemi a attaqué");
                System.out.println("PV du héros = " + healer.getLifePoints() + " / PV de l'ennemi " + enemy.getLifePoints() + " !");

            }
            else{
                break;
            }

        }
        if(healer.getLifePoints()<= 0){
            System.out.println("Vous avez perdu");
        }
        else{
            System.out.println("Vous avez gagné le combat !");
        }

    }

    public static void playGameasWarrior(Warrior warrior) {

        Enemy enemy = new BasicEnemy();
        System.out.println("Le combat va commencer !");
        System.out.println("PV du héros = " + warrior.getLifePoints() + " / PV de l'ennemi " + enemy.getLifePoints() + " ! ");

        while(warrior.getLifePoints()>0 && enemy.getLifePoints()>0){

            boolean heroTurn = true;
            System.out.println("Votre héros est prêt à combattre ");
            System.out.println("Choisissez votre attaque : 1 = Attaque normale / 2 = Passer son tour");
            int choixAttackMage = scanner.nextInt();
            switch(choixAttackMage) {
                case 1:
                    warrior.heroAttack(enemy, warrior.getWeaponDamage(), warrior);
                    break;
                case 2:
                    break;
            }
            System.out.println("PV du héros = " + warrior.getLifePoints() + " / PV de l'ennemi " + enemy.getLifePoints() +" !");
            heroTurn = false;
            if(enemy.getLifePoints()>0){
                enemy.enemyAttack(warrior, enemy.getWeaponDamage(), enemy);
                System.out.println("L'ennemi a attaqué");
                System.out.println("PV du héros = " + warrior.getLifePoints() + " / PV de l'ennemi " + enemy.getLifePoints() + " !");

            }
            else{
                break;
            }

        }
        if(warrior.getLifePoints()<= 0){
            System.out.println("Vous avez perdu");
        }
        else{
            System.out.println("Vous avez gagné le combat !");
        }

    }









    /*public static void generateCombat(Hero hero, Enemy enemy, boolean heroTurn, int choix){
        if (heroTurn == true){
            switch(choix){
                case 1 :
                    choixAttaqueMage(hero,enemy);
                    break;
                case 2 :
                    choixAttaqueHunter();
                    break;
                case 3 :
                    heroes.get(0).heroAttack(enemy,heroes.get(0).getWeaponDamage(),heroes.get(0)); // Le warrior ne choisit pas son attaque , il n'en a qu'une
                case 4 :
                    choixAttaqueHealer();
            }

            System.out.println("Votre héros a attaqué");
        }
        else{


    }
    public static void choixAttaqueMage(Mage mage, Enemy enemy){

        }*/
    }


