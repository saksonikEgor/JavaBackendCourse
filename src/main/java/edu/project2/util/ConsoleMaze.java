package edu.project2.util;

import edu.project2.generation.Generator;
import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.properties.ApplicationProperties;
import edu.project2.solving.Solver;
import edu.project2.view.Renderer;
import edu.project2.view.console.ConsoleRenderer;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleMaze {
    private static final String NUMBER_REGEX = "^\\d+$";
    private final Scanner scanner;
    private final Random generationRandom;
    private Maze maze;
    private List<Cell> path;
    private Generator generator;
    private Solver solver;
    private final Renderer renderer = new ConsoleRenderer();

    public ConsoleMaze() {
        scanner = new Scanner(System.in);
        generationRandom = new Random(5);
    }

    public ConsoleMaze(Scanner scanner, Random generationRandom) {
        this.scanner = scanner;
        this.generationRandom = generationRandom;
    }

    enum InputType {
        SelectGenerator,
        SelectSolver,
        EnterMazeSize
    }

    public void run() {
        List<Integer> input;

        while (true) {
            displaySuggestionToSelectAGenerationAlgorithm();
            input = formatInput(InputType.SelectGenerator);
            if (input.get(0) == 0) {
                break;
            }
            setGenerator(input);

            displaySuggestionToSelectAMazeSize();
            input = formatInput(InputType.EnterMazeSize);
            if (input.get(0) == 0) {
                break;
            }
            generateTheMaze(input);
            displayGeneratedMaze();

            displaySuggestionToSelectASolvingAlgorithm();
            input = formatInput(InputType.SelectSolver);
            if (input.get(0) == 0) {
                break;
            }
            setSolver(input);
            solveTheMaze();
            displaySolvedMaze();
        }
    }

    private List<Integer> formatInput(InputType type) {
        while (true) {
            try {
                String[] lines = scanner.nextLine().split(" ");
                if (lines.length < 1 || lines.length > 2) {
                    System.out.println(ApplicationProperties.INVALID_NUMBER_INPUT_MESSAGE);
                    continue;
                }

                if (!Arrays.stream(lines).allMatch(s -> s.matches(NUMBER_REGEX))) {
                    System.out.println(ApplicationProperties.INVALID_NUMBER_INPUT_MESSAGE);
                    continue; // wrong
                }

                if (lines.length == 1 && Integer.parseInt(lines[0]) == 0) {
                    return List.of(0);
                }

                switch (type) {
                    case SelectGenerator -> {
                        if (isCorrectInputForSelectionGenerator(lines)) {
                            return List.of(Integer.parseInt(lines[0]));
                        }
                    }
                    case SelectSolver -> {
                        if (isCorrectInputForSelectionSolver(lines)) {
                            return List.of(Integer.parseInt(lines[0]));
                        }
                    }
                    case EnterMazeSize -> {
                        if (isCorrectInputForCreatingMaze(lines)) {
                            if (lines.length == 2) {
                                return List.of(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]));
                            } else {
                                return List.of(Integer.parseInt(lines[0]), Integer.parseInt(lines[0]));
                            }
                        }
                    }
                    default -> throw new IllegalArgumentException();
                }
                System.out.println(ApplicationProperties.INVALID_NUMBER_INPUT_MESSAGE);
            } catch (InputMismatchException e) {
                System.out.println(ApplicationProperties.INVALID_NUMBER_INPUT_MESSAGE);
            }
        }
    }

    private boolean isCorrectInputForSelectionGenerator(String[] lines) {
        int choice = Integer.parseInt(lines[0]);
        ApplicationProperties.GenerationAlgorithm[] generationAlgorithms =
            ApplicationProperties.GenerationAlgorithm.values();

        return choice >= 1 && choice <= generationAlgorithms.length && lines.length != 2;
    }

    private boolean isCorrectInputForSelectionSolver(String[] lines) {
        int choice = Integer.parseInt(lines[0]);
        ApplicationProperties.SolvingAlgorithm[] solvingAlgorithms =
            ApplicationProperties.SolvingAlgorithm.values();

        return choice >= 1 && choice <= solvingAlgorithms.length && lines.length != 2;
    }

    private boolean isCorrectInputForCreatingMaze(String[] lines) {
        int height;
        int width;

        if (lines.length == 2) {
            height = Integer.parseInt(lines[0]);
            width = Integer.parseInt(lines[1]);
        } else {
            height = Integer.parseInt(lines[0]);
            width = Integer.parseInt(lines[0]);
        }

        return height >= ApplicationProperties.SIZE_LOWER_BOUND && width >= ApplicationProperties.SIZE_LOWER_BOUND;
    }

    private void setGenerator(List<Integer> choice) {
        generator = ApplicationProperties
            .GenerationAlgorithm
            .values()[choice.get(0) - 1]
            .getGenerator(generationRandom);
    }

    private void setSolver(List<Integer> choice) {
        solver = ApplicationProperties
            .SolvingAlgorithm
            .values()[choice.get(0) - 1]
            .getSolver();
    }

    private void generateTheMaze(List<Integer> params) {
        maze = generator.generate(params.get(0), params.get(1));
    }

    private void solveTheMaze() {
        path = solver.solve(maze, maze.getEntrance(), maze.getExit());
    }

//    @SuppressWarnings("RegexpSinglelineJava")
//    public void run() {
//        while (true) {
//            while (!selectGenerator()) {
//                System.out.println(ApplicationProperties.INVALID_NUMBER_INPUT_MESSAGE);
//            }
//
//            while (!selectMazeSizeAndGenerateTheMaze()) {
//                System.out.println(ApplicationProperties.INVALID_MAZE_SIZE_MESSAGE);
//            }
//            displayGeneratedMaze();
//
//            while (!selectSolverAndSolveTheMaze()) {
//                System.out.println(ApplicationProperties.INVALID_NUMBER_INPUT_MESSAGE);
//            }
//            displaySolvedMaze();
//        }
//    }
//
//    private boolean selectGenerator() {
//        displaySuggestionToSelectAGenerationAlgorithm();
//
//        try {
//            ApplicationProperties.GenerationAlgorithm[] generationAlgorithms =
//                ApplicationProperties.GenerationAlgorithm.values();
//
//            String line = scanner.nextLine();
//            if (!line.matches(NUMBER_REGEX)) {
//                return false;
//            }
//
//            int choice = Integer.parseInt(line);
//            if (choice == 0) {
//                exit();
//            }
//
//            if (choice < 0 || choice > generationAlgorithms.length) {
//                return false;
//            }
//            generator = generationAlgorithms[choice - 1].getGenerator(generationRandom);
//        } catch (InputMismatchException e) {
//            return false;
//        }
//        return true;
//    }
//
//    @SuppressWarnings("RegexpSinglelineJava")
//    private boolean selectMazeSizeAndGenerateTheMaze() {
//        displaySuggestionToSelectAMazeSize();
//
//        try {
//            String line = scanner.nextLine();
//            String[] split = line.split(" ");
//
//            if (!Arrays.stream(split).allMatch(str -> str.matches(NUMBER_REGEX))
//                || split.length < 1 || split.length > 2) {
//                return false;
//            }
//            if (split.length == 1) {
//                int choice = parseInt(split[0]);
//
//                if (choice == 0) {
//                    exit();
//                }
//                maze = generator.generate(choice, choice);
//            } else {
//                maze = generator.generate(parseInt(split[0]), parseInt(split[1]));
//            }
//        } catch (InputMismatchException | IllegalArgumentException e) {
//            return false;
//        }
//        return true;
//    }
//
//    private boolean selectSolverAndSolveTheMaze() {
//        displaySuggestionToSelectASolvingAlgorithm();
//        try {
//            ApplicationProperties.SolvingAlgorithm[] solvingAlgorithms =
//                ApplicationProperties.SolvingAlgorithm.values();
//
//            String line = scanner.nextLine();
//            if (!line.matches(NUMBER_REGEX)) {
//                return false;
//            }
//
//            int choice = Integer.parseInt(line);
//            if (choice == 0) {
//                exit();
//            }
//
//            if (choice < 0 || choice > solvingAlgorithms.length) {
//                return false;
//            }
//            Solver solver = solvingAlgorithms[choice - 1].getSolver();
//            path = solver.solve(maze, maze.getEntrance(), maze.getExit());
//        } catch (InputMismatchException e) {
//            return false;
//        }
//        return true;
//    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void displaySuggestionToSelectAGenerationAlgorithm() {
        System.out.println(ApplicationProperties.SELECT_A_MAZE_GENERATION_ALGORITHM_MESSAGE);
        ApplicationProperties.GenerationAlgorithm[] algorithms = ApplicationProperties.GenerationAlgorithm.values();

        for (int i = 1; i <= algorithms.length; i++) {
            System.out.println(i + ". " + algorithms[i - 1]);
        }
        System.out.println(ApplicationProperties.ENTER_COMMAND_TO_EXIT_MESSAGE);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void displaySuggestionToSelectASolvingAlgorithm() {
        System.out.println(ApplicationProperties.SELECT_A_MAZE_SOLVING_ALGORITHM_MESSAGE);
        ApplicationProperties.SolvingAlgorithm[] algorithms = ApplicationProperties.SolvingAlgorithm.values();

        for (int i = 1; i <= algorithms.length; i++) {
            System.out.println(i + ". " + algorithms[i - 1]);
        }
        System.out.println(ApplicationProperties.ENTER_COMMAND_TO_EXIT_MESSAGE);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void displaySuggestionToSelectAMazeSize() {
        System.out.println(ApplicationProperties.SUGGESTION_TO_SELECT_A_MAZE_SIZE_MESSAGE);
        System.out.println(ApplicationProperties.ENTER_COMMAND_TO_EXIT_MESSAGE);
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
        System.out.println(ApplicationProperties.FAREWELL_MESSAGE);
        scanner.close();
        System.exit(0);
    }
}
