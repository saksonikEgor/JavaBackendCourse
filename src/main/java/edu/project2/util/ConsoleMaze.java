package edu.project2.util;

import edu.project2.generation.Generator;
import edu.project2.model.Maze;
import edu.project2.options.ApplicationOptions;
import edu.project2.solving.Solver;
import edu.project2.view.Renderer;
import edu.project2.view.console.ConsoleRenderer;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ConsoleMaze {
    private final Scanner scanner;
    private Maze maze;
    private Generator generator;
    private Solver solver;
    private final Renderer renderer = new ConsoleRenderer();
    private final Random generationRandom;
    private boolean isMazeAvailable = false;

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
            displaySuggestionToSelectAGenerationAlgorithm();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                ApplicationOptions.GenerationAlgorithm[] generationAlgorithms =
                    ApplicationOptions.GenerationAlgorithm.values();

                if (choice < 0 || choice >= generationAlgorithms.length) {
                    System.out.println("Incorrect option. Please try again");
                    continue;
                }

                generator = generationAlgorithms[choice].getGenerator(generationRandom);

            } catch (InputMismatchException e) {
                System.out.println("Incorrect option. Please try again");
            } catch (Exception e) {
                System.out.println("Unknown error");
            }
        }

//        while (true) {
//            help();
//            try {
//                int choice = scanner.nextInt();
//                scanner.nextLine();
//                switch (choice) {
//                    case 0 -> {
//                        exit();
//                        return;
//                    }
//                    case 1 -> generate();
//                    case 2 -> findEscape();
//                    default -> System.out.println("Incorrect option. Please try again");
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Incorrect option. Please try again");
//            } catch (Exception e) {
//                System.out.println("Unknown error");
//            }
//        }
    }

    private void displaySuggestionToSelectAGenerationAlgorithm() {
        System.out.println("=== Select a maze generation algorithm ===");
        ApplicationOptions.GenerationAlgorithm[] algorithms = ApplicationOptions.GenerationAlgorithm.values();
        for (int i = 1; i <= algorithms.length; i++) {
            System.out.println(i + ". " + algorithms[i]);
        }
        System.out.println("0. Exit");
    }

    private void displaySuggestionToSelectASolvingAlgorithm() {
        System.out.println("=== Select a maze solving algorithm ===");
        ApplicationOptions.SolvingAlgorithm[] algorithms = ApplicationOptions.SolvingAlgorithm.values();
        for (int i = 1; i <= algorithms.length; i++) {
            System.out.println(i + ". " + algorithms[i]);
        }
        System.out.println("0. Exit");
    }

    private void help() {
        System.out.println("=== Menu ===");
        System.out.println("1. Generate a new maze");
        if (isMazeAvailable) {
            System.out.println("2. Find the escape");
        }
        System.out.println("0. Exit");
    }

    private void exit() {
        scanner.close();
        System.out.println("Bye!");
    }

    private void generate() {
        System.out.println("Enter the size of the new maze (in the [size] or [height width] format)");
        String line = scanner.nextLine();
        String[] split = line.split(" ");
        if (split.length == 1) {
            int size = parseInt(split[0]);
            maze = new Maze(size);
        } else if (split.length == 2) {
            int height = parseInt(split[0]);
            int width = parseInt(split[1]);
            maze = new Maze(height, width);
        } else {
            System.out.println("Cannot generate a maze. Invalid size");
        }
        isMazeAvailable = true;
        display();
    }

    private void display() {
//        System.out.println(maze);
    }

    private void findEscape() {
//        System.out.println(maze.findEscape());
    }
}
