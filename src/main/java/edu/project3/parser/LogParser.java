package edu.project3.parser;

import edu.project3.model.NginxLogRecord;
import java.util.List;

public interface LogParser {
    List<NginxLogRecord> parseLogs();
}
