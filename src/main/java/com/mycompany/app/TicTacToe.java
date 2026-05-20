package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    private char[] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        currentPlayer = 'X';
    }

    public boolean makeMove(int position) {
        if (position < 0 || position > 8 || board[position] != ' ') {
            return false;
        }
        board[position] = currentPlayer;
        if (!isGameOver()) {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        return true;
    }

    public boolean isGameOver() {
        return isWin('X') || isWin('O') || isBoardFull();
    }

    public boolean isWin(char player) {
        int[][] winPatterns = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
        };
        for (int[] pattern : winPatterns) {
            if (board[pattern[0]] == player && board[pattern[1]] == player && board[pattern[2]] == player) {
                return true;
            }
        }
        return false;
    }

    public boolean isBoardFull() {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }

    public char getWinner() {
        if (isWin('X')) return 'X';
        if (isWin('O')) return 'O';
        return ' ';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public char[] getBoard() {
        return board.clone();
    }

    // Минимакс для компьютера (играет за 'O')
    public int minimax(boolean isMaximizing) {
        if (isWin('O')) return 1;
        if (isWin('X')) return -1;
        if (isBoardFull()) return 0;

        if (isMaximizing) {
            int best = -1000;
            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    board[i] = 'O';
                    best = Math.max(best, minimax(false));
                    board[i] = ' ';
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    board[i] = 'X';
                    best = Math.min(best, minimax(true));
                    board[i] = ' ';
                }
            }
            return best;
        }
    }

    public int bestMove() {
        int bestVal = -1000;
        int bestMove = -1;
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = 'O';
                int moveVal = minimax(false);
                board[i] = ' ';
                if (moveVal > bestVal) {
                    bestMove = i;
                    bestVal = moveVal;
                }
            }
        }
        return bestMove;
    }
}