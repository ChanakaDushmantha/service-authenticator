package service_authenticator;

/**
 * @author Dushmantha
 * @email chanakadushmantha73@gmail.com
 * @date 12/22/2022
 */
public class AuthException extends RuntimeException {

    public AuthException(String message, Throwable cause){
        super(message, cause);
    }

    public AuthException(String message){
        super(message);
    }
}
