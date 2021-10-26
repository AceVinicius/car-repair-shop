package Model;

public class EmailException extends Exception {

    private static final long serialVersionUID = 4894045441901450424L;

    public EmailException(String error) {
        super(error);
    }
}
