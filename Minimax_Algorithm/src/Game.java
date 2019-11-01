/**
 * @Course: Artificial Intelligence  MYY602
 * @Project MINIMAX algorithm implementation
 *
 * @Author: Chouliaras Ioannis   AM : 2631
 * @Author: Kaloudis   Spyridon  AM : 2447
 * @Author: Molos      Ioannis   AM : xxxx
 */



import java.util.ArrayList;

public class Game {

    /**
     * Constructor
     */
    public Game() {}

    /**
     * isFinished : When a game is finished. when we've reached T.K
     * @param whites
     * @param blacks
     * @return true if we are in T.K , false if not
     */
    public boolean isFinished(int whites, int blacks) {
        if (whites == 2 && blacks == 1) return true;
        if (whites == 1 && blacks == 2) return true;
        if (whites == 0 && blacks == 1) return true;
        if (whites == 1 && blacks == 0) return true;
        //if (whites == 0 && blacks == 0) return true;
        return false;
    }

    /**
     * whoWon : after we've reached a T.K we should find who won.
     * @param whites T.K for whites
     * @param blacks T.K for blacks
     * @param AIplayer true if AI , false if player
     * @return 10 if AI won, -10 if player won or 0 if draw
     */
    public int whoWon(int whites, int blacks, boolean AIplayer) {
        if (whites == 1 && blacks == 0 && AIplayer) return 10;
        if (whites == 2 && blacks == 1 && AIplayer) return 10;
        if (whites == 1 && blacks == 2 && AIplayer) return 10;
        if (whites == 1 && blacks == 0 && !AIplayer) return -10;
        if (whites == 2 && blacks == 1 && !AIplayer) return -10;
        if (whites == 1 && blacks == 2 && !AIplayer) return -10;
        if (whites == 0 && blacks == 1) return 0;
        return 100;
    }


    /**
     * findMoves : we collect all possibles moves (max 4) and store them in ArrayList.
     * @param whites
     * @param blacks
     * @return ArrayList<Node> of all possible moves.
     */
    public ArrayList<Node> findMoves(int whites, int blacks) {
        ArrayList<Node> moves = new ArrayList<Node>();

        if (whites >= 1 && blacks >= 0) {
            Node node = new Node();
            node.setID(0);
            moves.add(node);
        }
        if (blacks >= 1 && whites >= 0) {
            Node node = new Node();
            node.setID(1);
            moves.add(node);
        }
        if (whites > 1 && blacks >= 1) {
            Node node = new Node();
            node.setID(2);
            moves.add(node);
        }
        if (whites >= 1 && blacks > 1) {
            Node node = new Node();
            node.setID(3);
            moves.add(node);
        }
        return moves;
    }

    /**
     * bestMoveID : calls minimax
     * @param whites
     * @param blacks
     * @param player
     * @return the node.id of the bestMove
     */
    public int bestMoveID(int whites, int blacks, boolean player) {
        return minimax(whites, blacks, player).getID();
    }


    /**
     * getBlack : an update for blacks based in id
     * @param id : is the move (0,1,2,3)
     * @param blacks
     * @return updated int blacks
     */
    public int getBlack(int id, int blacks) {
        if (blacks > 0) {
            if (id == 1 || id == 2) return blacks -1;
            if (id == 3 && blacks >= 2) return blacks -2;
        }
        return blacks;
    }

    /**
     * getWhites : an update for whites based in id
     * @param id : is the move (0,1,2,3)
     * @param whites
     * @return updated int blacks
     */
    public int getWhites(int id, int whites) {
        if (whites > 0) {
            if (id == 0 || id == 3) return whites - 1;
            if (id == 2 && whites >= 2) return whites -2;
        }
        return whites;
    }

    /**
     * findID : based on whites and blacks we find the move (id)
     * @param whites
     * @param blacks
     * @return int id
     */
    public int findID(int whites, int blacks) {
      if (blacks == 0 && whites == 1) return 0;
      if (blacks == 1 && whites == 0) return 1;
      if (blacks == 1 && whites == 2) return 2;
      if (blacks == 2 && whites == 1) return 3;
      return -1;
    }

    /**
     * minimax algorithm : finding the best move using recursion.
     * @param whites
     * @param blacks
     * @param player AI = true, Player = false
     * @return Node node where node is the best move.
     */
    public Node minimax(int whites, int blacks, boolean player) {

        //find the possibles moves calling findMoves function
        ArrayList<Node> posMoves = findMoves(whites, blacks);

        /* Checking if is in terminal mode. We are not using depth.
        * using whoWon function and creating a Node, setting hCost = 0, setting score of Node(-10,0,10) and return it.
        */
        if (isFinished(whites, blacks)){
            int score = whoWon(whites, blacks, true);
            Node node = new Node();
            node.setID(findID(whites,blacks));
            node.setMyScore(score);
            node.sethCost();
            return node;
        }

        //ArrayList<Node> of all moves that we'll do.
        ArrayList<Node> moves = new ArrayList<Node>();

        /* for every possible move, update blacks,whites and
        * if is AI then use recursion with updated blacks,whites and player = false. then update this move's score and hCost.
        * if is Player then use recursion with updated blacks,whites and player = true. then update this move's score and hCost.
        * after every search we set the values of blacks and whites to the original values, so we can continue with next available move.
        * in the end, we adding to ArrayList<Node> moves the updated available move. */
        for (Node nod : posMoves) {
            int temp_blacks = getBlack(nod.getID(), blacks);
            int temp_whites = getWhites(nod.getID(), whites);

            if (player) { // use the recursion with player = false and store it in a Node.
                Node heidi = minimax(temp_whites, temp_blacks, false);
                nod.setMyScore(heidi.getScore());
                nod.addHCost(heidi.getHCost() + 1);
            }
            else {  // use the recursion with player = true and store it in a Node.
                Node heidi = minimax(temp_whites, temp_blacks, true);
                nod.setMyScore(heidi.getScore());      // set the score of the move nod.
                nod.addHCost(heidi.getHCost() + 1); // update the hCost of the move nod
            }

            temp_blacks = blacks; // restore the temp_blacks with the first blacks value
            temp_whites = whites; // restore the temp_whites with the first whites value
            moves.add(nod);       // add move nod to the ArrayList<Node> moves.


        }
        // now we found all possible moves and their values.
        // if player = AI (true) then for all possible moves find the one with the highest score.
        // if two moves have the same high scores (10) check their HCost and pick the smallest value.
        // Store the Best move to Node BestMove and return it.
        Node bestMove = null;
        if (player) {
            int bestScore = Integer.MIN_VALUE;
            int HCost = Integer.MAX_VALUE;
            for (Node heidi : moves) {
                //System.out.println("MAX : Score = " + heidi.getScore() + "\tid = " + heidi.getID() + "\th(n) = " + heidi.getHCost());
                if (heidi.getScore() > bestScore) {
                    bestMove = heidi;
                    bestScore = heidi.getScore();
                }
                if (heidi.getScore() == bestScore && bestScore == 10) {
                    if (heidi.getHCost() < HCost) {
                        HCost = heidi.getHCost();
                        bestMove = heidi;
                    }
                }
            }
            //System.out.println("max");
        }
        else { // player = Player (false) then for all possible moves find the lowest score. if there are two moves with (-10) check HCost
            int bestScore = Integer.MAX_VALUE;
            int HCost = Integer.MAX_VALUE;
            for (Node heidi : moves) {
                //System.out.println("MIN : Score = " + heidi.getScore() + "\tid = " + heidi.getID() + "\th(n) = " + heidi.getHCost());
                if (heidi.getScore() < bestScore) {
                    bestMove = heidi;
                    bestScore = heidi.getScore();
                }
                if (heidi.getScore() == bestScore && bestScore == -10) {
                    if (heidi.getHCost() < HCost) {
                        HCost = heidi.getHCost();
                        bestMove = heidi;
                    }
                }

            }
            /** System.out.println("min"); */
        }
        /**System.out.println("BEST : SCORE: " + bestMove.getScore() + "\tID = " + bestMove.getID() + "\tH(N) = " + bestMove.getHCost()); */
        /**System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); */
        return bestMove;
    }

}
