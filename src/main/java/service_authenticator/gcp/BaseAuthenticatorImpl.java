package service_authenticator.gcp;

import service_authenticator.BaseAuthenticator;
import com.azure.security.keyvault.secrets.SecretClient;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;

/**
 * @author Dushmantha
 * @email chanakadushmantha73@gmail.com
 * @date 12/23/2022
 * Todo implementation
 */
public class BaseAuthenticatorImpl implements BaseAuthenticator {
    @Override
    public ConfidentialClientApplication createClientApplication(String clientId, String authority, String clientSecret) {
        return null;
    }

    @Override
    public String getAccessToken(ConfidentialClientApplication cca, String scope) {
        return null;
    }

    @Override
    public SecretClient clientAuthentication(String keyVaultUri, String keyVaultName) {
        return null;
    }

    @Override
    public String retrieveSecretValue(SecretClient secretClient, String secretName) {
        return null;
    }

    @Override
    public String authenticate(String username, String password) {
        return null;
    }

    @Override
    public String clientAuthenticate(String clientSecret) {
        return null;
    }

    @Override
    public String asyncClientAuthenticate(String clientSecret) {
        return null;
    }

    @Override
    public void logout() {

    }
}
