package edu.project3.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationProperties {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String DATE_PATTERN = "dd/MMM/yyyy:HH:mm:ss Z";
    public static final String REQUEST_PATTERN = ".+ (/.+)+ HTTP/[^\"]+";

}
