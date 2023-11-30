package edu.project2.view;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Cell> path);
}
