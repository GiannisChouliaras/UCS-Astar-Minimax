/**
 * @Course: Artificial Intelligence  MYY602
 * @Project A* and UCS algorithms implementations
 *
 * @Author: Chouliaras Ioannis   AM : 2631
 * @Author: Kaloudis   Spyridon  AM : 2447
 * @Author: Molos      Ioannis   AM : xxxx
 */

import java.util.ArrayList;

public class FindPath {

    /** Field */
    Maze maze;

    /**
     * Constructor
     * @param maze = our maze that we've made in Main class.
     */
    public FindPath(Maze maze) {
        this.maze = maze;
    }

    /**
     * Implementing A* Algorithm
     * @param start = our starting point. Where robot begins his journey.
     * @param goal  = final destination number 1
     * @param goal2 = final destination number 2
     */
    public ArrayList<Node> A_Star(Node start, Node goal, Node goal2) {

        int moves = -1;
        int cost = 0;
        int hDistance = 0;
        boolean found = false;

        ArrayList<Node> openSet = new ArrayList<Node>();
        ArrayList<Node> closedSet = new ArrayList<Node>();

        start.setGCost(0);
        start.setFather(null);
        int temp  = getDistance(start, goal);
        int temp2 = getDistance(start, goal2);

        if (temp < temp2) hDistance = temp;
        else hDistance = temp2;

        start.setHCost(hDistance);
        openSet.add(start);

        while (!openSet.isEmpty())
        {
            Node current = openSet.get(0);
            for (Node node : openSet) {
                if (current.getFCost() <= node.getFCost()) continue;
                else current = node;
            }

            moves++;

            /** Check if GOAL1 */
            if (current.getX() == goal.getX() && current.getY() == goal.getY()) {

                ArrayList<Node> path = new ArrayList<Node>();
                int counterBro = -1;

                while(true){
                    if(current.getX()== start.getX() && current.getY()== start.getY()){
                        System.out.print("Path Found:\t" );
                        found = true;
                        for (int i = counterBro; i >= 0; i--) {
                            cost++;
                            System.out.print("(" + path.get(i).getX() + ", " + path.get(i).getY() + ")\t");
                        }
                        System.out.println("\n");
                        System.out.println("Cost for this path is : " + cost);
                        System.out.println("Moves that we've made to find path: " + moves);
                        return path;
                        //break;

                    }else{
                        path.add(current);
                        counterBro++;
                        current = current.getFather();
                    }
                }
                //break;
            }
            /** Check if GOAL2 */
            if (current.getX() == goal2.getX() && current.getY() == goal2.getY()) {

                ArrayList<Node> path = new ArrayList<Node>();
                int counterBro = -1;

                while(true){
                    if (current.getX() == start.getX() && current.getY() == start.getY()) {
                        System.out.print("Path Found:\t");
                        found = true;
                        for (int i = counterBro; i >= 0; i--) {
                            cost++;
                            System.out.print("(" + path.get(i).getX() + ", " + path.get(i).getY() + ")\t");
                        }
                        System.out.println("\n");
                        System.out.println("Cost for this path is : " + cost);
                        System.out.println("Moves that we've made to find path: " + moves);
                        return path;
                        //break;

                    } else{
                        path.add(current);
                        counterBro++;
                        current = current.getFather();

                    }
                }
                //break;
            }

            openSet.remove(current);
            closedSet.add(current);

            ArrayList<Node> neighbors = new ArrayList<Node>();
            neighbors = getNeighbours(current);

            for (Node node : neighbors) {
                if (isInList(node, closedSet)) continue;

                int temporary_gCost = current.getGCost() + 1;

                if (!isInList(node, openSet)) openSet.add(node);
                else if (temporary_gCost >= node.getGCost()) continue;

                node.setFather(current);
                node.setGCost(temporary_gCost);

                int tmp  = getDistance(node, goal);
                int tmp2 = getDistance(node, goal2);
                if (tmp < tmp2) hDistance = tmp;
                else hDistance = tmp2;

                node.setHCost(hDistance);
            }
        }

        if (!found)
            System.out.println("Ok, I couldn't find any path. I am an A* Algorithm and i think if there was any path, i should find it. Check it");
        return null;

    }

    /**
     * Implementing UCS Algorithm
     * @param start = our starting point. Where robot begins his journey.
     * @param goal  = final destination number 1
     * @param goal2 = final destination number 2
     */
    public ArrayList<Node> UCS_Algorithm(Node start, Node goal, Node goal2){

        int moves = -1;
        int cost = 0;
        boolean found = false;

        ArrayList<Node> openSet   = new ArrayList<Node>();
        ArrayList<Node> closedSet = new ArrayList<Node>();

        start.setGCost(0);
        start.setFather(null);
        openSet.add(start);

        while (!openSet.isEmpty())
        {
            Node current = openSet.get(0);
            for (Node node : openSet) {
                if (current.getGCost() <= node.getGCost()) continue;
                else current = node;
            }

            moves++;

            /** Check if GOAL1 */
            if (current.getX() == goal.getX() && current.getY() == goal.getY()) {

                ArrayList<Node> path = new ArrayList<Node>();
                int counterBro = -1;

                while(true){
                    if(current.getX()== start.getX() && current.getY()== start.getY()){
                        System.out.print("Path Found:\t" );
                        found = true;
                        for (int i = counterBro; i >= 0; i--) {
                            cost++;
                            System.out.print("(" + path.get(i).getX() + ", " + path.get(i).getY() + ")\t");
                        }
                        System.out.println("\n");
                        System.out.println("Cost for this path is : " + cost);
                        System.out.println("Moves that we've made to find path: " + moves);
                        break;

                    }else{
                        path.add(current);
                        counterBro++;
                        current = current.getFather();
                    }
                }
                return path;
                //break;
            }

            /** Check if GOAL2 */
            if (current.getX() == goal2.getX() && current.getY() == goal2.getY()) {

                ArrayList<Node> path = new ArrayList<Node>();
                int counterBro = -1;

                while(true){
                    if (current.getX() == start.getX() && current.getY() == start.getY()) {
                        System.out.print("Path Found:\t");
                        found = true;
                        for (int i = counterBro; i >= 0; i--) {
                            cost++;
                            System.out.print("(" + path.get(i).getX() + ", " + path.get(i).getY() + ")\t");
                        }
                        System.out.println("\n");
                        System.out.println("Cost for this path is : " + cost);
                        System.out.println("Moves that we've made to find path: " + moves);
                        break;

                    } else{
                        path.add(current);
                        counterBro++;
                        current = current.getFather();

                    }
                }
                return path;
                //break;
            }

            openSet.remove(current);
            closedSet.add(current);

            ArrayList<Node> neighbors = new ArrayList<Node>();
            neighbors = getNeighbours(current);

            for (Node node : neighbors) {
                if (isInList(node, closedSet)) continue;

                int temporary_gCost = current.getGCost() + 1;

                if (!isInList(node, openSet)) openSet.add(node);
                else if (temporary_gCost >= node.getGCost()) continue;

                node.setFather(current);
                node.setGCost(temporary_gCost);
            }

        }
        if (!found)
            System.out.println("Ok, I couldn't find any path. I am an A* Algorithm and i think if there was any path, i should find it. Check it");
        return null;
    }

    /**
     * getDistance
     * @param start = starting node
     * @param end = ending node
     * @return = the distance between starting and ending.
     */
    public int getDistance(Node start, Node end) {

        if (start.getX() > maze.getDimension() || start.getX() < 0) return 0;
        if (start.getY() > maze.getDimension() || start.getY() < 0) return 0;
        if (end.getX() > maze.getDimension() || end.getX() < 0) return 0;
        if (end.getY() > maze.getDimension() || end.getY() < 0) return 0;

        int distX = Math.abs(start.getX() - end.getX());
        int distY = Math.abs(start.getY() - end.getY());

        if (distX > distY) return (1 * distY) + 1 * (distX - distY);
        else return (1 * distX) + 1 * (distY - distX);
    }

    /**
     * getNeigbours
     * @param node = our current node.
     * @return an ArrayList of all his neighbors who can visit
     */

    public ArrayList<Node> getNeighbours(Node node) {
        ArrayList<Node> neighbours = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;

                int checkX = node.getX() + x;
                int checkY = node.getY() + y;

                if (checkX >= 0 && checkX < maze.getDimension() && checkY >= 0 && checkY < maze.getDimension() && maze.isFree(checkX, checkY)) {
                    Node neighbor = new Node(checkX, checkY);
                    neighbours.add(neighbor);
                }
            }
        }
        return neighbours;
    }

    /**
     * inInList
     * @param node = the node we want to check if is in the ArrayList.
     * @param visited = the ArrayList
     * @return boolean true if in, false if not.
     */
    public boolean isInList(Node node, ArrayList<Node> visited) {
        boolean bool = false;
        for (Node noding : visited)
            if (node.getX() == noding.getX() && node.getY() == noding.getY()) return true;
        return false;
    }
}
