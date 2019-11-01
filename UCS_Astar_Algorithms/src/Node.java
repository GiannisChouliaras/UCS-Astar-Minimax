/**
 * @Course: Artificial Intelligence  MYY602
 * @Project A* and UCS algorithms implementations
 *
 * @Author: Chouliaras Ioannis   AM : 2631
 * @Author: Kaloudis   Spyridon  AM : 2447
 * @Author: Molos      Ioannis   AM : xxxx
 */

public class Node {

    /** fields */
    private int gCost;
    private int hCost;
    private int x,y;

    private Node father;
    private boolean isFree;

    /**
     * Constructor
     * @param x = a number of maze's row
     * @param y = a number of maze's column
     */
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * SetFather
     * @param Father
     */
    public void setFather(Node Father) {this.father = Father;}

    /**
     * setIsFree
     * @param isFree
     */
    public void setIsFree(boolean isFree) {this.isFree = isFree;}

    /**
     * setHCost
     * @param hCost
     */
    public void setHCost(int hCost) {this.hCost = hCost;}

    /**
     * setGCost
     * @param gCost
     */
    public void setGCost(int gCost) {this.gCost = gCost;}

    /**
     * getFather
     * @return Node father of this node
     */
    public Node getFather() {return father;}

    /**
     * getX
     * @return int X coordinate of node
     */
    public int getX() {return x;}

    /**
     * getY
     * @return int Y coordinate of node
     */
    public int getY() {return y;}

    /**
     * getIsFree
     * @return boolean False if there is an obstacle or true if is free
     */
    public boolean getIsFree() {return isFree;}

    /**
     * getFCost
     * @return int calculated FCost = GCost + HCost
     */
    public int getFCost() {return gCost + hCost;}

    /**
     * getHCost
     * @return int HCost
     */
    public int getHCost() {return hCost;}

    /**
     * getGCost
     * @return int GCost
     */
    public int getGCost() {return gCost;}
}
