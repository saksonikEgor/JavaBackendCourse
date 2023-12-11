package edu.project3.model;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public record InputArguments(
    String[] paths,
    Optional<OffsetDateTime> from,
    Optional<OffsetDateTime> to,
    OutputFormat outputType
) {
    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InputArguments that = (InputArguments) o;
        return Arrays.equals(paths, that.paths) && Objects.equals(from, that.from)
            && Objects.equals(to, that.to) && outputType == that.outputType;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(from, to, outputType);
        result = 31 * result + Arrays.hashCode(paths);
        return result;
    }
}
