package com.ivon.spcsai.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solver {

    static int n = 5, m = 3;
    static String inputfile;
    static Board initialBoard;
    static Board goalBoard;
    static String heuristic;

    public static void Initialize(String filename) {
        String inputLine = null;
        File inputFile = new File(filename);
        try {
            BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
            try {
                //read intial state from file
                int[][] maps = new int[n][m];
                for (int i = 0; i < n; i++) {
                    inputLine = inputReader.readLine();
                    StringTokenizer stk = new StringTokenizer(inputLine, "\t");
                    int j = 0;
                    while (stk.hasMoreElements()) {
                        maps[i][j++] = Integer.parseInt(stk.nextToken());   //Populate initial state
                        if (j == m) {
                            break;
                        }
                    }
                }
                initialBoard = new Board(0, maps);
                inputReader.readLine();                                     //Reads empty line between Initial state and Goal State
                //read goal state from file
                for (int i = 0; i < n; i++) {
                    inputLine = inputReader.readLine();
                    StringTokenizer stk = new StringTokenizer(inputLine, "\t");
                    int j = 0;
                    while (stk.hasMoreElements()) {
                        maps[i][j++] = Integer.parseInt(stk.nextToken());   //Populate Goal state
                        if (j == m) {
                            break;
                        }
                    }
                }
                goalBoard = new Board(0, maps);


            } finally {
                inputReader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
		args = new String[] {
			"empty",
			"res/day4/boards.txt"
		};
        if (args.length < 2) {
            System.err.println("Usage : Token heuristic inputFile");
            System.exit(-1);
        }
		heuristic = args[0];		
		inputfile = args[1];
        Initialize(inputfile);
		if (heuristic.equals("empty"))
		{
        	AStar searcher = new AStar(initialBoard, goalBoard, new ManhattanHeuristic());	//Choose heuristic function according to the input argument 0
        	searcher.search();
		}
    }
}

