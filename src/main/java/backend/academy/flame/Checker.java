package backend.academy.flame;

import java.util.ArrayList;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("checkstyle:MagicNumber")
@Slf4j
public class Checker {

    private Checker() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }

    public static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                log.info(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value > 0) {
                    return value;
                } else {
                    log.warn("Значение должно быть положительным числом.");
                }
            } catch (Exception e) {
                log.warn("Значение должно быть числом.");
                scanner.nextLine();
            }
        }
    }

    public static int readOneOrTwo(Scanner scanner) {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Очистка после nextInt()
                if (value == 1 || value == 2) {
                    return value;
                } else {
                    log.warn("Значение должно быть: 1 или 2.");
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
        }
    }

    public static int[] getInputArrayFromConsole() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        while (true) {
            log.info("1-Circular, 2-Cross, 3-EyeFish, 4-Heart, 5-Linear, 6-Spiral, 7-Swirl, 8-Tangent, 9-Waves");
            log.info("Введите одну трансформацию или несколько через запятую (от 1 до 9):");
            String input = scanner.nextLine();

            String[] parts = input.split(",");
            numbers.clear();

            boolean validInput = true;

            for (String part : parts) {
                String trimmedPart = part.trim();
                try {
                    int num = Integer.parseInt(trimmedPart);
                    if (num >= 1 && num <= 9) {
                        numbers.add(num);
                    } else {
                        log.warn("Число {} вне допустимого диапазона (1-9).", num);
                        validInput = false;
                    }

                } catch (NumberFormatException e) {
                    log.warn("Некорректный ввод: {}", trimmedPart);
                    validInput = false;
                }
            }

            if (validInput && !numbers.isEmpty()) {
                break;
            } else {
                log.warn("Попробуйте ввести корректные данные.");
            }
        }

        int[] result = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            result[i] = numbers.get(i);
        }

        return result;
    }




}
