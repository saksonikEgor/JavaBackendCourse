package edu.project3.writer;

import edu.project3.model.NginxLogReport;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class LogWriter {
    protected final NginxLogReport report;
    protected final String path;


    protected LogWriter(NginxLogReport report, String path) {
        this.report = report;
        this.path = path;
    }

    public void write() {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(path))) {
            writer.write(getGeneralInfo());
            writer.write(getResources());
            writer.write(getCodes());

            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to the file", e);
        }
    }

    public abstract String getGeneralInfo();

    public abstract String getResources();

    public abstract String getCodes();
}
