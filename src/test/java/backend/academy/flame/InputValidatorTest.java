package backend.academy.flame;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

class InputValidatorTest {

    @Test
    void testReadPositiveIntValidInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("5");

        int result = InputValidator.readPositiveInt(scanner, "Введите положительное число:");
        assertEquals(5, result);
    }

    @Test
    void testReadPositiveIntNegativeInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("-1", "5");

        int result = InputValidator.readPositiveInt(scanner, "Введите положительное число:");
        assertEquals(5, result);
    }

    @Test
    void testReadPositiveIntInvalidInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("abc", "5");

        int result = InputValidator.readPositiveInt(scanner, "Введите положительное число:");
        assertEquals(5, result);
    }

    @Test
    void testReadOneOrTwoValidInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("1");

        int result = InputValidator.readOneOrTwo(scanner);
        assertEquals(1, result);
    }

    @Test
    void testReadOneOrTwoInvalidInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("3", "1");

        int result = InputValidator.readOneOrTwo(scanner);
        assertEquals(1, result);
    }

    @Test
    void testGetInputArrayFromConsoleValidInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("1, 2, 3");

        int[] result = InputValidator.getInputArrayFromConsole(scanner);
        assertArrayEquals(new int[] {1, 2, 3}, result);
    }

    @Test
    void testGetInputArrayFromConsoleInvalidInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("1, x, 3", "2, 4, 5");

        int[] result = InputValidator.getInputArrayFromConsole(scanner);
        assertArrayEquals(new int[] {2, 4, 5}, result);
    }
}
