package gg.lisomandiy.tictactoe;

public class AI {

    private final char aiSymbol;
    private final char humanSymbol;

    public AI(char aiSymbol, char humanSymbol) {
        this.aiSymbol = aiSymbol;
        this.humanSymbol = humanSymbol;
    }

    public int findBestMove(Board board) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < 9; i++) {
            if (!board.isEmpty(i)) {
                continue;
            }

            board.set(i, aiSymbol);
            int score = minimax(board, 0, false);
            board.set(i, Board.EMPTY);

            if (score > bestScore) {
                bestScore = score;
                bestMove = i;
            }
        }

        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMaximizing) {
        char winner = board.getWinner();

        if (winner == aiSymbol) {
            return 10 - depth;
        }
        if (winner == humanSymbol) {
            return depth - 10;
        }
        if (board.isFull()) {
            return 0;
        }

        int best = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 9; i++) {
            if (!board.isEmpty(i)) {
                continue;
            }

            board.set(i, isMaximizing ? aiSymbol : humanSymbol);
            int score = minimax(board, depth + 1, !isMaximizing);
            board.set(i, Board.EMPTY);

            best = isMaximizing ? Math.max(best, score) : Math.min(best, score);
        }

        return best;
    }
}
