package edu.hw4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AnimalUtils {
    public static final String ANIMALS_IS_NULL_MESSAGE = "List of animals cant be null";
    public static final String LIST_OF_ANIMAL_LISTS_IS_NULL_MESSAGE = "List of animal lists cant be null";
    public static final String WRONG_K_MESSAGE = "Wrong k value";
    public static final String WRONG_K_AND_L_MESSAGE = "Wrong k and l value";
    public static final String WRONG_LIST_COUNT_MESSAGE = "Wrong list count";
    private static final int LATIN_LOWERCASE_ASCII_LOWER_BOUND = 97;
    private static final int LATIN_LOWERCASE_ASCII_UPPER_BOUND = 122;
    private static final int LATIN_UPPERCASE_ASCII_LOWER_BOUND = 65;
    private static final int LATIN_UPPERCASE_ASCII_UPPER_BOUND = 90;

    private AnimalUtils() {
    }

    @NotNull
    public static List<Animal> sortByHeight(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).collect(Collectors.toList());
    }

    @NotNull
    public static List<Animal> getFirstKHeaviest(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        if (k < 0) {
            throw new IllegalArgumentException(WRONG_K_MESSAGE);
        }

        return animals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed()).toList()
            .stream().limit(Math.min(k, animals.size())).collect(Collectors.toList());
    }

    @NotNull
    public static Map<Animal.Type, Integer> animalsToDictionary(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        Map<Animal.Type, Integer> dict = new HashMap<>();
        animals.forEach(a -> dict.put(a.type(), dict.getOrDefault(a.type(), 0) + 1));
        return dict;
    }

    @Nullable
    public static Animal getLargestNamedAnimal(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals.stream().max(Comparator.comparingInt(a -> a.name().length())).orElse(null);
    }

    @NotNull
    public static Animal.Sex getTheMostNumerousSex(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return 2 * animals.stream().filter(a -> a.sex() == Animal.Sex.M).count() >= animals.size()
            ? Animal.Sex.M
            : Animal.Sex.F;
    }

    @NotNull
    public static Map<Animal.Type, Animal> getTheHeaviestAnimalOfEachTypes(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        Map<Animal.Type, Animal> types = new HashMap<>();

        animals.forEach(a -> {
            if (!types.containsKey(a.type()) || a.weight() > types.get(a.type()).weight()) {
                types.put(a.type(), a);
            }
        });

        return types;
    }

    @NotNull
    public static Animal getKOldest(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        if (k <= 0 || k > animals.size()) {
            throw new IllegalArgumentException(WRONG_K_MESSAGE);
        }

        return animals.stream().toList()
            .stream().sorted(Comparator.comparingInt(Animal::weight)).toList()
            .get(animals.size() - k);
    }

    @NotNull
    public static Optional<Animal> getHeaviestAnimalBelowKSM(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals.stream().filter(a -> a.height() < k).max(Comparator.comparingInt(Animal::weight));
    }

    public static int countPaws(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals.stream().mapToInt(Animal::paws).sum();
    }

    @NotNull
    public static List<Animal> getAnimalsWhoseAgeDoesNotMatchTheNumberOfPaws(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals.stream().filter(a -> a.age() != a.paws()).collect(Collectors.toList());
    }

    @NotNull
    public static List<Animal> getAnimalsThatCanBiteAndHigher100SM(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals.stream().filter(a -> a.height() > 100 && a.bites()).collect(Collectors.toList());
    }

    public static int getCountOfAnimalsWhoseWeightExceedsTheirHeight(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return Math.toIntExact(animals.stream().filter(a -> a.weight() > a.height()).count());
    }

    @NotNull
    public static List<Animal> getAnimalsWhoseNamesConsistOfMoreThanTwoWords(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }
        final int WORD_COUNT = 2;

        return animals.stream().filter(a -> a.name().split(" ").length > WORD_COUNT).collect(Collectors.toList());
    }

    public static boolean isThereADogOnTheListTallerThanKSM(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals.stream().filter(a -> a.type() == Animal.Type.DOG).anyMatch(a -> a.height() > k);
    }

    public static int countTotalWeightOfAnimalsThatAreFromKToLYearsOld(List<Animal> animals, int k, int l) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        if (k > l) {
            throw new IllegalArgumentException(WRONG_K_AND_L_MESSAGE);
        }

        return animals.stream().filter(a -> a.age() >= k && a.age() <= l).mapToInt(Animal::weight).sum();
    }

    @NotNull
    public static List<Animal> sortAnimalsByTypeSexAndName(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals.stream().sorted(Comparator
            .comparing(Animal::type)
            .thenComparing(Animal::sex)
            .thenComparing(Animal::name)).collect(Collectors.toList());
    }

    public static boolean isSpidersBiteMoreOftenThanDogs(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals.stream().mapToInt(a -> switch (a.type()) {
            case SPIDER -> 1;
            case DOG -> -1;
            default -> 0;
        }).sum() > 0;
    }

    @Nullable
    public static Animal getHeaviestFist(List<List<Animal>> lists) {
        if (lists == null) {
            throw new NullPointerException(LIST_OF_ANIMAL_LISTS_IS_NULL_MESSAGE);
        }
        if (lists.size() < 2) {
            throw new IllegalArgumentException(WRONG_LIST_COUNT_MESSAGE);
        }

        for (List<Animal> animals : lists) {
            if (animals == null) {
                throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
            }
        }

        var animalsWithHeaviestFist = lists.stream().max((animals1, animals2) -> {
            int weight1 = Integer.MIN_VALUE;
            int weight2 = Integer.MIN_VALUE;

            var fish1 = animals1.stream()
                .filter(a -> a.type() == Animal.Type.FISH)
                .max(Comparator.comparingInt(Animal::weight));
            var fish2 = animals2.stream()
                .filter(a -> a.type() == Animal.Type.FISH)
                .max(Comparator.comparingInt(Animal::weight));

            if (fish1.isPresent()) {
                weight1 = fish1.get().weight();
            }
            if (fish2.isPresent()) {
                weight2 = fish2.get().weight();
            }

            return Integer.compare(weight1, weight2);
        });

        return animalsWithHeaviestFist.flatMap(animals -> animals.stream()
            .filter(a -> a.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))).orElse(null);
    }

    @NotNull
    public static Map<Animal, Set<ValidationError>> wrongAnimals(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        Map<Animal, Set<ValidationError>> animalToErrors = new HashMap<>();
        animals.forEach(a -> animalToErrors.put(a, ValidationError.validate(a)));

        return animalToErrors;
    }

    @NotNull
    public static Map<String, String> wrongAnimalsInString(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        Map<String, String> animalToErrors = new HashMap<>();
        animals.forEach(a -> {
            var entry = ValidationError.concatenateNameAndErrors(a);
            animalToErrors.put(entry.getKey(), entry.getValue());
        });

        return animalToErrors;
    }

    public enum ValidationError {
        ANIMAL_IS_NULL,
        WRONG_NAME,
        WRONG_TYPE,
        WRONG_SEX,
        WRONG_HEIGHT,
        WRONG_WEIGHT;

        @NotNull
        public static Set<ValidationError> validate(Animal animal) {
            Set<ValidationError> errors = new HashSet<>();
            if (animal == null) {
                return Set.of(ANIMAL_IS_NULL);
            }

            if (validateName(animal.name())) {
                errors.add(WRONG_NAME);
            }
            if (validateType(animal.type())) {
                errors.add(WRONG_TYPE);
            }
            if (validateSex(animal.sex())) {
                errors.add(WRONG_SEX);
            }
            if (validateHeight(animal.height())) {
                errors.add(WRONG_HEIGHT);
            }
            if (validateWeight(animal.weight())) {
                errors.add(WRONG_WEIGHT);
            }
            return errors;
        }

        private static boolean validateName(String name) {
            if (name == null || name.isEmpty()) {
                return false;
            }

            String[] words = name.split(" ");
            if (words.length > 2) {
                return false;
            }

            return Arrays.stream(words)
                .allMatch(str -> str.chars().allMatch(ValidationError::characterIsLatin));
        }

        private static boolean characterIsLatin(int ascii) {
            return LATIN_LOWERCASE_ASCII_LOWER_BOUND <= ascii && ascii <= LATIN_LOWERCASE_ASCII_UPPER_BOUND
                || LATIN_UPPERCASE_ASCII_LOWER_BOUND <= ascii && ascii <= LATIN_UPPERCASE_ASCII_UPPER_BOUND;
        }

        private static boolean validateType(Animal.Type type) {
            return type != null;
        }

        private static boolean validateSex(Animal.Sex sex) {
            return sex != null;
        }

        private static boolean validateHeight(int height) {
            return height > 0;
        }

        private static boolean validateWeight(int weight) {
            return weight > 0;
        }

        @NotNull
        public static Map.Entry<String, String> concatenateNameAndErrors(Animal animal) {
            Set<ValidationError> errors = validate(animal);

            return Map.entry(
                errors.contains(ANIMAL_IS_NULL) ? null : animal.name(),
                errorsToString(errors)
            );
        }

        @NotNull
        private static String errorsToString(Set<ValidationError> errors) {
            return Arrays.toString(errors.toArray());
        }
    }
}


