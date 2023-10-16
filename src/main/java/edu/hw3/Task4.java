package edu.hw3;

public class Task4 {
    public static final int LOWER_BOUND = 1;
    public static final int UPPER_BOUND = 3999;
    public static final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "Number \"num\" must be in range "
        + "[\"LOWER_BOUND\", \"UPPER_BOUND\"]";
    private static final RomanLetter[] LETTERS = {
        new RomanLetter(1000, "M"), new RomanLetter(900, "CM"),
        new RomanLetter(500, "D"), new RomanLetter(400, "CD"),
        new RomanLetter(100, "C"), new RomanLetter(90, "XC"),
        new RomanLetter(50, "L"), new RomanLetter(40, "XL"),
        new RomanLetter(10, "X"), new RomanLetter(9, "IX"),
        new RomanLetter(5, "V"), new RomanLetter(4, "IV"),
        new RomanLetter(1, "I")
    };

    private Task4() {
    }

    public static String convertToRoman(int num) {
        if (num < LOWER_BOUND || num > UPPER_BOUND) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
        }
        int number = num;
        StringBuilder sb = new StringBuilder();

        for (RomanLetter letter : LETTERS) {
            while (number >= letter.arabicNumber) {
                number -= letter.arabicNumber;
                sb.append(letter.romanLetter);
            }
        }
        return sb.toString();
    }

    private record RomanLetter(int arabicNumber, String romanLetter) {
    }
}
