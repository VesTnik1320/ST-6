package com.mycompany.app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicTacToeTest {
    private TicTacToe game;

    @Before
    public void setUp() {
        game = new TicTacToe();
    }

    @Test
    public void testInitialBoardEmpty() {
        char[] board = game.getBoard();
        for (char c : board) {
            assertEquals(' ', c);
        }
        assertEquals('X', game.getCurrentPlayer());
        assertFalse(game.isGameOver());
    }

    @Test
    public void testMakeValidMove() {
        assertTrue(game.makeMove(0));
        assertEquals('O', game.getCurrentPlayer());
        assertEquals('X', game.getBoard()[0]);
    }

    @Test
    public void testMakeInvalidMoveOutOfRange() {
        assertFalse(game.makeMove(-1));
        assertFalse(game.makeMove(9));
    }

    @Test
    public void testMakeMoveOnOccupiedCell() {
        game.makeMove(0);
        assertFalse(game.makeMove(0));
    }

    @Test
    public void testWinX() {
        game.makeMove(0); 
        game.makeMove(3); 
        game.makeMove(1); 
        game.makeMove(4); 
        game.makeMove(2); 
        assertTrue(game.isWin('X'));
        assertTrue(game.isGameOver());
        assertEquals('X', game.getWinner());
    }

    @Test
    public void testWinO() {
        game.makeMove(0); 
        game.makeMove(1); 
        game.makeMove(2); 
        game.makeMove(4); 
        game.makeMove(5); 
        game.makeMove(7); 
        assertTrue(game.isWin('O'));
        assertTrue(game.isGameOver());
        assertEquals('O', game.getWinner());
    }

    @Test
    public void testDraw() {
        int[] moves = {0,1,2,4,3,5,7,6,8};
        for (int i = 0; i < moves.length; i++) {
            game.makeMove(moves[i]);
        }
        assertTrue(game.isBoardFull());
        assertTrue(game.isGameOver());
        assertEquals(' ', game.getWinner());
    }

    @Test
    public void testBestMoveReturnsValidIndex() {
        int move = game.bestMove();
        assertTrue(move >= 0 && move < 9);
        assertEquals(' ', game.getBoard()[move]);
    }

    @Test
    public void testMinimaxOnEmptyBoard() {
        int score = game.minimax(true);
        assertTrue(score >= -1 && score <= 1);
    }

    @Test
    public void testBestMoveDoesNotModifyBoard() {
        char[] original = game.getBoard().clone();
        game.bestMove();
        assertArrayEquals(original, game.getBoard());
    }
}
