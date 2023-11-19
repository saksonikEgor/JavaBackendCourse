package edu.project3.parser.fileParser;

import edu.project3.model.NginxLogRecord;
import edu.project3.parser.LogParser;
import edu.project3.util.ParserUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NginxFileLogParser implements LogParser {
    private final Path path;

    public NginxFileLogParser(Path path) throws FileNotFoundException {
        if (path.toFile().exists()) {
            this.path = path;
        } else {
            throw new FileNotFoundException();
        }
    }

    @Override
    public List<NginxLogRecord> parseLogs() {
        List<NginxLogRecord> records = new ArrayList<>();

        try (
            BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(String.valueOf(path)))
            )) {
            while (bufferedReader.ready()) {
                records.add(ParserUtils.parseLog(bufferedReader.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading a file by path: " + path, e);
        }

        return records;
    }
}
