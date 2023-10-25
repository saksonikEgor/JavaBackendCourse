package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalUtilTest {
    @Test
    @DisplayName("Сортировка по росту")
    void sortByHeight() {
        assertEquals(List.of(
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 15, 10, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 22, 10, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 25, 10, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 30, 10, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 40, 10, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 81, 10, false)
        ), AnimalUtils.sortByHeight(List.of(
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 15, 10, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 81, 10, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 30, 10, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 40, 10, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 22, 10, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 25, 10, false)
        )));

        assertThatThrownBy(() -> AnimalUtils.sortByHeight(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Выбрать к самых тяжелых")
    void getFirstKHeaviest() {
        assertEquals(List.of(
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 15, 130, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 81, 110, false)
        ), AnimalUtils.getFirstKHeaviest(List.of(
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 15, 10, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 22, 20, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 15, 130, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 81, 110, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 40, -10, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 81, 10, false)
        ), 2));

        assertThatThrownBy(() -> AnimalUtils.getFirstKHeaviest(null, 2))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);

        assertThatThrownBy(() -> AnimalUtils.getFirstKHeaviest(new ArrayList<>(), -2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(AnimalUtils.WRONG_K_MESSAGE);
    }

    @Test
    @DisplayName("Посчитать животных по видам")
    void animalsToDictionary() {
        assertThat(List.of(Map.of(
            Animal.Type.DOG, 2,
            Animal.Type.FISH, 2,
            Animal.Type.CAT, 1,
            Animal.Type.BIRD, 1
        ))).hasSameElementsAs(List.of(AnimalUtils.animalsToDictionary(List.of(
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 15, 10, false),
            new Animal("Pupy", Animal.Type.FISH, Animal.Sex.M, 12, 22, 20, false),
            new Animal("Pupy", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 81, 110, false),
            new Animal("Pupy", Animal.Type.CAT, Animal.Sex.M, 12, 40, -10, false),
            new Animal("Pupy", Animal.Type.BIRD, Animal.Sex.M, 12, 81, 10, false)
        ))));

        assertThatThrownBy(() -> AnimalUtils.animalsToDictionary(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Найти животное с самым длинным именем")
    void getLargestNamedAnimal() {
        assertEquals(
            new Animal("Ronaldo", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
            AnimalUtils.getLargestNamedAnimal(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.M, 12, 15, 10, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.M, 12, 22, 20, false),
                new Animal("Ronaldo", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.M, 12, 81, 110, false),
                new Animal("Puppy", Animal.Type.CAT, Animal.Sex.M, 12, 40, -10, false),
                new Animal("Adam", Animal.Type.BIRD, Animal.Sex.M, 12, 81, 10, false)
            ))
        );

        assertThatThrownBy(() -> AnimalUtils.getLargestNamedAnimal(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Найти наиболее популярный пол")
    void getTheMostNumerousSex() {
        assertEquals(
            Animal.Sex.F,
            AnimalUtils.getTheMostNumerousSex(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 12, 15, 10, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 12, 22, 20, false),
                new Animal("Ronaldo", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 110, false),
                new Animal("Puppy", Animal.Type.CAT, Animal.Sex.F, 12, 40, -10, false),
                new Animal("Adam", Animal.Type.BIRD, Animal.Sex.M, 12, 81, 10, false)
            ))
        );

        assertThatThrownBy(() -> AnimalUtils.getTheMostNumerousSex(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Найти самое тяжелое животное каждого вида")
    void getTheHeaviestAnimalOfEachTypes() {
        assertThat(List.of(Map.of(
            Animal.Type.DOG, new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 81, 110, false),
            Animal.Type.FISH, new Animal("Pupy", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
            Animal.Type.CAT, new Animal("Pupy", Animal.Type.CAT, Animal.Sex.M, 12, 40, -10, false),
            Animal.Type.BIRD, new Animal("Pupy", Animal.Type.BIRD, Animal.Sex.M, 12, 81, 10, false)
        ))).hasSameElementsAs(List.of(AnimalUtils.getTheHeaviestAnimalOfEachTypes(List.of(
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 15, 10, false),
            new Animal("Pupy", Animal.Type.FISH, Animal.Sex.M, 12, 22, 20, false),
            new Animal("Pupy", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 12, 81, 110, false),
            new Animal("Pupy", Animal.Type.CAT, Animal.Sex.M, 12, 40, -10, false),
            new Animal("Pupy", Animal.Type.BIRD, Animal.Sex.M, 12, 81, 10, false)
        ))));

        assertThatThrownBy(() -> AnimalUtils.getTheHeaviestAnimalOfEachTypes(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Найти к-е самое старое животное")
    void getKOldest() {
        assertEquals(
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 17, 81, 110, false),
            AnimalUtils.getKOldest(List.of(
                new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 13, 15, 10, false),
                new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 32, 22, 20, false),
                new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 2, 15, 130, false),
                new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 17, 81, 110, false),
                new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 9, 40, -10, false),
                new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 13, 81, 10, false)
            ), 2)
        );

        assertThatThrownBy(() -> AnimalUtils.getKOldest(null, 2))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);

        assertThatThrownBy(() -> AnimalUtils.getKOldest(new ArrayList<>(), -2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(AnimalUtils.WRONG_K_MESSAGE);
    }

    @Test
    @DisplayName("Найти самое тяжелое животное среди животных ниже k см")
    void getHeaviestAnimalBelowKSM() {
        assertEquals(
            new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 2, 15, 15, false),
            AnimalUtils.getHeaviestAnimalBelowKSM(List.of(
                new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 13, 10, 10, false),
                new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 32, 22, 20, false),
                new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 2, 15, 15, false),
                new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 17, 81, 110, false),
                new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 9, 40, -10, false),
                new Animal("Pupy", Animal.Type.DOG, Animal.Sex.M, 13, 81, 10, false)
            ), 20).get()
        );

        assertThatThrownBy(() -> AnimalUtils.getHeaviestAnimalBelowKSM(null, 2))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Посчитать сумму сап всех животных")
    void countPaws() {
        assertEquals(
            14,
            AnimalUtils.countPaws(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 12, 15, 10, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 12, 22, 20, false),
                new Animal("Ronaldo", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 110, false),
                new Animal("Puppy", Animal.Type.CAT, Animal.Sex.F, 12, 40, -10, false),
                new Animal("Adam", Animal.Type.BIRD, Animal.Sex.M, 12, 81, 10, false)
            ))
        );

        assertThatThrownBy(() -> AnimalUtils.countPaws(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Найти животных возраст у которых не совпадает с количеством лап")
    void getAnimalsWhoseAgeDoesNotMatchTheNumberOfPaws() {
        assertEquals(
            List.of(
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 20, false),
                new Animal("Ronaldo", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 110, false),
                new Animal("Adam", Animal.Type.BIRD, Animal.Sex.M, 1, 81, 10, false)
            ),
            AnimalUtils.getAnimalsWhoseAgeDoesNotMatchTheNumberOfPaws(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 10, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 20, false),
                new Animal("Ronaldo", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 110, false),
                new Animal("Puppy", Animal.Type.CAT, Animal.Sex.F, 4, 40, -10, false),
                new Animal("Adam", Animal.Type.BIRD, Animal.Sex.M, 1, 81, 10, false)
            ))
        );

        assertThatThrownBy(() -> AnimalUtils.getAnimalsWhoseAgeDoesNotMatchTheNumberOfPaws(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Найти животных, котрые выше 100см и которые кусаются")
    void getAnimalsThatCanBiteAndHigher100SM() {
        assertEquals(
            List.of(
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 811, 110, true),
                new Animal("Adam", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 10, true)
            ),
            AnimalUtils.getAnimalsThatCanBiteAndHigher100SM(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 10, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 20, false),
                new Animal("Ronaldo", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 811, 110, true),
                new Animal("Puppy", Animal.Type.CAT, Animal.Sex.F, 4, 40, -10, false),
                new Animal("Adam", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 10, true)
            ))
        );

        assertThatThrownBy(() -> AnimalUtils.getAnimalsThatCanBiteAndHigher100SM(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Подсчет животных, вес которых превышает их рост")
    void getCountOfAnimalsWhoseWeightExceedsTheirHeight() {
        assertEquals(
            3,
            AnimalUtils.getCountOfAnimalsWhoseWeightExceedsTheirHeight(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 10, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 20, false),
                new Animal("Ronaldo", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 110, true),
                new Animal("Puppy", Animal.Type.CAT, Animal.Sex.F, 4, 40, -10, false),
                new Animal("Adam", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 190, true)
            ))
        );

        assertThatThrownBy(() -> AnimalUtils.getCountOfAnimalsWhoseWeightExceedsTheirHeight(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Найти животных, имена которых состоят больше чем из 2 слов")
    void getAnimalsWhoseNamesConsistOfMoreThanTwoWords() {
        assertEquals(
            List.of(
                new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 40, -10, false),
                new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 190, true)
            ),
            AnimalUtils.getAnimalsWhoseNamesConsistOfMoreThanTwoWords(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 10, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 20, false),
                new Animal("Ronaldo White", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 110, true),
                new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 40, -10, false),
                new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 190, true)
            ))
        );

        assertThatThrownBy(() -> AnimalUtils.getAnimalsWhoseNamesConsistOfMoreThanTwoWords(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Проверка на наличие собаки ростом более k см")
    void isThereADogOnTheListTallerThanKSM() {
        assertTrue(
            AnimalUtils.isThereADogOnTheListTallerThanKSM(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 10, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 20, false),
                new Animal("Ronaldo White", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 110, true),
                new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 40, -10, false),
                new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 190, true)
            ), 50)
        );

        assertFalse(
            AnimalUtils.isThereADogOnTheListTallerThanKSM(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 10, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 20, false),
                new Animal("Ronaldo White", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 110, true),
                new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 40, -10, false),
                new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 190, true)
            ), 85)
        );

        assertThatThrownBy(() -> AnimalUtils.isThereADogOnTheListTallerThanKSM(null, 25))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Подсчет суммарного веса животных которым от l до k лет")
    void countTotalWeightOfAnimalsThatAreFromKToLYearsOld() {
        assertEquals(
            240,
            AnimalUtils.countTotalWeightOfAnimalsThatAreFromKToLYearsOld(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 10, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 20, false),
                new Animal("Ronaldo White", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 110, true),
                new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 40, -10, false),
                new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 190, true)
            ), 10, 20)
        );

        assertEquals(
            190,
            AnimalUtils.countTotalWeightOfAnimalsThatAreFromKToLYearsOld(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 10, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 20, false),
                new Animal("Ronaldo White", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 110, true),
                new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 40, -10, false),
                new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 190, true)
            ), 1, 1)
        );

        assertThatThrownBy(() -> AnimalUtils.countTotalWeightOfAnimalsThatAreFromKToLYearsOld(null, 25, 30))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);

        assertThatThrownBy(() -> AnimalUtils.countTotalWeightOfAnimalsThatAreFromKToLYearsOld(
            new ArrayList<>(),
            25,
            24
        ))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(AnimalUtils.WRONG_K_AND_L_MESSAGE);
    }

    @Test
    @DisplayName("Отсортировать животных по виду, затем по полу, затем по имени")
    void sortAnimalsByTypeSexAndName() {
        assertEquals(
            List.of(
                new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 40, 5, false),
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 1, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 4, true),
                new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 6, true),
                new Animal("Ronaldo White", Animal.Type.FISH, Animal.Sex.M, 12, 15, 3, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 2, false)
            ),
            AnimalUtils.sortAnimalsByTypeSexAndName(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 1, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 2, false),
                new Animal("Ronaldo White", Animal.Type.FISH, Animal.Sex.M, 12, 15, 3, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 4, true),
                new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 40, 5, false),
                new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 6, true)
            ))
        );

        assertThatThrownBy(() -> AnimalUtils.sortAnimalsByTypeSexAndName(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Проверка, что пауки кусаются чаще собак")
    void isSpidersBiteMoreOftenThanDogs() {
        assertTrue(
            AnimalUtils.isSpidersBiteMoreOftenThanDogs(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 10, false),
                new Animal("A", Animal.Type.SPIDER, Animal.Sex.F, 3, 22, 20, true),
                new Animal("Ronaldo White", Animal.Type.SPIDER, Animal.Sex.M, 12, 15, 130, true),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 110, true),
                new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 40, -10, false),
                new Animal("Zach Adam Dmitrievich", Animal.Type.SPIDER, Animal.Sex.M, 1, 181, 190, true)
            ))
        );

        assertFalse(
            AnimalUtils.isSpidersBiteMoreOftenThanDogs(List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 10, false),
                new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 20, false),
                new Animal("Ronaldo White", Animal.Type.FISH, Animal.Sex.M, 12, 15, 130, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 110, true),
                new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 40, -10, false),
                new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 190, true)
            ))
        );

        assertThatThrownBy(() -> AnimalUtils.isSpidersBiteMoreOftenThanDogs(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.ANIMALS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Нахождение самоя тяжелой рыбки в 2х или более списках")
    void getHeaviestFist() {
        assertEquals(
            new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 122, 12, false),
            AnimalUtils.getHeaviestFist(List.of(
                List.of(
                    new Animal("", Animal.Type.DOG, Animal.Sex.F, 14, 15, 1, false),
                    new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 232, 6, false),
                    new Animal("Ronaldo White", Animal.Type.FISH, Animal.Sex.M, 12, 215, 8, false),
                    new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 121, 81, 34, true)
                ), List.of(
                    new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 1, false),
                    new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 22, 2, false),
                    new Animal("Ronaldo White", Animal.Type.FISH, Animal.Sex.M, 312, 15, 3, false),
                    new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 281, 42, true),
                    new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 340, 5, false),
                    new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 62, true)
                ), List.of(
                    new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 1, false),
                    new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 122, 12, false),
                    new Animal("Ronaldo White", Animal.Type.FISH, Animal.Sex.M, 12, 15, 3, false),
                    new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 24, true),
                    new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 40, 53, false),
                    new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 6, true)
                )
            ))
        );

        assertNull(AnimalUtils.getHeaviestFist(List.of(
            List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 14, 15, 1, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 121, 81, 34, true)
            ), List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 1, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 281, 42, true),
                new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 340, 5, false),
                new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 62, true)
            ), List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 15, 1, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 12, 81, 24, true),
                new Animal("Puppy Pop Pip", Animal.Type.CAT, Animal.Sex.F, 4, 40, 53, false),
                new Animal("Zach Adam Dmitrievich", Animal.Type.BIRD, Animal.Sex.M, 1, 181, 6, true)
            )
        )));

        assertThatThrownBy(() -> AnimalUtils.getHeaviestFist(List.of(
            List.of(
                new Animal("", Animal.Type.DOG, Animal.Sex.F, 14, 15, 1, false),
                new Animal("Qweee", Animal.Type.DOG, Animal.Sex.F, 121, 81, 34, true)
            )
        )))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(AnimalUtils.WRONG_LIST_COUNT_MESSAGE);

        assertThatThrownBy(() -> AnimalUtils.getHeaviestFist(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(AnimalUtils.LIST_OF_ANIMAL_LISTS_IS_NULL_MESSAGE);
    }

    @Test
    @DisplayName("Получение информации об ошибках")
    void wrongAnimals() {
    }
}
