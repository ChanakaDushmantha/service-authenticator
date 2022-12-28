package service_authenticator;

import com.azure.security.keyvault.secrets.SecretClient;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;

/**
 * @author Dushmantha
 * @email chanakadushmantha73@gmail.com
 * @date 12/22/2022
 */
public interface BaseAuthenticator {

    ConfidentialClientApplication createClientApplication(String clientId, String authority, String clientSecret);

    String getAccessToken(ConfidentialClientApplication cca, String scope);

    SecretClient clientAuthentication(String keyVaultUri, String keyVaultName);

    String retrieveSecretValue(SecretClient secretClient, String secretName);

    String authenticate(String username, String password);

    String clientAuthenticate(String clientSecret);

    String asyncClientAuthenticate(String clientSecret);

    void logout();
}
