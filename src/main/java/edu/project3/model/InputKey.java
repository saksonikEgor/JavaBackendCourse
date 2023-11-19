package edu.project3.model;

import java.util.Set;

public enum InputKey {
    Path,
    From,
    To,
    Format;

    public static Set<String> getSetOfKeysName() {
        return Set.of("--path", "--from", "--to", "--format");
    }

    @Override
    public String toString() {
        return switch (this) {
            case Path -> "--path";
            case From -> "--from";
            case To -> "--to";
            case Format -> "--format";
        };
    }
}
