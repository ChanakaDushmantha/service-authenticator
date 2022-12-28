package service_authenticator;


import service_authenticator.azure.BaseAuthenticatorImpl;

/**
 * @author Dushmantha
 * @email chanakadushmantha73@gmail.com
 * @date 12/22/2022
 * Factory class for service providers
 */
public class ServiceAuthenticator {
    public BaseAuthenticator getAuthenticator(String provider){

        switch (provider) {
            case "azure":
                return new BaseAuthenticatorImpl();
            case "gcp":
                throw new IllegalArgumentException("Unknown provider");
                //return new bst_service_authenticator.gcp.BaseAuthenticatorImpl();
            case "aws":
                throw new IllegalArgumentException("Unknown provider");
                //return new bst_service_authenticator.cognito.BaseAuthenticatorImpl();
            default:
                throw new IllegalArgumentException("Unknown provider");
        }
    }
}
