package edu.hw4.utils;

import edu.hw4.model.Animal;
import edu.hw4.model.Sex;
import edu.hw4.model.Type;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AnimalUtils {
    public static final String ANIMALS_IS_NULL_MESSAGE = "List of animals cant be null";
    public static final String LIST_OF_ANIMAL_LISTS_IS_NULL_MESSAGE = "List of animal lists cant be null";
    public static final String WRONG_K_MESSAGE = "Wrong k value";
    public static final String WRONG_K_AND_L_MESSAGE = "Wrong k and l value";
    public static final String WRONG_LIST_COUNT_MESSAGE = "Wrong list count";
    public static final String NAME_OF_NULL_ANIMAL = "NULL";

    private AnimalUtils() {
    }

    @NotNull
    public static List<Animal> sortByHeight(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals
            .stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    @NotNull
    public static List<Animal> getFirstKHeaviest(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        if (k < 0) {
            throw new IllegalArgumentException(WRONG_K_MESSAGE);
        }

        return animals
            .stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .toList()
            .stream()
            .limit(Math.min(k, animals.size()))
            .toList();
    }

    @NotNull
    public static Map<Type, Integer> animalsToDictionary(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        Map<Type, Integer> dict = new HashMap<>();
        animals.forEach(a -> dict.put(a.type(), dict.getOrDefault(a.type(), 0) + 1));
        return dict;
    }

    @Nullable
    public static Animal getLargestNamedAnimal(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals
            .stream()
            .max(Comparator.comparingInt(a -> a.name().length()))
            .orElse(null);
    }

    @NotNull
    public static Sex getTheMostNumerousSex(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return 2 * animals
            .stream()
            .filter(a -> a.sex() == Sex.M)
            .count() >= animals.size()
            ? Sex.M
            : Sex.F;
    }

    @NotNull
    public static Map<Type, Animal> getTheHeaviestAnimalOfEachTypes(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        Map<Type, Animal> types = new HashMap<>();

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

        return animals
            .stream()
            .toList()
            .stream()
            .sorted(Comparator.comparingInt(Animal::weight))
            .toList()
            .get(animals.size() - k);
    }

    @NotNull
    public static Optional<Animal> getHeaviestAnimalBelowKSM(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals
            .stream()
            .filter(a -> a.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static int countPaws(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals
            .stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    @NotNull
    public static List<Animal> getAnimalsWhoseAgeDoesNotMatchTheNumberOfPaws(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals
            .stream()
            .filter(a -> a.age() != a.paws())
            .toList();
    }

    @NotNull
    public static List<Animal> getAnimalsThatCanBiteAndHigher100SM(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }
        final int HEIGHT = 100;

        return animals
            .stream()
            .filter(a -> a.height() > HEIGHT && a.bites())
            .toList();
    }

    public static int getCountOfAnimalsWhoseWeightExceedsTheirHeight(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return Math.toIntExact(
            animals
                .stream()
                .filter(a -> a.weight() > a.height())
                .count()
        );
    }

    @NotNull
    public static List<Animal> getAnimalsWhoseNamesConsistOfMoreThanTwoWords(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }
        final int WORD_COUNT = 2;

        return animals
            .stream()
            .filter(a -> a.name().split(" ").length > WORD_COUNT)
            .toList();
    }

    public static boolean isThereADogOnTheListTallerThanKSM(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals
            .stream()
            .filter(a -> a.type() == Type.DOG)
            .anyMatch(a -> a.height() > k);
    }

    public static int countTotalWeightOfAnimalsThatAreFromKToLYearsOld(List<Animal> animals, int k, int l) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        if (k > l) {
            throw new IllegalArgumentException(WRONG_K_AND_L_MESSAGE);
        }

        return animals
            .stream()
            .filter(a -> a.age() >= k && a.age() <= l)
            .mapToInt(Animal::weight)
            .sum();
    }

    @NotNull
    public static List<Animal> sortAnimalsByTypeSexAndName(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals
            .stream()
            .sorted(
                Comparator
                    .comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name)
            )
            .toList();
    }

    public static boolean isSpidersBiteMoreOftenThanDogs(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        return animals
            .stream()
            .mapToInt(
                a -> switch (a.type()) {
                    case SPIDER -> 1;
                    case DOG -> -1;
                    default -> 0;
                }
            )
            .sum() > 0;
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
                .filter(a -> a.type() == Type.FISH)
                .max(Comparator.comparingInt(Animal::weight));
            var fish2 = animals2.stream()
                .filter(a -> a.type() == Type.FISH)
                .max(Comparator.comparingInt(Animal::weight));

            if (fish1.isPresent()) {
                weight1 = fish1.get().weight();
            }
            if (fish2.isPresent()) {
                weight2 = fish2.get().weight();
            }

            return Integer.compare(weight1, weight2);
        });

        return animalsWithHeaviestFist
            .flatMap(
                animals -> animals.stream()
                    .filter(a -> a.type() == Type.FISH)
                    .max(Comparator.comparingInt(Animal::weight))
            )
            .orElse(null);
    }

    @NotNull
    public static Map<String, Set<ValidationError>> wrongAnimals(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        Map<String, Set<ValidationError>> animalToErrors = new HashMap<>();
        animals.forEach(a -> {
            var entry = ValidationError.validate(a);
            animalToErrors.put(entry.getKey(), entry.getValue());
        });

        return animalToErrors;
    }

    @NotNull
    public static Map<String, String> wrongAnimalsInString(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(ANIMALS_IS_NULL_MESSAGE);
        }

        Map<String, String> animalToErrors = new HashMap<>();
        animals.forEach(
            a -> {
                var entry = ValidationError.concatenateNameAndErrors(a);
                animalToErrors.put(entry.getKey(), entry.getValue());
            }
        );

        return animalToErrors;
    }
}


