/*
 * Top.java
 *
 * Version:
 *    $Id$
 *
 * Revisions:
 *    &Log$
 *
 */

import java.util.*;

/** 
 * This class represents a player who knows how to play the game of Mancala. 
 *
 * @author     Andrew Sinn
 *
 */

public class Top {   

    /** 
     *  This method calls the appropriate strategy.
     *
     *  @param    board   the current state of the game
     *
     *  @return           the selected column of the board     
     */
    public int strategy(Board board) {

        //return strategy1(board);
        return randStrategy(board);
    }

    /**
     *  Take a turn using a random strategy.
     *
     *  @param    board   the current state of the game
     *
     *  @return           the selected column of the board  
     */
    public int randStrategy(Board board) {

        int row = 1, column = 1;
        boolean valid = false;
        
        while (!valid) {
            column = (int)(Math.random()*(board.WIDTH)) + 1;
            if ( (column > 0) && (column < board.WIDTH-1) 
                 && (board.getSeeds(row,column) != 0) )
                valid = true;
        }
        
        return column;
    }

    /**
     *  Take a turn using another strategy. 
     *
     *  @param    board   the current state of the game
     *
     *  @return           the selected column of the board
     */
    public int strategy1(Board board) {
        
        int row = 1;
        
        return -1;
    }

    /**
     *  What happens when a player makes a move.
     *
     *  @param    board    state of the game before move (changed here)
     *  @param    column   selected column of board 
     *
     *  @return            1 if extra_turn, 2 if capture
     */
    public int move(Board board, int column) {
        
        int row = 1;

        int distribute = board.getSeeds(row, column);
        board.setSeeds(row,column,0);
        board.setColor(row,column,"Green");
        
        while(distribute > 0) {
        	for(int i = column -1; i >=0; i--) {
        		int newSeeds = board.getSeeds(row, i);
        		newSeeds++;
        		distribute--;
        		
        		board.setSeeds(row, i, newSeeds);
        		
        		//Check to see if move results in a capture
        		if(distribute == 0 && newSeeds == 1 && i !=0) {
        			int opponentSeeds = board.getSeeds(2, i);
        			int mancala = board.getSeeds(1, 0);
        			mancala = mancala + opponentSeeds + newSeeds;
        			board.setSeeds(1, i, 0);
        			board.setSeeds(1, 0, mancala);
        			board.setSeeds(2, i, 0);
        			return 2;
        		}
        		
        		if(distribute == 0 && i == 0) {
        			return 1;
        		}
        	}
        	
        	for(int j = 1; j < Board.WIDTH; j++) {
        		
        	}

        return -1;
    }
}
