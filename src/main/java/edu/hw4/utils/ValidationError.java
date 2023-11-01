package edu.hw4.utils;

import edu.hw4.model.Animal;
import edu.hw4.model.Sex;
import edu.hw4.model.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public enum ValidationError {
    ANIMAL_IS_NULL,
    WRONG_NAME,
    WRONG_TYPE,
    WRONG_SEX,
    WRONG_AGE,
    WRONG_HEIGHT,
    WRONG_WEIGHT;

    @NotNull
    public static Map.Entry<String, Set<ValidationError>> validate(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (animal == null) {
            return Map.entry(AnimalUtils.NAME_OF_NULL_ANIMAL, Set.of(ANIMAL_IS_NULL));
        }

        if (!nameIsValid(animal.name())) {
            errors.add(WRONG_NAME);
        }
        if (!typeIsValid(animal.type())) {
            errors.add(WRONG_TYPE);
        }
        if (!sexIsValid(animal.sex())) {
            errors.add(WRONG_SEX);
        }
        if (!ageIsValid(animal.age())) {
            errors.add(WRONG_AGE);
        }
        if (!heightIsValid(animal.height())) {
            errors.add(WRONG_HEIGHT);
        }
        if (!weightIsValid(animal.weight())) {
            errors.add(WRONG_WEIGHT);
        }

        return Map.entry(
            animal.name() == null ? AnimalUtils.NAME_OF_NULL_ANIMAL : animal.name(),
            errors
        );
    }

    private static boolean nameIsValid(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }

        String[] words = name.split(" ");
        if (words.length > 2) {
            return false;
        }

        return Arrays
            .stream(words)
            .allMatch(str -> str.chars().allMatch(ValidationError::characterIsLatin));
    }

    private static boolean characterIsLatin(int ascii) {
        final int LATIN_LOWERCASE_ASCII_LOWER_BOUND = 97;
        final int LATIN_LOWERCASE_ASCII_UPPER_BOUND = 122;
        final int LATIN_UPPERCASE_ASCII_LOWER_BOUND = 65;
        final int LATIN_UPPERCASE_ASCII_UPPER_BOUND = 90;

        return LATIN_LOWERCASE_ASCII_LOWER_BOUND <= ascii
            && ascii <= LATIN_LOWERCASE_ASCII_UPPER_BOUND
            || LATIN_UPPERCASE_ASCII_LOWER_BOUND <= ascii
            && ascii <= LATIN_UPPERCASE_ASCII_UPPER_BOUND;
    }

    private static boolean typeIsValid(Type type) {
        return type != null;
    }

    private static boolean sexIsValid(Sex sex) {
        return sex != null;
    }

    private static boolean ageIsValid(int age) {
        final int AGE_UPPER_BOUND = 100;

        return age > 0 && age < AGE_UPPER_BOUND;
    }

    private static boolean heightIsValid(int height) {
        return height > 0;
    }

    private static boolean weightIsValid(int weight) {
        return weight > 0;
    }

    @NotNull
    public static Map.Entry<String, String> concatenateNameAndErrors(Animal animal) {
        Map.Entry<String, Set<ValidationError>> errors = validate(animal);

        return Map.entry(
            errors.getKey(),
            errorsToString(errors.getValue())
        );
    }

    @NotNull
    private static String errorsToString(Set<ValidationError> errors) {
        return Arrays.toString(errors.toArray());
    }
}
