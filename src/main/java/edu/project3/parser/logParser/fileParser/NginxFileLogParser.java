package edu.project3.parser.logParser.fileParser;

import edu.project3.model.NginxLogRecord;
import edu.project3.parser.logParser.LogParser;
import edu.project3.util.ParserUtils;
import java.io.BufferedReader;
import java.io.File;
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
            InputStream inputStream = new FileInputStream(String.valueOf(path));
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            while (bufferedReader.ready()) {
                records.add(ParserUtils.parseLog(bufferedReader.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading a file by path: " + path, e);
        }

        return records;
    }
}
