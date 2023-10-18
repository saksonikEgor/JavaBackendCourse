package edu.project2.generation;

import edu.project2.model.Cell;
import java.util.List;

public interface Generator {
    List<Cell> generate(int height, int width);
}
