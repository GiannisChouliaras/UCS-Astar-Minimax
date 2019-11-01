/**
 * @Course: Artificial Intelligence  MYY602
 * @Project A* and UCS algorithms implementations
 *
 * @Author: Chouliaras Ioannis   AM : 2631
 * @Author: Kaloudis   Spyridon  AM : 2447
 * @Author: Molos      Ioannis   AM : xxxx
 */

import java.util.Random;
import java.util.ArrayList;

public class Maze {

    /** fields */
    private int     dimension;
    private double  probability;
    Random rand = new Random();
    private int randomNumber = rand.nextInt(100);
    private ArrayList<ArrayList<Integer>> mazeRow = new ArrayList();
    private ArrayList<Integer> mazeColumn;

    /**
     * Constructor
     * @param dimension = the dimension of the Maze
     * @param probability = the probability of the obstacles.
     */
    public Maze(int dimension, double probability) {
        this.dimension   = dimension;
        this.probability = probability;
    }

    /**
     * getDimension
     * @return int dimension
     */
    public int getDimension() { return dimension; }

    /**
     * getProbability
     * @return int probability
     */
    public double getProbability() { return probability; }

    /**
     * getMaze
     * @return an ArrayList of the Maze.
     */
    public ArrayList<ArrayList<Integer>> getMaze() { return mazeRow; }

    /**
     * SetMaze
     * @param hello
     */
    public void setMaze(ArrayList<ArrayList<Integer>> hello) { mazeRow = hello; }

    /**
     * CreateMaze
     */
    public void createMaze() {
        for(int i = 0; i < dimension; i++){
            mazeColumn = new ArrayList<Integer>();
            mazeRow.add(mazeColumn);
            for(int j = 0; j < dimension; j++) {
                if(randomNumber <= probability) mazeRow.get(i).add(0);
                else mazeRow.get(i).add(1);
                randomNumber = rand.nextInt(100);
            }
        }
    }

    /**
     * isFree
     * @param x = a number of maze's row
     * @param y = a number of maze's column
     * @return true if (x,y) = 0 or false if (x,y) = 1 *Obstacle*
     */
    public boolean isFree(int x, int y) {
        if (mazeRow.get(x).get(y) == 0) return true;
        return false;
    }

    /**
     * toString
     * @return Maze as a String
     */
    public String toString(){
        String Maze = "";
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                Maze = Maze + mazeRow.get(i).get(j) + "\t";
            }
            Maze = Maze + "\n\n";
        }

        return Maze;
    }

    /**
     * getValue
     * @param x = a number of maze's row
     * @param y = a number of maze's column
     * @return 0 or 1 if there is an obstacle
     */
    public int getValue(int x, int y) { return mazeRow.get(x).get(y);}

}
