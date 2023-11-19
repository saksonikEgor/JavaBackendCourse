package edu.project3;

import edu.project3.analyze.NginxLogStatisticsCollector;
import edu.project3.filter.LogFilter;
import edu.project3.filter.dateFilter.NginxLogsDateFilter;
import edu.project3.model.InputArguments;
import edu.project3.model.NginxLogRecord;
import edu.project3.model.NginxLogReport;
import edu.project3.util.ParserUtils;

public class NginxLogAnalyzer {
    private final InputArguments inputArguments;

    public NginxLogAnalyzer(String[] args) {
        inputArguments = ParserUtils.parseInput(args);
    }

    public void analyse() {
        LogFilter dateFilter = new NginxLogsDateFilter(inputArguments.from(), inputArguments.to());
        var statisticsCollector = new NginxLogStatisticsCollector();

        for (String path : inputArguments.paths()) {
            ParserUtils.getLogParserByPath(path)
                .parseLogs()
                .stream()
                .filter(dateFilter::accept)
                .forEach(statisticsCollector::takeStock);
        }

        NginxLogReport report = statisticsCollector.getReport(inputArguments.from(), inputArguments.to());




    }




//    private final String pathString;
//
//    public NginxLogAnalyzer(String pathString) {
//        this.pathString = pathString;
//    }
//
//    public List<Path> getFilesFromPath() throws FileNotFoundException {
//        if (this.pathString.contains("*")) {
//            return findAllPaths();
//        } else {
//            return getSinglePath();
//        }
//    }
//
//    private List<Path> getSinglePath() throws FileNotFoundException {
//        Path path = Paths.get(this.pathString);
//        if (path.toFile().exists()) {
//            return List.of(path);
//        } else {
//            throw new FileNotFoundException("File not exists!");
//        }
//    }
//
//    private List<Path> findAllPaths() {
//        List<Path> matchingPaths = new ArrayList<>();
//        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + this.pathString);
//
//        String startDir = getStartDir(this.pathString);
//
//        try {
//            Files.walkFileTree(Path.of(startDir), new SimpleFileVisitor<>() {
//                @Override
//                public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) {
//                    if (pathMatcher.matches(filePath)) {
//                        matchingPaths.add(filePath);
//                    }
//                    return FileVisitResult.CONTINUE;
//                }
//            });
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return matchingPaths;
//    }
//
//    private String getStartDir(String path) {
//        int firstAsteriskIndex = path.indexOf("*");
//        int lastSlashIndex = path.lastIndexOf("/", firstAsteriskIndex);
//        return path.substring(0, lastSlashIndex);
//    }
}
