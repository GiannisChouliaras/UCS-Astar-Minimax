/**
 * @Course: Artificial Intelligence  MYY602
 * @Project A* and UCS algorithms implementations
 *
 * @Author: Chouliaras Ioannis   AM : 2631
 * @Author: Kaloudis   Spyridon  AM : 2447
 * @Author: Molos      Ioannis   AM : xxxx
 */


import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        int dim;
        int i = 0;
        double prob;
        String maz;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Dimension: ");
        dim = scanner.nextInt();

        System.out.println("Enter the Probability, value must be between 0-100: ");
        prob = scanner.nextDouble();

        while(prob < 0 || prob >100) {
            System.out.println("Am i a joke to you? try again");
            prob = scanner.nextDouble();
        }



        Maze maze = new Maze(dim,prob);
        maze.createMaze();
        FindPath findpath = new FindPath(maze);

        maz = maze.toString();
        System.out.println(maz);

        System.out.println("\n~~~~~~~ START POSITION ~~~~~~~~");

        System.out.println("Give me the Start position: X = ");
        int x = scanner.nextInt();

        System.out.println("Give me the Start position: Y = ");
        int y = scanner.nextInt();

        System.out.println("~~~~~~~ GOAL POSITION ~~~~~~~~");

        System.out.println("Give me the Goal1 position: X = ");
        int G1x = scanner.nextInt();

        System.out.println("Give me the Goal1 position: Y = ");
        int G1y = scanner.nextInt();

        System.out.println("Give me the Goal2 position: X = ");
        int G2x = scanner.nextInt();

        System.out.println("Give me the Goal2 position: Y = ");
        int G2y = scanner.nextInt();

        Node start = new Node(x,y);

        Node goal = new Node(G1x, G1y);
        Node goal2 = new Node(G2x, G2y);

        if (maze.getValue(G1x, G1y) == 1 || maze.getValue(G2x, G2y) ==1) {
            System.out.println("Goal is an obstacle, please try again later.");
            System.out.println(maz);
            System.exit(0);
        }

        if (maze.getValue(x, y) == 1) {
            System.out.println("Start is an obstacle, please try again later.\n\n");
            System.out.println(maz);
            System.exit(0);
        }

        System.out.println("~~~~~ GET DIMENSION ~~~~");
        System.out.println(dim);
        System.out.println("~~~~~ GET PROBABILITY ~~~~");
        System.out.println(prob);
        System.out.println("~~~~~ PRINT MAZE ~~~~\n\n");
        System.out.println(maz);
        System.out.println("\n\n");
        System.out.println("Starting point  : (" + x + ", " + y + ")");
        System.out.println("Golazo's points : 1:(" + G1x + ", " + G1y + ")\t" + "2:(" + G2x + ", " + G2y + ")");
        System.out.println("\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Starting with A* Algorithm! Voila!");
        ArrayList<Node> pathA = new ArrayList<Node>();
        pathA = findpath.A_Star(start, goal, goal2);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\n\n");

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Continue  with UCS Algorithm! Viva!");
        ArrayList<Node> pathUCS = new ArrayList<Node>();
        pathUCS = findpath.UCS_Algorithm(start, goal, goal2);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        Gui g = new Gui(dim, x,y,G1x,G1y,G2x,G2y, maze, pathA, pathUCS);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.setVisible(true);
        g.setSize(500,500);
        maz = maze.toString();

    }

}
