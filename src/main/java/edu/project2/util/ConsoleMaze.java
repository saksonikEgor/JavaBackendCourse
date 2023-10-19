package edu.project2.util;

import edu.project2.generation.Generator;
import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.options.ApplicationOptions;
import edu.project2.solving.Solver;
import edu.project2.view.Renderer;
import edu.project2.view.console.ConsoleRenderer;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class ConsoleMaze {
    private final Scanner scanner;
    private final Renderer renderer = new ConsoleRenderer();
    private final Random generationRandom;
    private Maze maze;
    private List<Cell> path;
    private Generator generator;

    public ConsoleMaze() {
        scanner = new Scanner(System.in);
        generationRandom = new Random();
    }

    public ConsoleMaze(Random generationRandom) {
        scanner = new Scanner(System.in);
        this.generationRandom = generationRandom;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void run() {
        while (true) {
            while (!selectGenerator()) {
                System.out.println(ApplicationOptions.INVALID_NUMBER_OF_ALGORITHM_MESSAGE);
            }

            while (!selectMazeSizeAndGenerateTheMaze()) {
                System.out.println(ApplicationOptions.INVALID_MAZE_SIZE_MESSAGE);
            }
            displayGeneratedMaze();

            while (!selectSolverAndSolveTheMaze()) {
                System.out.println(ApplicationOptions.INVALID_NUMBER_OF_ALGORITHM_MESSAGE);
            }
            displaySolvedMaze();
        }
    }

    private boolean selectGenerator() {
        displaySuggestionToSelectAGenerationAlgorithm();
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                exit();
            }

            ApplicationOptions.GenerationAlgorithm[] generationAlgorithms =
                ApplicationOptions.GenerationAlgorithm.values();

            if (choice < 0 || choice > generationAlgorithms.length) {
                return false;
            }
            generator = generationAlgorithms[choice - 1].getGenerator(generationRandom);
        } catch (InputMismatchException e) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private boolean selectMazeSizeAndGenerateTheMaze() {
        displaySuggestionToSelectAMazeSize();

        String line = scanner.nextLine();
        String[] split = line.split(" ");

        try {
            if (split.length == 1) {
                int choice = parseInt(split[0]);

                if (choice == 0) {
                    exit();
                }
                maze = generator.generate(choice, choice);
            } else if (split.length == 2) {
                maze = generator.generate(parseInt(split[0]), parseInt(split[1]));
            } else {
                return false;
            }
        } catch (InputMismatchException | NumberFormatException e) {
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private boolean selectSolverAndSolveTheMaze() {
        displaySuggestionToSelectASolvingAlgorithm();
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                exit();
            }

            ApplicationOptions.SolvingAlgorithm[] solvingAlgorithms =
                ApplicationOptions.SolvingAlgorithm.values();

            if (choice < 0 || choice > solvingAlgorithms.length) {
                return false;
            }

            Solver solver = solvingAlgorithms[choice - 1].getSolver();
            path = solver.solve(maze, maze.getEntrance(), maze.getExit());
        } catch (InputMismatchException e) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void displaySuggestionToSelectAGenerationAlgorithm() {
        System.out.println(ApplicationOptions.SELECT_A_MAZE_GENERATION_ALGORITHM_MESSAGE);
        ApplicationOptions.GenerationAlgorithm[] algorithms = ApplicationOptions.GenerationAlgorithm.values();

        for (int i = 1; i <= algorithms.length; i++) {
            System.out.println(i + ". " + algorithms[i - 1]);
        }
        System.out.println(ApplicationOptions.ENTER_COMMAND_TO_EXIT_MESSAGE);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void displaySuggestionToSelectASolvingAlgorithm() {
        System.out.println(ApplicationOptions.SELECT_A_MAZE_SOLVING_ALGORITHM_MESSAGE);
        ApplicationOptions.SolvingAlgorithm[] algorithms = ApplicationOptions.SolvingAlgorithm.values();

        for (int i = 1; i <= algorithms.length; i++) {
            System.out.println(i + ". " + algorithms[i - 1]);
        }
        System.out.println(ApplicationOptions.ENTER_COMMAND_TO_EXIT_MESSAGE);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void displaySuggestionToSelectAMazeSize() {
        System.out.println(ApplicationOptions.SUGGESTION_TO_SELECT_A_MAZE_SIZE_MESSAGE);
        System.out.println(ApplicationOptions.ENTER_COMMAND_TO_EXIT_MESSAGE);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void displayGeneratedMaze() {
        System.out.println(renderer.render(maze));
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void displaySolvedMaze() {
        System.out.println(renderer.render(maze, path));
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void exit() {
        scanner.close();
        System.out.println(ApplicationOptions.FAREWELL_MESSAGE);
        System.exit(0);
    }
}
