package com.wheel.sys;

import com.apps.util.Console;
import com.wheel.resources.Puzzle;

import static com.wheel.resources.myColors.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * Places where there are words will be filled with "▓".
 * Blanks will be filled with "░".
 */
public class Board {
    private static Board board = null;
    private List<String> banner = new ArrayList<>(); //Read banner from file
    private List<String> boardLines = loadLinesFromFile("textFiles/boardBase.txt");
    private BoardPuzzleManager manager;
    private List<Player> players;
    private StringBuilder correctGuesses = new StringBuilder();
    private Integer currentRound;
    private Puzzle currentPuzzle;

    private Board() {

    }

    // Static methods
    private static List<String> loadLinesFromFile(String filePath) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    // Public methods

    public void showRoundWinner(Player roundWinner) {
        String filePath = "textFiles/roundWinner.txt";
        List<String> lines = loadLinesFromFile(filePath);
        lines.set(8, lines.get(8).replaceAll("\\d", currentRound.toString()));
        String name = roundWinner.getName();
        int roundBalance = roundWinner.getRoundBalance();
        int gameBalance = roundWinner.getGameBalance();

        topFiveLines();
        categoryLineFive(" Round Winner");
        for (int i = 0; i < 4; i++) {
            System.out.println(colorize(lines.get(i)));
        }
        System.out.println(overlay(lines.get(4), " !!!" + name + "!!!"));
        System.out.println(overlay(lines.get(5), "$" + roundBalance));
        for (int i = 6; i < 12; i++) {
            System.out.println(colorize(lines.get(i)));
        }
        System.out.println(overlay(lines.get(13), "Your Game Balance"));
        System.out.println(overlay(lines.get(14), "$" + gameBalance));
        for (int i = 15; i < lines.size(); i++) {
            System.out.println(colorize(lines.get(i)));
        }
    }

    public void displayBanner() {
        List<String> bannerLines = null;
        try {
            bannerLines = Files.readAllLines(Path.of("textFiles/banner.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = bannerLines.size() - 1; i >= 0; i--) {
            Console.clear();

            for (int j = i; j < bannerLines.size(); j++) {
                System.out.println("\u001b[38;5;220m" + bannerLines.get(j) + "\033[0m");
            }
            Console.pause(100L);
        }
    }

    public void displayRound(int round) {
        long flashPauseDuration = 300L;
        String roundNumber = "round" + round + ".txt";
        List<String> roundLines = null;
        try {
            roundLines = Files.readAllLines(Path.of("roundNumbers/" + roundNumber));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = roundLines.size() - 1; i >= 0; i--) {
            Console.clear();
            for (int j = i; j < roundLines.size(); j++) {
                System.out.println(roundLines.get(j));
            }
            Console.pause(100L);
        }
        Console.pause(500L);
        for (int i = 0; i < round; i++) {
            Console.clear();
            Console.pause(flashPauseDuration);
            for (var line : roundLines) {
                System.out.println(line);
            }
            Console.pause(flashPauseDuration);
            Console.clear();
            Console.pause(flashPauseDuration);
        }
        for (var line : roundLines) {
            System.out.println(line);
        }
    }

    public void update() {
        topFiveLines();
        categoryLineFive(currentPuzzle.getCategory());
        for (int i = 5; i < 7; i++) {
            System.out.println(boardLines.get(i));
        }
        List<String[]> puzzleWords = manager.determineLines();
        int index = 0;
        //This portion works right now but could be refactored later to make it more compact.
        if (puzzleWords.size() > 2) {
            String line1 = solutionLine(puzzleWords.get(index++));
            String mask = boardLines.get(7);
            System.out.println(overlaySolution(mask, line1));
            System.out.println(boardLines.get(8));
            line1 = solutionLine(puzzleWords.get(index++));
            mask = boardLines.get(9);
            System.out.println(overlaySolution(mask, line1));

            System.out.println(boardLines.get(10));
            line1 = solutionLine(puzzleWords.get(index++));
            mask = boardLines.get(11);
            System.out.println(overlaySolution(mask, line1));

            System.out.println(boardLines.get(12));

            if (index != puzzleWords.size()) {
                line1 = solutionLine(puzzleWords.get(puzzleWords.size() - 1));
                mask = boardLines.get(13);
                System.out.println(overlaySolution(mask, line1));
            } else {
                System.out.println(colorize(boardLines.get(13)));
            }
        } else {
            System.out.println(colorize(boardLines.get(7)));
            System.out.println(boardLines.get(8));
            String mask = boardLines.get(9);
            String line = solutionLine(puzzleWords.get(index++));
            System.out.println(overlaySolution(mask, line));
            System.out.println(boardLines.get(10));
            if (puzzleWords.size() == 2) {
                mask = boardLines.get(11);
                line = solutionLine(puzzleWords.get(index));
                System.out.println(overlaySolution(mask, line));

            } else {
                System.out.println(colorize(boardLines.get(11)));
            }
            System.out.println(boardLines.get(12));
            System.out.println(colorize(boardLines.get(13)));
        }
        updateRoundMoney();
        for (int i = 14; i <= 20; i++) {
            System.out.println(colorize(boardLines.get(i)));
        }
    }

    public void markCurrentPlayer(int playerIndex) {
        String old = "║                                     ║";
        int index = (playerIndex + 1) * 10 - 1;
        String updated = old.substring(0, index) + "*" + old.substring(index + 1);
        boardLines.set(16, updated);
    }

    public void showGameWinner(List<Player> players) {
        List<Player> winners = new ArrayList<>(players);
        winners.sort(Comparator.comparing(Player::getGameBalance).reversed());
        String[] overlayLines = new String[14];
        Arrays.fill(overlayLines, "");
        for (int i = 0; i < winners.size(); i++) {
            int j = i * 3;
            overlayLines[j] = winners.get(i).getName();
            overlayLines[j + 1] = "$" + Integer.toString(winners.get(i).getGameBalance());
            overlayLines[j + 2] = "";
        }
        Console.clear();
        List<String> lines = loadLinesFromFile("textFiles/endOfGame.txt");
        topFiveLines();
        categoryLineFive(" WE HAVE A WINNER!");
        System.out.println(colorize(lines.get(0)));
        System.out.println(colorize(lines.get(1)));
        for (int i = 2; i < 11; i++) {
            System.out.println(overlay(lines.get(i), overlayLines[i - 2]));
        }
        System.out.println(overlay(lines.get(11), "THANKS FOR PLAYING"));
        System.out.println(overlay(lines.get(12), "$$ WHEEL OF FORTUNE $$"));
        for (int i = 14; i < lines.size(); i++) {
            System.out.println(colorize(lines.get(i)));
        }

    }


    // Private helper methods
    private String overlay(String base, String overlay) {
        int baseLength = base.length();
        int overlayLength = overlay.length();
        int paddingLength = (baseLength - overlayLength) / 2;

        String leftPadding;
        String rightPadding;

        leftPadding = base.substring(0, paddingLength);
        if (overlay.length() % 2 == 0) {
            rightPadding = base.substring(baseLength - paddingLength - 1);
        } else {
            rightPadding = base.substring(baseLength - paddingLength);
        }
        return colorize(leftPadding + overlay + rightPadding);
    }

    private String colorize(String line) {
        String result = line.replaceAll("║", FBLUE.value() + "║" + FGOLD.value())
                .replaceAll("╗", FBLUE.value() + "╗" + FGOLD.value())
                .replaceAll("╔", FBLUE.value() + "╔" + FGOLD.value()) //▓
                .replaceAll("═", FBLUE.value() + "═" + FGOLD.value())
                .replaceAll("╝", FBLUE.value() + "╝" + FGOLD.value())
                .replaceAll("╚", FBLUE.value() + "╚" + FGOLD.value())
                .replaceAll("░", FGREN.value() + "░" + ESCAP.value())
                .replaceAll("▓", ESCAP.value() + "▓");
        String[] tokens = result.split("\\|");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            sb.append(tokens[i]);
            if (i < tokens.length - 1) sb.append(FGREN.value() + "|" + FGOLD.value());
        }
        return sb.toString();
    }

    private String overlaySolution(String base, String overlay) {
        int baseLength = base.length();
        int overlayLength = overlay.length();
        int paddingLength = (baseLength - overlayLength) / 2;

        String leftPadding;
        String rightPadding;
        if (paddingLength % 2 == 0) {
            leftPadding = base.substring(0, paddingLength - 1);
            rightPadding = base.substring(baseLength - paddingLength - 1);
        } else {
            leftPadding = base.substring(0, paddingLength - 2);
            rightPadding = base.substring(baseLength - paddingLength - 2);
        }

        return colorize(leftPadding + overlay + rightPadding);
    }

    private String solutionLine(String[] puzzleWords) {
        String line1 = Arrays.toString(puzzleWords).replace("[", "").
                replace("]", "").replace(",", "").replace(" ", "░");

        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (int i = 0; i < line1.length(); i++) {
            sb.append(line1.charAt(i));
            if (i != line1.length()) {
                sb.append("|");
            }
        }
        return maskSolutionLine(sb.toString());
    }

    private String maskSolutionLine(String line) {
        return line.replaceAll(getAnswerMask(), "▓");
    }

    private void topFiveLines() {
        for (int i = 0; i < 4; i++) {
            System.out.println(boardLines.get(i));
        }
    }

    private void categoryLineFive(String category) {
        System.out.printf(FBLUE.value() + "║");
        int leading = (36 - category.length()) / 2;
        printSpaces(leading);
        System.out.printf(FGOLD.value() + category);
        printSpaces(37 - category.length() - leading);
        System.out.printf(FBLUE.value() + "║\n");
    }

    private void printSpaces(int num) {
        for (int i = 0; i < num; i++) {
            System.out.printf(" ");
        }
    }

    private void updateRoundMoney() {
        StringBuilder sb = new StringBuilder();
        sb.append("║    ");
        int count = 0;
        for (var player : players) {
            sb.append(padBottomBracket("$" + player.getRoundBalance()));
            count++;
            if (count < 3) sb.append("|");
        }
        if (players.size() == 2) sb.append("         ");
        sb.append("    ║");
        boardLines.set(18, sb.toString());
    }

    private String padBottomBracket(String input) {
        int paddingLength = 9 - input.length();
        int paddingBefore = paddingLength / 2;
        int paddingAfter = paddingLength - paddingBefore;
        String padding = " ".repeat(paddingBefore) + " ".repeat(paddingAfter);
        return padding.substring(0, paddingBefore) + input + padding.substring(paddingBefore);

    }

    private String getAnswerMask() {
        return "[^-?!'&░|" + correctGuesses.toString() + "]";
    }

    // Public Setters and getters

    public Puzzle getCurrentPuzzle() {
        return currentPuzzle;
    }

    public void setCurrentPuzzle(Puzzle currentPuzzle) {
        this.currentPuzzle = currentPuzzle;
        manager = new BoardPuzzleManager(currentPuzzle);
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        StringBuilder sb = new StringBuilder();
        sb.append("║    ");
        int count = 0;
        for (var player : players) {
            sb.append(padBottomBracket(player.getName()));
            count++;
            if (count < 3) sb.append("|");
        }
        if (players.size() == 2) sb.append("         ");
        sb.append("    ║");
        boardLines.set(17, sb.toString());
    }

    public void updateRound(Integer round) {
        boardLines.set(13, boardLines.get(13).replaceAll("\\d", round.toString()));
        currentRound = round;
        correctGuesses = new StringBuilder();
    }

    public void recordCorrectGuess(String guess) {
        correctGuesses.append(guess);
    }

    /*
     * Possible extra
     */
    public void revealBoard() {
        //create transition from banner to board
    }
}
