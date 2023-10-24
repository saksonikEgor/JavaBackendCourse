package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnimalUtils {
    private AnimalUtils() {
    }

    public static void sortByHeight(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException();
        }

        animals.sort(Comparator.comparingInt(Animal::height));
    }

    public static List<Animal> getFirstKHeaviest(List<Animal> animals, int k) {
        if (k < 0) {
            throw new IllegalArgumentException();
        }

        animals.sort(Comparator.comparingInt(Animal::weight).reversed());
        return animals.stream().limit(Math.min(k, animals.size())).collect(Collectors.toList());
    }

    public static Map<Animal.Type, Integer> animalsToDictionary(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException();
        }

        Map<Animal.Type, Integer> dict = new HashMap<>();
        animals.forEach(a -> dict.put(a.type(), dict.getOrDefault(a.type(), 0) + 1));
        return dict;
    }

    public static Animal getLargestNamedAnimal(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException();
        }

        return animals.stream().max(Comparator.comparingInt(a -> a.name().length())).orElse(null);
    }

    public static Animal.Sex getTheMostNumerousSex(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException();
        }

        return 2 * animals.stream().filter(a -> a.sex() == Animal.Sex.M).count() >= animals.size()
            ? Animal.Sex.M
            : Animal.Sex.F;
    }

    public static Map<Animal.Type, Animal> getTheHeaviestAnimalOfEachTypes(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException();
        }

        Map<Animal.Type, Animal> types = new HashMap<>();

        animals.forEach(a -> {
            if (!types.containsKey(a.type()) || a.weight() > types.get(a.type()).weight()) {
                types.put(a.type(), a);
            }
        });

        return types;
    }

    public static Animal getKOldest(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NullPointerException();
        }

        if (k < 0 || k > animals.size()) {
            throw new IllegalArgumentException();
        }

        return animals.stream().toList().stream().sorted().toList().get(animals.size() - k);
    }

    public static Optional<Animal> getHeaviestAnimalBelowKSM(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NullPointerException();
        }

        return animals.stream().filter(a -> a.height() < k).max(Comparator.comparingInt(Animal::weight));
    }

    public static int countPaws(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException();
        }

        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> getAnimalsWhoseAgeDoesNotMatchTheNumberOfPaws(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException();
        }

        return animals.stream().filter(a -> a.age() != a.paws()).collect(Collectors.toList());
    }

    public static List<Animal> getAnimalsThatCanBiteAndHigher100SM(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException();
        }

        return animals.stream().filter(a -> a.height() > 100 && a.bites()).collect(Collectors.toList());
    }

    public static int getCountOfAnimalsWhoseWeightExceedsTheirHeight(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException();
        }

        return Math.toIntExact(animals.stream().filter(a -> a.weight() > a.height()).count());
    }

    public static List<Animal> getAnimalsWhoseNamesConsistOfMoreThanTwoWords(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException();
        }
        final int WORD_COUNT = 2;

        return animals.stream().filter(a -> a.name().split(" ").length > WORD_COUNT).collect(Collectors.toList());
    }

    public static boolean isThereADogOnTheListTallerThanKSM(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NullPointerException();
        }

        return animals.stream().anyMatch(a -> a.height() > k);
    }

    public static int countTotalWeightOfAnimalsThatAreFromKToLYearsOld(List<Animal> animals, int k, int l) {
        if (animals == null) {
            throw new NullPointerException();
        }

        if (k > l) {
            throw new IllegalArgumentException();
        }

        return animals.stream().filter(a -> a.age() >= k && a.age() <= l).mapToInt(Animal::weight).sum();
    }

    public static List<Animal> sortAnimalsByTypeSexAndName(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException();
        }

        List<Animal> result = new ArrayList<>(animals);
        result.sort(Comparator
            .comparing(Animal::type)
            .thenComparing(Animal::sex)
            .thenComparing(Animal::name)
        );

        return result;
    }

    public static boolean isSpidersBiteMoreOftenThanDogs(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException();
        }

        return animals.stream().mapToInt(a -> switch (a.type()) {
            case SPIDER -> 1;
            case DOG -> -1;
            default -> 0;
        }).sum() > 0;
    }

//    public static Animal getHeaviestFist(List<List<Animal>> lists) {
//        if (lists == null || lists.size() < 2) {
//            throw new NullPointerException();
//        }
//
//        for (List<Animal> animals : lists) {
//            if (animals == null) {
//                throw new NullPointerException();
//            }
//        }
//
//        return lists.stream().max((animals1, animals2) -> {
//            int weight1 = Integer.MIN_VALUE;
//            int weight2 = Integer.MIN_VALUE;
//
//            var fish1 = animals1.stream().max(Comparator.comparingInt(Animal::weight));
//            var fish2 = animals2.stream().max(Comparator.comparingInt(Animal::weight));
//
//            if (fish1.isPresent()) {
//                weight1 = fish1.get().weight();
//            }
//            if (fish2.isPresent()) {
//                weight2 = fish2.get().weight();
//            }
//
//            return Integer.compare(weight1, weight2);
//        }).get();
//    }

}


