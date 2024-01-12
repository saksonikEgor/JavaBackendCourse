package edu.hw9.task2.searching;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountingSearchTask extends RecursiveTask<Integer> {
    private static final int FILE_COUNT_LOWER_BOUND = 1000;
    private static final List<Path> DIRECTORIES = Collections.synchronizedList(new ArrayList<>());
    private final Path path;

    public CountingSearchTask(Path path) {
        this.path = path;
    }

    @Override
    public Integer compute() {
        if (!Files.isDirectory(path)) {
            return 1;
        }

        int fileCount;
        try {
            fileCount = ForkJoinTask.invokeAll(makeSubtasks())
                .stream()
                .mapToInt(ForkJoinTask::join)
                .sum();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (fileCount >= FILE_COUNT_LOWER_BOUND) {
            DIRECTORIES.add(path);
        }

        return fileCount;
    }

    private List<CountingSearchTask> makeSubtasks() throws IOException {
        List<CountingSearchTask> subTasks = new ArrayList<>();

        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (Path subPath : files) {
                subTasks.add(new CountingSearchTask(subPath));
            }
        }

        return subTasks;
    }

    public List<Path> getDirectories() {
        return DIRECTORIES;
    }
}
