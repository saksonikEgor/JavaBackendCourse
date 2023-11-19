package edu.project3.model;

import java.time.OffsetDateTime;
import java.util.Optional;

public record InputArguments(
    String[] paths,
    Optional<OffsetDateTime> from,
    Optional<OffsetDateTime> to,
    OutputFormat outputType
) {
}
