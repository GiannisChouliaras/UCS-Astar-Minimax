/**
 * @Course: Artificial Intelligence  MYY602
 * @Project MINIMAX algorithm implementation
 *
 * @Author: Chouliaras Ioannis   AM : 2631
 * @Author: Kaloudis   Spyridon  AM : 2447
 * @Author: Molos      Ioannis   AM : xxxx
 */


import java.util.Scanner;

public class Main {
    static int move=-1;
    static int turn = 0;
    static int white=8;
    static int black=9;
    static Scanner scanner = new Scanner(System.in);

    static int lastMove = -1;

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {

        Game g = new Game();
        System.out.println("There are " + white + " white balls and " + black + " black balls.");
        System.out.println();
        while(white>0 || black>0){
          if(turn%2==0){
              move = g.bestMoveID(white, black, true);
              System.out.println("Computer's move: " + move);
              System.out.println();
              move(move);
          }else{
              System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
              System.out.println("Moves: ");
              System.out.println("0. Take 1 white ball");
              System.out.println("1. Take 1 black ball");
              System.out.println("2. Take 2 white balls and 1 black ball");
              System.out.println("3. Take 1 white ball and 2 black balls");
              System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
              System.out.println();
              System.out.println("There are " + white + " white and " + black + " black.");
              System.out.println("Player's move: ");
              move = scanner.nextInt();
              move(move);
          }
          lastMove=move;
          //System.out.println("There are " + white + " white and " + black + " black.");
          turn++;
        }
        if(lastMove==0||lastMove==2||lastMove==3){
          if(turn%2!=0){
            System.out.println("Computer wins.");
          }else{
            System.out.println("You win!");
          }
        }else{
          System.out.println("It's a draw :)");
        }
    }

    /**
     * Move, perform the move of the id
     * @param id
     */
    public static void move(int id){
    	while(!moveIsValid(id)){
    		System.out.println("Please enter a valid move!");
    		System.out.println("0. Take 1 white ball");
    		System.out.println("1. Take 1 black ball");
    		System.out.println("2. Take 2 white balls and 1 black ball");
    		System.out.println("3. Take 1 white ball and 2 black balls");
            id = scanner.nextInt();
    	}
	      if(id==0){
	        white--;
	      }
	      if(id==1){
	        black--;
	      }
	      if(id ==2){
	        white-=2;
	        black--;
	      }
	      if(id ==3){
	        white--;
	        black-=2;
	      }
    }

    /**
     * moveIsValid, checking if move with this id is valid
     * @param id of a move
     * @return true if valid, false if not.
     */
	private static boolean moveIsValid(int id) {
		if(id==0){
			if(white>0){
				return true;
			}
		}
		if(id==1){
			if(black>0){
				return true;
			}
		}
		if(id==2){
			if(white>1 && black>0){
				return true;
			}
		}
		if(id==3){
			if(white>0&&black>1){
				return true;
			}
		}
		return false;
	}

}
