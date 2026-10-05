package gg.lisomandiy.tictactoe;

import java.util.Scanner;

public class Main {

    private static final char HUMAN = 'X';
    private static final char COMPUTER = 'O';

    public static void main(String[] args) {
        Board board = new Board();
        AI ai = new AI(COMPUTER, HUMAN);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Крестики-нолики против непобедимого ИИ. Вы играете за X.");
        board.print();

        while (true) {
            int move = readHumanMove(scanner, board);
            board.set(move, HUMAN);

            if (checkGameOver(board)) {
                break;
            }

            int aiMove = ai.findBestMove(board);
            board.set(aiMove, COMPUTER);
            System.out.println();
            System.out.println("Ход компьютера: " + (aiMove + 1));
            board.print();

            if (checkGameOver(board)) {
                break;
            }
        }

        scanner.close();
    }

    private static int readHumanMove(Scanner scanner, Board board) {
        while (true) {
            System.out.print("Ваш ход (1-9): ");
            String line = scanner.nextLine().trim();

            int index;
            try {
                index = Integer.parseInt(line) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Введите число от 1 до 9.");
                continue;
            }

            if (index < 0 || index > 8 || !board.isEmpty(index)) {
                System.out.println("Клетка занята или номер неверный.");
                continue;
            }

            return index;
        }
    }

    private static boolean checkGameOver(Board board) {
        char winner = board.getWinner();

        if (winner == HUMAN) {
            System.out.println();
            System.out.println("Вы победили!");
            return true;
        }

        if (winner == COMPUTER) {
            System.out.println();
            System.out.println("Победил компьютер.");
            return true;
        }

        if (board.isFull()) {
            System.out.println();
            System.out.println("Ничья.");
            return true;
        }

        return false;
    }
}
