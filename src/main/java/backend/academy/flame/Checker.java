package backend.academy.flame;

import backend.academy.flame.image.ImageFormat;
import backend.academy.flame.transformations.Transformation;
import backend.academy.flame.transformations.VortexFlowerTransformation;
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

    // Метод для чтения коэффициентов
    public static double[] readCoefficients(Scanner scanner) {
        double[] coefficients = new double[6];
        log.info("Вы хотите ввести свои коэффициенты или оставить стандартные? (1-свои, 2-стандартные)");
        int n = readOneOrTwo(scanner);
        if (n == 1) {
            String[] prompts = {"Введите коэффициент a:", "Введите коэффициент b:", "Введите коэффициент c:",
                "Введите коэффициент d:", "Введите коэффициент e:", "Введите коэффициент f:"};
            for (int i = 0; i < coefficients.length; i++) {
                while (true) {
                    try {
                        log.info(prompts[i]);
                        coefficients[i] = scanner.nextDouble();
                        break;
                    } catch (Exception e) {
                        scanner.nextLine();
                        log.warn("Введите допустимое числовое значение.");
                    }
                }
            }
            scanner.nextLine();
        } else {
            for (int i = 0; i < coefficients.length; i++) {
                if (i == 0 || i == 4) {
                    coefficients[i] = 1;
                } else {
                    coefficients[i] = 0;
                }
            }
        }
        return coefficients;
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

    public static Transformation readVariationOfFlower(Scanner scanner) {
        log.info("Какой цветок вы хотите вывести? (1-Стандартный, 2-С маленькими лепестками, 3-С широкими лепестками)");
        int n = readOneOrTwoOrThree(scanner);
        if (n == 1) {
            return VortexFlowerTransformation.createDefault();
        } else if (n == 2) {
            return VortexFlowerTransformation.createFlowerWithSmallPetals();
        } else {
            return VortexFlowerTransformation.createFlowerWithWidePetals();
        }
    }
}
