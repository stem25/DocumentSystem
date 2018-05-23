package exception;

public class DocumentExistsException extends Exception {
    public DocumentExistsException() {
        super("Документ с таким id уже существует.");
    }
}
