package edu.project2.util;

import edu.project2.model.Maze;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class ConsoleMaze {
    private Scanner scanner;
    private Maze maze;
    private boolean isMazeAvailable = false;

    public void run() {
        scanner = new Scanner(System.in);
        while (true) {
            help();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 0 -> {
                        exit();
                        return;
                    }
                    case 1 -> generate();
                    case 2 -> display();
                    case 3 -> findEscape();
                    default -> System.out.println("Incorrect option. Please try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect option. Please try again");
            } catch (Exception e) {
                System.out.println("Unknown error");
            }
        }
    }

    private void help() {
        System.out.println("=== Menu ===");
        System.out.println("1. Generate a new maze");
        if (isMazeAvailable) {
            System.out.println("2. Display the maze");
            System.out.println("3. Find the escape");
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
