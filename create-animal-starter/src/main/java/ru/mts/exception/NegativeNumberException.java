package ru.mts.exception;

public class NegativeNumberException extends IllegalArgumentException {
    public NegativeNumberException() {
        super();
    }

    public NegativeNumberException(int n) {
        super("Число должно быть равно или больше нуля. Текущее число: " + n);
    }
}
