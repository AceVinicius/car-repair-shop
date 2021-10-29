package View;

public class InvalidFormException extends Exception {

    private static final long serialVersionUID = -1195907614665606837L;

    public InvalidFormException(String error) {
        super(error);
    }
}
