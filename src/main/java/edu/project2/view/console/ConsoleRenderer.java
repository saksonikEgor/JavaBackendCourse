package edu.project2.view.console;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.view.Renderer;
import java.util.List;

public class ConsoleRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        return mazeToString(maze, false);
    }

    @Override
    public String render(Maze maze, List<Cell> path) {
        maze.putCells(path);
        return mazeToString(maze, true);
    }

    private String mazeToString(Maze maze, boolean showEscape) {
        StringBuilder sb = new StringBuilder();

        for (Cell[] row : maze.getGrid()) {
            for (Cell cell : row) {
                if (cell.isWall()) {
                    sb.append("██");
                } else if (showEscape && cell.isEscape()) {
                    sb.append("XX");
                } else {
                    sb.append("  ");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
