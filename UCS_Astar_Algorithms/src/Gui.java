/**
 * @Course: Artificial Intelligence  MYY602
 * @Project A* and UCS algorithms implementations
 *
 * @Author: Chouliaras Ioannis   AM : 2631
 * @Author: Kaloudis   Spyridon  AM : 2447
 * @Author: Molos      Ioannis   AM : xxxx
 */


import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame {
	private Container contents;
	private Color black = Color.BLACK;
	private Color red = Color.RED;
	private Color white = Color.WHITE;
	private Color gray = Color.GRAY;
	private Color orange = Color.ORANGE;
	private Color blue = Color.CYAN;
	private Color purple = Color.MAGENTA;
	private int row = 0;
	private int collumn = 0;
	int n=0;
    private ArrayList<Node> pathA = new ArrayList<Node>();
    private ArrayList<Node> pathUCS = new ArrayList<Node>();

	public Gui(int dim, int startx, int starty, int g1x, int g1y, int g2x, int g2y, Maze maze, ArrayList<Node> pathA, ArrayList<Node> pathUCS){
		super("GUI");
		Random rand = new Random();
		JPanel[][] squares = new JPanel [dim][dim];
		this.pathA=pathA;
		this.pathUCS=pathUCS;
		contents = getContentPane();
		contents.setLayout(new GridLayout(dim,dim));
		
		for(int i=0; i<dim; i++){
			for(int j=0; j<dim; j++){
				squares[i][j] = new JPanel();
				squares[i][j].setBackground(gray);
				squares[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				contents.add(squares[i][j]);
			}
		}
		for(int i=0; i<dim; i++){
			for(int j=0; j<dim; j++){
				if (maze.getValue(i, j)==1){
					squares[i][j].setBackground(black);
				}
			}
		}
		squares[startx][startx].setBackground(white);
		squares[g1x][g1y].setBackground(orange);
		squares[g2x][g2y].setBackground(orange);
		
		int size=pathA.size();
		for (int i=0; i<size; i++){
			squares[pathA.get(i).getX()][pathA.get(i).getY()].setBackground(blue);
			squares[pathUCS.get(i).getX()][pathUCS.get(i).getY()].setBackground(purple);
			if(pathA.get(i).getX()==g1x && pathA.get(i).getY()==g1y){
				squares[g1x][g1y].setBackground(red);
			}
			if(pathA.get(i).getX()==g2x && pathA.get(i).getY()==g2y){
				squares[g2x][g2y].setBackground(red);
			}
		}
	}
}