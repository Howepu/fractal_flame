package backend.academy.flame;

import backend.academy.flame.image.ImageFormat;
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

    public static String readImageFormat(Scanner scanner) {
        while (true) {
            log.info("Введите формат изображения (например, PNG):");
            String format = scanner.nextLine().trim().toUpperCase();
            try {
                ImageFormat.valueOf(format);
                return format;
            } catch (IllegalArgumentException e) {
                log.warn("Некорректный формат. Допустимые форматы: PNG, JPEG, BMP.");
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

    public static int readOneOrTwoOrThree(Scanner scanner) {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Очистка после nextInt()
                if (value == 1 || value == 2 || value == 3) {
                    return value;
                } else {
                    log.warn("Значение должно быть: 1, 2 или 3.");
                }
            } catch (Exception e) {
                log.warn("Значение должно быть числовым");
                scanner.nextLine();
            }
        }
    }

}
