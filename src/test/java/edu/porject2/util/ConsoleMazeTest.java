package edu.porject2.util;

import edu.project2.util.ConsoleMaze;
import java.util.Random;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleMazeTest {
    @Test
    @DisplayName("Выход сразу после запуска")
    void ExitImmediatelyAfterLaunch() throws Exception {
        int status = catchSystemExit(() ->
            withTextFromSystemIn("0")
                .execute(() -> assertEquals("""
                    === Choose a maze generation algorithm ===
                    1. Kruskal
                    2. DFS
                    3. BFS
                    0. Exit
                    Bye!""", tapSystemOutNormalized(new ConsoleMaze(new Scanner(System.in), new Random(5))::run)))
        );

        assertEquals(0, status);
    }

}
