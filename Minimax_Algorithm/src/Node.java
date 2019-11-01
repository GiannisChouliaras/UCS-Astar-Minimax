/**
 * @Course: Artificial Intelligence  MYY602
 * @Project MINIMAX Algorithm implementation
 *
 * @Author: Chouliaras Ioannis   AM : 2631
 * @Author: Kaloudis   Spyridon  AM : 2447
 * @Author: Molos      Ioannis   AM : xxxx
 */


public class Node {

    private int id;
    private int hCost;
    private int myScore;

    public Node() { ; }

    /**
     * setID
     * @param id
     */
    public void setID(int id) { this.id = id; }

    /**
     * sethCost()
     */
    public void sethCost() { this.hCost = 0;}

    /**
     * addHCost. adding the HCost
     * @param a
     */
    public void addHCost(int a) { this.hCost += a; }

    /**
     * setMyScore, setting score
     * @param myScore
     */
    public void setMyScore(int myScore) { this.myScore = myScore; }

    /**
     * getID
     * @return id;
     */
    public int getID() { return id; }

    /**
     * getHCost()
     * @return hCost
     */
    public int getHCost() { return hCost; }

    /**
     * getScore
     * @return myScore
     */
    public int getScore() { return myScore; }


}
