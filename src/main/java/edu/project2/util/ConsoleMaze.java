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
    private Maze maze;
    private List<Cell> path;
    private Generator generator;
    private final Renderer renderer = new ConsoleRenderer();
    private final Random generationRandom;

    public ConsoleMaze() {
        scanner = new Scanner(System.in);
        generationRandom = new Random();
    }

    public ConsoleMaze(Random generationRandom) {
        scanner = new Scanner(System.in);
        this.generationRandom = generationRandom;
    }

    public void run() {
        while (true) {
            while (!selectGenerator()) {
                System.out.println("Incorrect option. Please try again");
            }

            while (!selectMazeSizeAndGenerateTheMaze()) {
                System.out.println("Incorrect option. Please try again");
            }
            displayGeneratedMaze();

            while (!selectSolverAndSolveTheMaze()) {
                System.out.println("Cannot generate a maze. Invalid size. Please try again");
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

    private boolean selectMazeSizeAndGenerateTheMaze() {
        displaySuggestionToSelectAMazeSize();

        String line = scanner.nextLine();
        String[] split = line.split(" ");

        try {
            switch (split.length) {
                case 1 -> {
                    int choice = parseInt(split[0]);

                    if (choice == 0) {
                        exit();
                    }
                    maze = generator.generate(choice, choice);
                }
                case 2 -> maze = generator.generate(parseInt(split[0]), parseInt(split[1]));
                default -> {
                    return false;
                }
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

//            Solver solver = solvingAlgorithms[choice].getSolver()
            Solver solver = solvingAlgorithms[choice - 1].getSolver();
            path = solver.solve(maze, maze.getEntrance(), maze.getExit());
        } catch (InputMismatchException e) {
            return false;
        }
        return true;
    }

    private void displaySuggestionToSelectAGenerationAlgorithm() {
        System.out.println("=== Select a maze generation algorithm ===");
        ApplicationOptions.GenerationAlgorithm[] algorithms = ApplicationOptions.GenerationAlgorithm.values();
        for (int i = 1; i <= algorithms.length; i++) {
            System.out.println(i + ". " + algorithms[i - 1]);
        }
        System.out.println("0. Exit");
    }

    private void displaySuggestionToSelectASolvingAlgorithm() {
        System.out.println("=== Select a maze solving algorithm ===");
        ApplicationOptions.SolvingAlgorithm[] algorithms = ApplicationOptions.SolvingAlgorithm.values();
        for (int i = 1; i <= algorithms.length; i++) {
            System.out.println(i + ". " + algorithms[i - 1]);
        }
        System.out.println("0. Exit");
    }

    private void displaySuggestionToSelectAMazeSize() {
        System.out.println("=== Enter the size of the new maze in the [size] or [height width] format "
            + "for example: 8 12 ===");
        System.out.println("0. Exit");
    }

    private void displayGeneratedMaze() {
        System.out.println(renderer.render(maze));
    }

    private void displaySolvedMaze() {
        System.out.println(renderer.render(maze, path));
    }

    private void exit() {
        scanner.close();
        System.out.println("Bye!");
        System.exit(0);
    }
}
