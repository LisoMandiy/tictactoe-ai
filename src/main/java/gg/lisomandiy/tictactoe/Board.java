package gg.lisomandiy.tictactoe;

public class Board {

    public static final char EMPTY = ' ';

    private final char[] cells = new char[9];

    public Board() {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = EMPTY;
        }
    }

    public char get(int index) {
        return cells[index];
    }

    public void set(int index, char symbol) {
        cells[index] = symbol;
    }

    public boolean isEmpty(int index) {
        return cells[index] == EMPTY;
    }

    public boolean isFull() {
        for (char cell : cells) {
            if (cell == EMPTY) {
                return false;
            }
        }
        return true;
    }

    public char getWinner() {
        int[][] lines = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] line : lines) {
            char a = cells[line[0]];
            char b = cells[line[1]];
            char c = cells[line[2]];

            if (a != EMPTY && a == b && b == c) {
                return a;
            }
        }

        return EMPTY;
    }

    public void print() {
        for (int row = 0; row < 3; row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < 3; col++) {
                int index = row * 3 + col;
                line.append(cells[index] == EMPTY ? Character.forDigit(index + 1, 10) : cells[index]);
                if (col < 2) {
                    line.append(" | ");
                }
            }
            System.out.println(line);
            if (row < 2) {
                System.out.println("---------");
            }
        }
    }
}
