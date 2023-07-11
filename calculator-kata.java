```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String expression = scanner.nextLine();
        scanner.close();

        String[] parts = expression.split(" ");
        if (parts.length != 3) {
            System.out.println("Неверный формат выражения!");
            return;
        }

        String num1 = parts[0];
        String operator = parts[1];
        String num2 = parts[2];

        int a = convertToArabic(num1);
        int b = convertToArabic(num2);
        if (a == -1 || b == -1) {
            System.out.println("Неверные числа!");
            return;
        }

        int result = calculate(a, operator, b);
        if (result == -1) {
            System.out.println("Неподдерживаемый оператор");
            return;
        }

        String resultString = convertToRoman(result);
        System.out.println("Результат: " + resultString);
    }

    public static int convertToArabic(String romanNumeral) {
        Map<Character, Integer> romanToArabicMap = new HashMap<>();
        romanToArabicMap.put('I', 1);
        romanToArabicMap.put('V', 5);
        romanToArabicMap.put('X', 10);
        romanToArabicMap.put('L', 50);
        romanToArabicMap.put('C', 100);
        romanToArabicMap.put('D', 500);
        romanToArabicMap.put('M', 1000);

        int result = 0;
        int prevValue = 0;
        for (int i = romanNumeral.length() - 1; i >= 0; i--) {
            int value = romanToArabicMap.get(romanNumeral.charAt(i));
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }

        if (!romanNumeral.equals(convertToRoman(result))) {
            return -1;
        }

        return result;
    }

    public static String convertToRoman(int number) {
        if (number < 1 || number > 3999) {
            return "";
        }

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[number / 1000] + hundreds[(number % 1000) / 100] +
                tens[(number % 100) / 10] + ones[number % 10];
    }

    public static int calculate(int a, String operator, int b) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return -1;
    }
}
```