package crapsGame;

import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.abs;

public class crapsFunctions {
    public static void main(String[] args) {
        startGame();
    }

    public static int roll ()
    {
        Random randomGenerator = new Random ();
        int dice = randomGenerator.nextInt (6) + 1;
        return dice;
    }
    public static void diceDisplay(int dice1, int dice2){
        System.out.println("Vous avez lance " + dice1 + " et " + dice2 + ". La somme est " + (dice1 + dice2) + ".");
        return;
    }

    public  static String askGamer(){
        String mot;
        do {
            Scanner s = new Scanner(System.in);
            System.out.println("Voulez vous encore jouer ? N/O");
            mot = s.next();
        }while (!(mot.equals("O") || mot.equals("N")));
        return mot;
    }

    public static int bet(int reserve){
        Scanner s = new Scanner(System.in);
        int m;
        System.out.println("Vous avez "+ reserve + " jetons.");

        do {
            System.out.println("Combien voulez vous miser ? (0 pour arreter) : ");
            m = s.nextInt();
            if(m < 0)
                System.out.println("Vous essayez de rouler le casino. Recommencez.");
            if(m > reserve)
                System.out.println("Vous n'avez pas assez de jetons. Recommencez la mise");
            else
                System.out.println("Vous misez ====== > " + m);
        }while (m > 0 && reserve < m);
        return m;
    }

    public static boolean joueEtGagneUnTourDeCraps(){
        int score = 0, tour = 0, dice1, dice2, j = 0, goal;
        boolean back = false;
        do {
            dice1 = roll();
            dice2 = roll();
            diceDisplay(dice1, dice2);
            score = dice1 + dice2;
            if (tour == 0) {
                if (score == 7 || score == 11) {
                    System.out.println("Vous avez fait 7 ou 11 au premier coup. Vous avez gagné le gros lot !!");
                    back = true;tour++;// bricolage ?
                }
                else if (score == 2 || score == 3 || score == 12) {
                    System.out.println("Mauvaise pioche pour démarrer, perdu !!!!");back = true;
                }
            } else {
                goal = score;
                System.out.println("Maintenant l'objectif est : " + goal);
                do {
                    dice1 = roll();
                    dice2 = roll();
                    diceDisplay(dice1, dice2);
                    score = dice1 + dice2;

                    if (score == 7) {
                        System.out.println("You make a 7 and you Loss Man. Try Again.");back = true;
                    }else if (score == goal) {
                        System.out.println("Vous avez gagné, vous doublé votre mise..");back = true;
                    }
                    System.out.println( "Value goal ==> " + goal);
                    System.out.println( "Value score ==> " + score);
                }while (back != true);
            }
            tour++;
            System.out.println("value back === > " + back);
        }while (back != true);
        return back;
    }

    public static void startGame(){
        int tokens = 10, mise = 0;
        String answer;
        Scanner s = new Scanner(System.in);
        boolean res = true;
        System.out.println("======================== GAME START ==============================");
        do {
            mise = bet(tokens);
            res = joueEtGagneUnTourDeCraps();
            if(res) {
                tokens += (mise * 2);
            }
            else {
                tokens -= mise;
            }
            answer = askGamer();
            System.out.println("answer == " + answer);
        }while (mise != 0 && !(answer.equals("N")) && (tokens != 0));
        System.out.println("=========================================");
        System.out.println("==========     GAME OUT       ===========");
        System.out.println("=========================================");
        return;
    }

}
