package service_authenticator.azure;

import service_authenticator.AuthException;
import service_authenticator.BaseAuthenticator;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.microsoft.aad.msal4j.*;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * @author Dushmantha
 * @email chanakadushmantha73@gmail.com
 * @date 12/22/2022
 */

public class BaseAuthenticatorImpl implements BaseAuthenticator {

    @Override
    public ConfidentialClientApplication createClientApplication(String clientId, String authority, String clientSecret) {
        // Load properties file and set properties used throughout the sample
        ConfidentialClientApplication cca;
        try {
            cca = ConfidentialClientApplication.builder(clientId, ClientCredentialFactory.createFromSecret(clientSecret)).
                    authority(authority).
                    build();
        } catch (Exception ex) {
            throw new AuthException(String.format("Error creating client application object: %s", ex.getMessage()),
                    ex.getCause());
        }
        return cca;
    }

    @Override
    public String getAccessToken(ConfidentialClientApplication cca, String scope) {
        // With client credentials flows the scope is ALWAYS of the shape "resource/.default", as the
        // application permissions need to be set statically (in the portal), and then granted by a tenant administrator
        CompletableFuture<IAuthenticationResult> future;
        String accessToken;
        try {
            ClientCredentialParameters clientCredentialParam = ClientCredentialParameters.builder(
                    Collections.singleton(scope)).build();
            future = cca.acquireToken(clientCredentialParam);
            IAuthenticationResult result = future.get();
            accessToken = result.accessToken();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AuthException(String.format("Error generating acquire token object: %s", e.getMessage()),
                    e.getCause());
        } catch (ExecutionException e) {
            throw new AuthException(String.format("Error generating acquire token object: %s", e.getMessage()),
                    e.getCause());
        }
        return accessToken;
    }

    @Override
    public SecretClient clientAuthentication(String keyVaultUri, String keyVaultName) {
        //Authenticate and create a client with default credentials
        try {
            String keyVaultFullUri;
            if(keyVaultName != null)
                keyVaultFullUri = String.format(keyVaultUri, keyVaultName);
            else
                keyVaultFullUri = keyVaultUri;
            return new SecretClientBuilder()
                    .vaultUrl(keyVaultFullUri)
                    .credential(new DefaultAzureCredentialBuilder().build())
                    .buildClient();
        } catch (Exception ex) {
            throw new AuthException(String.format("Error creating client application object: %s", ex.getMessage()),
                    ex.getCause());
        }
    }

    @Override
    public String retrieveSecretValue(SecretClient secretClient, String secretName) {
        try {
            KeyVaultSecret keyVaultSecret = secretClient.getSecret(secretName);
            return keyVaultSecret.getValue();
        } catch (Exception ex) {
            throw new AuthException(String.format("Error getting key vault secret object: %s", ex.getMessage()),
                    ex.getCause());
        }
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
        //Todo implementation
    }

}
