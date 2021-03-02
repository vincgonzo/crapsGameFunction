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
    public static void diceDsiplay(int dice1, int dice2){
        System.out.println("Vous avez lance " + dice1 + " et " + dice2 + ". La somme est " + (dice1 + dice2) + ".");
        return;
    }

    public  static String askGamer(){
        String mot;
        do {
            Scanner s = new Scanner(System.in);
            System.out.println("Voulez vous jouer ? N/O");
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

    public static boolean joueEtGagneUnTourDeCraps(int turn, int score, int tokens, int mise, int goal){
        if (turn == 0) {
            if (score == 7 || score == 11) {
                System.out.println("You Win !! Perfect ! HA HA HA HA HA.");return true;
            }
            else if (score == 2 || score == 3 || score == 12) {
                System.out.println("You Loss Man. Try Again.");return false;
            }
        } else {
            while ((tokens > 0 && turn > 0)) {
                String answer = askGamer();
                if(answer.equals("O")){
                    if (score == 7) {
                        System.out.println("You make a 7 and you Loss Man. Try Again.");
                        return false;
                    }else if (score == goal) {
                        System.out.println("You Win !! Double Bet. You got now " + tokens + " tokens now.");return true;
                    }
                    else {
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }
        return false;
    }

    public static void startGame(){
        int tokens = 10, dice1, dice2, score = 0, goal = 0, mise = 0, j = 0;
        String answer;
        Scanner s = new Scanner(System.in);
        boolean tour = true;
        System.out.println("======================== GAME START ==============================");
        do {
            answer = askGamer();
            mise = bet(tokens);
            if(mise != 0){
                dice1 = roll();
                dice2 = roll();
                diceDsiplay(dice1, dice2);
                score = dice1 + dice2;
                tour = joueEtGagneUnTourDeCraps(j, score, tokens, mise, goal);
                if(tour)
                    tokens += (mise * 2);
                else
                    tokens -= mise;
                System.out.println("Après ce tour il vous reste : " + tokens +"jetons");
            }
            else{
                tour = false;
            }
            if(j == 0)
                goal = score;
            j++;
        }while (mise <= 0 || (answer.equals("N")) || !tour);
        System.out.println("=========================================");
        System.out.println("==========     GAME OUT       ===========");
        System.out.println("=========================================");
        return;
    }

}
