package backend.academy.flame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
@SuppressWarnings("checkstyle:MagicNumber")
public class InputValidator {

    public int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            log.info(prompt);
            String input = scanner.nextLine().trim();
            if (isPositiveInteger(input)) {
                return Integer.parseInt(input);
            } else {
                log.warn("Введите положительное целое число.");
            }
        }
    }

    public int readOneOrTwo(Scanner scanner) {
        while (true) {
            int value = readPositiveInt(scanner, "Введите 1 или 2:");
            if (value == 1 || value == 2) {
                return value;
            } else {
                log.warn("Значение должно быть 1 или 2.");
            }
        }
    }


    public int[] getInputArrayFromConsole(Scanner scanner) {
        while (true) {
            log.info("1-Circular, 2-Cross, 3-EyeFish, 4-Heart, 5-Spiral, 6-Swirl, 7-Tangent, 8-Waves");
            log.info("Введите одну или несколько трансформаций через запятую (от 1 до 9):");
            String input = scanner.nextLine();

            List<Integer> numbers = parseTransformations(input);
            if (!numbers.isEmpty()) {
                return numbers.stream().mapToInt(Integer::intValue).toArray();
            } else {
                log.warn("Некорректный ввод. Попробуйте снова.");
            }
        }
    }

    private boolean isPositiveInteger(String input) {
        try {
            int value = Integer.parseInt(input);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<Integer> parseTransformations(String input) {
        String[] parts = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        boolean valid = true;

        for (String part : parts) {
            String trimmedPart = part.trim();
            try {
                int num = Integer.parseInt(trimmedPart);
                if (num >= 1 && num <= 9) {
                    numbers.add(num);
                } else {
                    log.warn("Число {} вне допустимого диапазона (1-9).", num);
                    valid = false;
                }
            } catch (NumberFormatException e) {
                log.warn("Некорректный ввод: {}", trimmedPart);
                valid = false;
            }
        }

        return valid ? numbers : new ArrayList<>();
    }
}
