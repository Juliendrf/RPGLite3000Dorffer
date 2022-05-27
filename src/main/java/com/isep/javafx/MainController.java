package com.isep.javafx;


import com.isep.rpg.*;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.util.Duration;


import java.io.IOException;


public class MainController extends Application {
    @Override
    public void start(Stage stage) throws IOException {

            Stage stage1 = new Stage();
            Stage stage2 = new Stage();
            Stage stage3 = new Stage();
            Stage stage4 = new Stage();


            //Creation of the menu

            Label title = new Label("Bienvenue dans le Mini RPG Lite 3000 !");
            Label rules = new Label("Règles :\n Vous devez choisir une classe ci-dessous ! \n Une fois la classe choisie, vous combattrez un monstre ! \n Préparez-vous au combat !");
            Label classChoice = new Label("Choisissez la classe que vous voulez jouer !");
            Button chooseMage = new Button("Mage");
            Button chooseHunter = new Button("Hunter");
            Button chooseWarrior = new Button("Warrior");
            Button chooseHealer = new Button("Healer");
            Image screenImage = new Image("file:Images/Imagerpg.jpg");
            final ImageView imageViewstart = new ImageView(screenImage);
            imageViewstart.setFitHeight(350);
            imageViewstart.setFitWidth(609);

           // On utilise un EventHandler pour chaque bouton différent
        chooseMage.setOnMouseClicked(new EventHandler<>()
        {
            @Override
            public void handle(MouseEvent t) {
                try {
                    Mage mage = new Mage();
                    Enemy enemy = new BasicEnemy();
                    mageBattle(stage1, enemy, mage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        chooseHunter.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                try {
                    Hunter hunter = new Hunter();
                    Enemy enemy = new BasicEnemy();
                    hunterBattle(stage2,enemy,hunter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        chooseWarrior.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                try {
                    Warrior warrior = new Warrior();
                    Enemy enemy = new BasicEnemy();
                    warriorBattle(stage3, enemy, warrior);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        chooseHealer.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                try {
                    Healer healer = new Healer();
                    Enemy enemy = new BasicEnemy();
                    healerBattle(stage4, enemy, healer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
//On réalise le visuel en disposant plusieurs Hbox dans une Vbox

            HBox titleplace = new HBox(title);
            title.setFont(new Font(30));
            titleplace.setAlignment(Pos.CENTER);

            HBox root = new HBox();
            root.getChildren().setAll(imageViewstart);
            root.setAlignment(Pos.CENTER);


            HBox rulesplace = new HBox(rules);
            rules.setFont(new Font(20));
            rulesplace.setAlignment(Pos.CENTER);

            HBox choice = new HBox(classChoice);
            choice.setAlignment(Pos.CENTER);

            HBox buttonchoice = new HBox(chooseMage,chooseHunter,chooseWarrior,chooseHealer);
            chooseMage.setFont(new Font(30));
            chooseHunter.setFont(new Font(30));
            chooseWarrior.setFont(new Font(30));
            chooseHealer.setFont(new Font(30));

            buttonchoice.setPrefSize(300, 150);
            buttonchoice.setSpacing(20);
            buttonchoice.setAlignment(Pos.CENTER);

            VBox menu = new VBox(30,titleplace,root, rulesplace, choice, buttonchoice);
            Scene scenemenu = new Scene(menu, 1000, 800);
            stage.setScene(scenemenu);
            stage.setTitle("Mini RPG Lite 3000");
            stage.setResizable(true);
            stage.show();

    }


    public void mageBattle(Stage stage, Enemy enemy, Mage mage) throws IOException{ //On traite les combats en fonction de la classe

        Label title = new Label("Vous êtes en combat !");
        Label decision = new Label("Vous devez choisir une attaque !");
        Label infoEnemy = new Label("PV Ennemi : " + enemy.getLifePoints());
        Label infoHero = new Label ( "PV Heros : " + mage.getLifePoints());
        Label manaCheck = new Label ( "Vous n'avez pas assez de mana !");
        Label winFight = new Label("Vous avez gagné le combat ! ");
        Label loseFight = new Label("Vous avez perdu le combat !");
        Button chooseFireball = new Button("Boule de feu");
        Button chooseNormalAttack = new Button("Attaque normale");
        Button choosePass = new Button("Passer votre tour");
        loseFight.setVisible(false);
        winFight.setVisible(false);
        manaCheck.setVisible(false);
        Image imageEnemy = new Image("file:Images//mechant.png");
        final ImageView imageMechant = new ImageView(imageEnemy);
        imageMechant.setFitHeight(207);
        imageMechant.setFitWidth(320);
        Image imageHero = new Image("file:Images/mage.jpg");
        final ImageView imageHeros = new ImageView(imageHero);
        imageHeros.setFitWidth(189);
        imageHeros.setFitHeight(267);


        chooseFireball.setOnMouseClicked(new EventHandler<>()
        {
            @Override
            public void handle(MouseEvent t) {
                if(mage.getManaPoints() < mage.manaCost) {
                    manaCheck.setVisible(true);
                }
                else {
                    mage.fireball(mage, enemy);


                    infoEnemy.setText("PV Ennemi : " + enemy.getLifePoints());
                    if (enemy.getLifePoints() > 0) {
                        enemy.enemyAttack(mage, enemy.getWeaponDamage(), enemy);

                    }
                    infoHero.setText("PV Heros : " + mage.getLifePoints());

                }
                if(enemy.getLifePoints() <= 0){
                    winFight.setVisible(true);
                    try {
                        winHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else if(mage.getLifePoints() <= 0){
                    loseFight.setVisible(true);
                    try {
                        loseHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        chooseNormalAttack.setOnMouseClicked(new EventHandler<>()
        {
            @Override
            public void handle(MouseEvent t) {
                mage.heroAttack(enemy, mage.getWeaponDamage(),mage);
                infoEnemy.setText("PV Ennemi : " + enemy.getLifePoints());
                if(enemy.getLifePoints()>0){
                    enemy.enemyAttack(mage, enemy.getWeaponDamage(), enemy);

                }
                infoHero.setText("PV Heros : " + mage.getLifePoints());

                if(enemy.getLifePoints() <= 0){
                    winFight.setVisible(true);
                    try {
                        winHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(mage.getLifePoints() <= 0){
                    loseFight.setVisible(true);
                    try {
                        loseHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        choosePass.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                infoEnemy.setText("PV Ennemi : " + enemy.getLifePoints());
                if(enemy.getLifePoints()>0){
                    enemy.enemyAttack(mage, enemy.getWeaponDamage(), enemy);

                }
                infoHero.setText("PV Heros : " + mage.getLifePoints());
                if(enemy.getLifePoints() <= 0){
                    winFight.setVisible(true);
                    try {
                        winHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(mage.getLifePoints() <= 0){
                    loseFight.setVisible(true);
                    try {
                        loseHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        HBox titleplace = new HBox(title);
        title.setFont(new Font(30));
        titleplace.setAlignment(Pos.CENTER);

        HBox decisionplace = new HBox(decision);
        decision.setFont(new Font(20));
        decisionplace.setAlignment(Pos.CENTER);

        HBox buttonchoice = new HBox(chooseFireball,chooseNormalAttack, choosePass);
        chooseFireball.setFont(new Font(15));
        chooseNormalAttack.setFont(new Font(15));
        choosePass.setFont(new Font(15));
        buttonchoice.setSpacing(20);
        buttonchoice.setAlignment(Pos.CENTER);

        HBox mana = new HBox(manaCheck);
        manaCheck.setFont(new Font(15));
        mana.setAlignment((Pos.CENTER));


        HBox boiteInfoEnemy = new HBox(infoEnemy,imageMechant);
        infoEnemy.setFont(new Font(15));
        boiteInfoEnemy.setAlignment(Pos.CENTER);



        HBox boiteInfoHero = new HBox(infoHero,imageHeros);
        infoHero.setFont(new Font(15));
        boiteInfoHero.setAlignment(Pos.CENTER);
        boiteInfoHero.setSpacing(20);

        HBox win = new HBox(winFight);
        loseFight.setFont(new Font(20));
        win.setAlignment(Pos.CENTER);

        HBox lose = new HBox(loseFight);
        loseFight.setFont(new Font(20));
        lose.setAlignment(Pos.CENTER);




        VBox combatMage = new VBox(30, titleplace, decisionplace, buttonchoice, mana,boiteInfoEnemy, boiteInfoHero, win, lose);
        Scene scenemenu = new Scene(combatMage, 1000, 800);
        stage.setScene(scenemenu);
        stage.setTitle("Combat Mage");
        stage.setResizable(true);
        stage.show();

    }

    public void hunterBattle(Stage stage, Enemy enemy, Hunter hunter) throws IOException{

        Label title = new Label("Vous êtes en combat !");
        Label decision = new Label("Vous devez choisir une attaque !");
        Label infoEnemy = new Label("PV Ennemi : " + enemy.getLifePoints());
        Label infoHero = new Label ( "PV Heros : " + hunter.getLifePoints());
        Label arrowCheck = new Label ( "Vous n'avez plus de flèches !");
        Label winFight = new Label("Vous avez gagné le combat ! ");
        Label loseFight = new Label("Vous avez perdu le combat !");
        Button chooseArrow = new Button("Tirer une flèche");
        Button chooseNormalAttack = new Button("Attaque normale");
        Button choosePass = new Button("Passer votre tour");
        loseFight.setVisible(false);
        winFight.setVisible(false);
        arrowCheck.setVisible(false);
        Image imageEnemy = new Image("file:Images//mechant.png");
        final ImageView imageMechant = new ImageView(imageEnemy);
        imageMechant.setFitHeight(207);
        imageMechant.setFitWidth(320);
        Image imageHero = new Image("file:Images/Hunter.jpg");
        final ImageView imageHeros = new ImageView(imageHero);
        imageHeros.setFitWidth(189);
        imageHeros.setFitHeight(267);


        chooseArrow.setOnMouseClicked(new EventHandler<>()
        {
            @Override
            public void handle(MouseEvent t) {
                if(hunter.getArrows() <= 0) {
                    arrowCheck.setVisible(true);
                }
                else {
                    hunter.shootArrows(hunter, enemy);


                    infoEnemy.setText("PV Ennemi : " + enemy.getLifePoints());
                    if (enemy.getLifePoints() > 0) {
                        enemy.enemyAttack(hunter, enemy.getWeaponDamage(), enemy);

                    }
                    infoHero.setText("PV Heros : " + hunter.getLifePoints());

                }
                if(enemy.getLifePoints() <= 0){
                    winFight.setVisible(true);
                    try {
                        winHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else if(hunter.getLifePoints() <= 0){
                    loseFight.setVisible(true);
                    try {
                        loseHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        chooseNormalAttack.setOnMouseClicked(new EventHandler<>()
        {
            @Override
            public void handle(MouseEvent t) {
                hunter.heroAttack(enemy, hunter.getWeaponDamage(),hunter);
                infoEnemy.setText("PV Ennemi : " + enemy.getLifePoints());
                if(enemy.getLifePoints()>0){
                    enemy.enemyAttack(hunter, enemy.getWeaponDamage(), enemy);

                }
                infoHero.setText("PV Heros : " + hunter.getLifePoints());

                if(enemy.getLifePoints() <= 0){
                    winFight.setVisible(true);
                    try {
                        winHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(hunter.getLifePoints() <= 0){
                    loseFight.setVisible(true);
                    try {
                        loseHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        choosePass.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                infoEnemy.setText("PV Ennemi : " + enemy.getLifePoints());
                if(enemy.getLifePoints()>0){
                    enemy.enemyAttack(hunter, enemy.getWeaponDamage(), enemy);

                }
                infoHero.setText("PV Heros : " + hunter.getLifePoints());
                if(enemy.getLifePoints() <= 0){
                    winFight.setVisible(true);
                    try {
                        winHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(hunter.getLifePoints() <= 0){
                    loseFight.setVisible(true);
                    try {
                        loseHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        HBox titleplace = new HBox(title);
        title.setFont(new Font(30));
        titleplace.setAlignment(Pos.CENTER);

        HBox decisionplace = new HBox(decision);
        decision.setFont(new Font(20));
        decisionplace.setAlignment(Pos.CENTER);

        HBox buttonchoice = new HBox(chooseArrow,chooseNormalAttack, choosePass);
        chooseArrow.setFont(new Font(15));
        chooseNormalAttack.setFont(new Font(15));
        choosePass.setFont(new Font(15));
        buttonchoice.setSpacing(20);
        buttonchoice.setAlignment(Pos.CENTER);

        HBox arrow = new HBox(arrowCheck);
        arrowCheck.setFont(new Font(15));
        arrow.setAlignment((Pos.CENTER));


        HBox boiteInfoEnemy = new HBox(infoEnemy,imageMechant);
        infoEnemy.setFont(new Font(15));
        boiteInfoEnemy.setAlignment(Pos.CENTER);

        HBox boiteInfoHero = new HBox(infoHero,imageHeros);
        infoHero.setFont(new Font(15));
        boiteInfoHero.setAlignment(Pos.CENTER);
        boiteInfoHero.setSpacing(20);

        HBox win = new HBox(winFight);
        loseFight.setFont(new Font(20));
        win.setAlignment(Pos.CENTER);

        HBox lose = new HBox(loseFight);
        loseFight.setFont(new Font(20));
        lose.setAlignment(Pos.CENTER);




        VBox combatHunter = new VBox(30, titleplace, decisionplace, buttonchoice, arrow,boiteInfoEnemy, boiteInfoHero, win, lose);
        Scene scenemenu = new Scene(combatHunter, 1000, 800);
        stage.setScene(scenemenu);
        stage.setTitle("Combat Hunter");
        stage.setResizable(true);
        stage.show();

    }


    public void warriorBattle(Stage stage, Enemy enemy, Warrior warrior) throws IOException{

        Label title = new Label("Vous êtes en combat !");
        Label decision = new Label("Vous devez choisir une attaque !");
        Label infoEnemy = new Label("PV Ennemi : " + enemy.getLifePoints());
        Label infoHero = new Label ( "PV Heros : " + warrior.getLifePoints());
        Label winFight = new Label("Vous avez gagné le combat ! ");
        Label loseFight = new Label("Vous avez perdu le combat !");
        Button chooseNormalAttack = new Button("Attaque normale");
        Button choosePass = new Button("Passer votre tour");
        loseFight.setVisible(false);
        winFight.setVisible(false);
        Image imageEnemy = new Image("file:Images//mechant.png");
        final ImageView imageMechant = new ImageView(imageEnemy);
        imageMechant.setFitHeight(207);
        imageMechant.setFitWidth(320);
        Image imageHero = new Image("file:Images/warrior.jpg");
        final ImageView imageHeros = new ImageView(imageHero);
        imageHeros.setFitWidth(189);
        imageHeros.setFitHeight(267);


        chooseNormalAttack.setOnMouseClicked(new EventHandler<>()
        {
            @Override
            public void handle(MouseEvent t) {
                warrior.heroAttack(enemy, warrior.getWeaponDamage(),warrior);
                infoEnemy.setText("PV Ennemi : " + enemy.getLifePoints());
                if(enemy.getLifePoints()>0){
                    enemy.enemyAttack(warrior, enemy.getWeaponDamage(), enemy);

                }
                infoHero.setText("PV Heros : " + warrior.getLifePoints());

                if(enemy.getLifePoints() <= 0){
                    winFight.setVisible(true);
                    try {
                        winHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(warrior.getLifePoints() <= 0){
                    loseFight.setVisible(true);
                    try {
                        loseHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        choosePass.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                infoEnemy.setText("PV Ennemi : " + enemy.getLifePoints());
                if(enemy.getLifePoints()>0){
                    enemy.enemyAttack(warrior, enemy.getWeaponDamage(), enemy);

                }
                infoHero.setText("PV Heros : " + warrior.getLifePoints());
                if(enemy.getLifePoints() <= 0){
                    winFight.setVisible(true);
                    try {
                        winHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(warrior.getLifePoints() <= 0){
                    loseFight.setVisible(true);
                    try {
                        loseHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        HBox titleplace = new HBox(title);
        title.setFont(new Font(30));
        titleplace.setAlignment(Pos.CENTER);

        HBox decisionplace = new HBox(decision);
        decision.setFont(new Font(20));
        decisionplace.setAlignment(Pos.CENTER);

        HBox buttonchoice = new HBox(chooseNormalAttack, choosePass);
        chooseNormalAttack.setFont(new Font(15));
        choosePass.setFont(new Font(15));
        buttonchoice.setSpacing(20);
        buttonchoice.setAlignment(Pos.CENTER);

        HBox boiteInfoEnemy = new HBox(infoEnemy,imageMechant);
        infoEnemy.setFont(new Font(30));
        boiteInfoEnemy.setAlignment(Pos.CENTER);

        HBox boiteInfoHero = new HBox(infoHero,imageHeros);
        infoHero.setFont(new Font(30));
        boiteInfoHero.setAlignment(Pos.CENTER);
        boiteInfoHero.setSpacing(20);

        HBox win = new HBox(winFight);
        loseFight.setFont(new Font(20));
        win.setAlignment(Pos.CENTER);

        HBox lose = new HBox(loseFight);
        loseFight.setFont(new Font(20));
        lose.setAlignment(Pos.CENTER);




        VBox combatWarrior = new VBox(30, titleplace, decisionplace, buttonchoice,boiteInfoEnemy, boiteInfoHero, win, lose);
        Scene scenemenu = new Scene(combatWarrior, 1000, 800);
        stage.setScene(scenemenu);
        stage.setTitle("Combat Warrior");
        stage.setResizable(true);
        stage.show();

    }

    public void healerBattle(Stage stage, Enemy enemy, Healer healer) throws IOException{

        Label title = new Label("Vous êtes en combat !");
        Label decision = new Label("Vous devez choisir une attaque !");
        Label infoEnemy = new Label("PV Ennemi : " + enemy.getLifePoints());
        Label infoHero = new Label ( "PV Heros : " + healer.getLifePoints());
        Label manaCheck = new Label ( "Vous n'avez pas assez de mana !");
        Label winFight = new Label("Vous avez gagné le combat ! ");
        Label loseFight = new Label("Vous avez perdu le combat !");
        Button chooseHeal = new Button("Soin");
        Button chooseNormalAttack = new Button("Attaque normale");
        Button choosePass = new Button("Passer votre tour");
        loseFight.setVisible(false);
        winFight.setVisible(false);
        manaCheck.setVisible(false);
        Image imageEnemy = new Image("file:Images//mechant.png");
        final ImageView imageMechant = new ImageView(imageEnemy);
        imageMechant.setFitHeight(207);
        imageMechant.setFitWidth(320);
        Image imageHero = new Image("file:Images/healer.jpg");
        final ImageView imageHeros = new ImageView(imageHero);
        imageHeros.setFitWidth(189);
        imageHeros.setFitHeight(267);


        chooseHeal.setOnMouseClicked(new EventHandler<>()
        {
            @Override
            public void handle(MouseEvent t) {
                if(healer.getManaPoints() < healer.manaCost) {
                    manaCheck.setVisible(true);
                }
                else {
                    healer.heal(healer, enemy);


                    infoEnemy.setText("PV Ennemi : " + enemy.getLifePoints());
                    if (enemy.getLifePoints() > 0) {
                        enemy.enemyAttack(healer, enemy.getWeaponDamage(), enemy);

                    }
                    infoHero.setText("PV Heros : " + healer.getLifePoints());

                }
                if(enemy.getLifePoints() <= 0){
                    winFight.setVisible(true);
                    try {
                        winHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else if(healer.getLifePoints() <= 0){
                    loseFight.setVisible(true);
                    try {
                        loseHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        chooseNormalAttack.setOnMouseClicked(new EventHandler<>()
        {
            @Override
            public void handle(MouseEvent t) {
                healer.heroAttack(enemy, healer.getWeaponDamage(),healer);
                infoEnemy.setText("PV Ennemi : " + enemy.getLifePoints());
                if(enemy.getLifePoints()>0){
                    enemy.enemyAttack(healer, enemy.getWeaponDamage(), enemy);

                }
                infoHero.setText("PV Heros : " + healer.getLifePoints());

                if(enemy.getLifePoints() <= 0){
                    winFight.setVisible(true);
                    try {
                        winHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(healer.getLifePoints() <= 0){
                    loseFight.setVisible(true);
                    try {
                        loseHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        choosePass.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                infoEnemy.setText("PV Ennemi : " + enemy.getLifePoints());
                if(enemy.getLifePoints()>0){
                    enemy.enemyAttack(healer, enemy.getWeaponDamage(), enemy);

                }
                infoHero.setText("PV Heros : " + healer.getLifePoints());
                if(enemy.getLifePoints() <= 0){
                    winFight.setVisible(true);
                    try {
                        winHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(healer.getLifePoints() <= 0){
                    loseFight.setVisible(true);
                    try {
                        loseHero(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        HBox titleplace = new HBox(title);
        title.setFont(new Font(30));
        titleplace.setAlignment(Pos.CENTER);

        HBox decisionplace = new HBox(decision);
        decision.setFont(new Font(20));
        decisionplace.setAlignment(Pos.CENTER);

        HBox buttonchoice = new HBox(chooseHeal,chooseNormalAttack, choosePass);
        chooseHeal.setFont(new Font(15));
        chooseNormalAttack.setFont(new Font(15));
        choosePass.setFont(new Font(15));
        buttonchoice.setSpacing(20);
        buttonchoice.setAlignment(Pos.CENTER);

        HBox mana = new HBox(manaCheck);
        manaCheck.setFont(new Font(15));
        mana.setAlignment((Pos.CENTER));


        HBox boiteInfoEnemy = new HBox(infoEnemy,imageMechant);
        infoEnemy.setFont(new Font(15));
        boiteInfoEnemy.setAlignment(Pos.CENTER);



        HBox boiteInfoHero = new HBox(infoHero,imageHeros);
        infoHero.setFont(new Font(15));
        boiteInfoHero.setAlignment(Pos.CENTER);
        boiteInfoHero.setSpacing(20);

        HBox win = new HBox(winFight);
        loseFight.setFont(new Font(20));
        win.setAlignment(Pos.CENTER);

        HBox lose = new HBox(loseFight);
        loseFight.setFont(new Font(20));
        lose.setAlignment(Pos.CENTER);




        VBox combatHealer = new VBox(30, titleplace, decisionplace, buttonchoice, mana,boiteInfoEnemy, boiteInfoHero, win, lose);
        Scene scenemenu = new Scene(combatHealer, 1000, 800);
        stage.setScene(scenemenu);
        stage.setTitle("Combat Healer");
        stage.setResizable(true);
        stage.show();

    }

    public void winHero(Stage stage) throws IOException{

        Label win = new Label("Vous avez gagné le combat !");
        Label retry = new Label("Réessayez avec une autre classe !");
        Button upWeapon = new Button("Améliorer votre arme");
        Button gainHealth = new Button("Regagner de la vie");
        Button upMaxHP = new Button("Augmenter votre vie maximale");

        upWeapon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("Vous avez amélioré votre arme !");
            }
        });
        gainHealth.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("Vous avez regagné de la vie !");
            }
        });
        upMaxHP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("Vous avez augmenté votre vie maximale !");
            }
        });


        HBox victoire = new HBox(win);
        win.setFont(new Font(20));
        victoire.setAlignment(Pos.CENTER);

        HBox retryText = new HBox(retry);
        retry.setFont(new Font(20));
        retryText.setAlignment(Pos.CENTER);

        HBox buttonchoice = new HBox(upWeapon, gainHealth, upMaxHP);
        upWeapon.setFont(new Font(20));
        gainHealth.setFont(new Font(20));
        upMaxHP.setFont(new Font(20));
        buttonchoice.setSpacing(20);
        buttonchoice.setAlignment(Pos.CENTER);


        VBox victoireHero = new VBox(30, victoire, retryText, buttonchoice);
        victoireHero.setAlignment(Pos.CENTER);

        Scene scenevictoire = new Scene(victoireHero,1000 , 1000);
        stage.setScene(scenevictoire);
        stage.setTitle("Combat Mage");
        PauseTransition delay = new PauseTransition(Duration.seconds(10));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
        stage.setResizable(true);
        stage.show();
    }

    public void loseHero(Stage stage) throws IOException {

        Label lose = new Label("Vous avez perdu le combat !");
        Label retry = new Label("Réessayez avec une autre classe !");

        HBox defaite = new HBox(lose);
        lose.setFont(new Font(20));
        defaite.setAlignment(Pos.CENTER);

        HBox retryText = new HBox(retry);
        retry.setFont(new Font(20));
        retryText.setAlignment(Pos.CENTER);

        VBox defaiteHero = new VBox(30, defaite, retryText);
        defaiteHero.setAlignment(Pos.CENTER);

        Scene scenedefaite = new Scene(defaiteHero, 1000, 1000);
        stage.setScene(scenedefaite);
        stage.setTitle("Combat Mage");
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}