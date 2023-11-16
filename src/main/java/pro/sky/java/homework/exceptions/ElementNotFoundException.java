package pro.sky.java.homework.exceptions;

public class ElementNotFoundException extends RuntimeException {
    public static final String TEMPLATE_MESSAGE = "Элемент %s не найден";
    public ElementNotFoundException(String message) {
        super(message);
    }
}
