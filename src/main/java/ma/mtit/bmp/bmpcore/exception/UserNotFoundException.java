package ma.mtit.bmp.bmpcore.exception;

public class UserNotFoundException extends RuntimeException {
    //SOLID
    public UserNotFoundException(String message) {
        super(message);
    }
}
