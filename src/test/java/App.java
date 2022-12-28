import service_authenticator.BaseAuthenticator;
import service_authenticator.ServiceAuthenticator;
import com.azure.security.keyvault.secrets.SecretClient;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;

/**
 * @author Dushmantha
 * @email chanakadushmantha73@gmail.com
 * @date 12/23/2022
 */

public class App {

    public static void main(String[] args)  {

        String keyVaultUri = "https://%s.vault.azure.net"; // replace key vault name with '%s'
        String keyVaultName = "auth-library";
        String provider = "azure";

        BaseAuthenticator authenticator = new ServiceAuthenticator().getAuthenticator(provider);
        SecretClient secretClient = authenticator.clientAuthentication(keyVaultUri, keyVaultName);

        System.out.println("Retrieving your secret...");

        String authority = authenticator.retrieveSecretValue(secretClient, "authority");
        String scope = authenticator.retrieveSecretValue(secretClient, "scope");
        String clientId = authenticator.retrieveSecretValue(secretClient, "clientid");
        String clientSecret = authenticator.retrieveSecretValue(secretClient, "clientsecret");

        System.out.println("Your secret's Authority value is '" + authority);
        System.out.println("Your secret's scope value is '" + scope);
        System.out.println("Your secret's client id value is '" + clientId);
        System.out.println("Your secret's client Secret value is '" + clientSecret);

        ConfidentialClientApplication clientApplication = authenticator.createClientApplication(clientId, authority, clientSecret);

        System.out.println("Retrieving service access token...");

        System.out.println("Bearer "+authenticator.getAccessToken(clientApplication, scope));
    }
}
