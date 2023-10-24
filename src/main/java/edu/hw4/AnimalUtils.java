package edu.hw4;

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




}


