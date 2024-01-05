package edu.hw9.task2.searching;

import edu.hw9.task2.utils.AbstractFilterUtils;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

public class PredicateSearchTask extends RecursiveTask<List<Path>> {
    private final String extension;
    private final int size;
    private final Path path;

    public PredicateSearchTask(Path path, String extension, int size) {
        this.path = path;
        this.extension = extension;
        this.size = size;
    }

    @Override
    public List<Path> compute() {
        try {
            if (!Files.isDirectory(path)) {
                return AbstractFilterUtils.extensionIs(extension)
                    .and(AbstractFilterUtils.sizeIs(size))
                    .accept(path)
                    ? List.of(path)
                    : Collections.emptyList();
            }
            return ForkJoinTask.invokeAll(makeSubtasks())
                .stream()
                .map(ForkJoinTask::join)
                .reduce((list1, list2) -> Stream.concat(list1.stream(), list2.stream()).toList())
                .orElse(Collections.emptyList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<PredicateSearchTask> makeSubtasks() throws IOException {
        List<PredicateSearchTask> subTasks = new ArrayList<>();

        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (Path subPath : files) {
                subTasks.add(new PredicateSearchTask(subPath, extension, size));
            }
        }

        return subTasks;
    }
}
