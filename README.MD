# Service Authenticator

Service Authenticator is a Java library for dealing with service principal authentication that generates service tokens for microservices.

## Installation

Initial require dependencies.

```bash
<dependency>
    <groupId>com.microsoft.azure</groupId>
    <artifactId>msal4j</artifactId>
    <version>1.13.3</version>
</dependency>
<dependency>
    <groupId>com.azure</groupId>
    <artifactId>azure-security-keyvault-secrets</artifactId>
    <version>4.5.2</version>
</dependency>
<dependency>
    <groupId>com.azure</groupId>
    <artifactId>azure-identity</artifactId>
    <version>1.7.2</version>
</dependency>
```

Use maven package manager [mvn](https://maven.apache.org/install.html) to install Service Authenticator.

```bash
mvn clean install package
```

* Authenticate [azure](https://azure.microsoft.com/en-us/) to access key vault.
    * A logged-in user is used to [authenticate](https://learn.microsoft.com/en-us/cli/azure/authenticate-azure-cli) to Key Vault, which is preferred method for local development.

    ```bash
    az login
    ```
  * A [Managed Identity](https://learn.microsoft.com/en-us/azure/key-vault/general/assign-access-policy?tabs=azure-portal) should be assigned to an App Service or Virtual Machine.
  <p>&nbsp;</p>
  
  ![alt text](https://learn.microsoft.com/en-us/azure/key-vault/media/authentication/assign-access-05.png)

## Usage

Use App.java test class to test run Service Authenticator.

```bash
src/test/java/App.java
```

```java
import service_authenticator.BaseAuthenticator;
import service_authenticator.ServiceAuthenticator;
import com.azure.security.keyvault.secrets.SecretClient;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;

String keyVaultUri = "https://%s.vault.azure.net"; // replace key vault name with '%s'
String keyVaultName = "auth-library";
String provider = "azure";

BaseAuthenticator authenticator = new ServiceAuthenticator().getAuthenticator(provider);
SecretClient secretClient = authenticator.clientAuthentication(keyVaultUri, keyVaultName);

//Retrieving your secret...

String authority = authenticator.retrieveSecretValue(secretClient, "authority");
String scope = authenticator.retrieveSecretValue(secretClient, "scope");
String clientId = authenticator.retrieveSecretValue(secretClient, "clientid");
String clientSecret = authenticator.retrieveSecretValue(secretClient, "clientsecret");

System.out.println("Your secret's Authority value is '" + authority);
System.out.println("Your secret's scope value is '" + scope);
System.out.println("Your secret's client id value is '" + clientId);
System.out.println("Your secret's client Secret value is '" + clientSecret);

ConfidentialClientApplication clientApplication = authenticator.createClientApplication(clientId, authority, clientSecret);

//Retrieving service access token...

System.out.println("Bearer "+authenticator.getAccessToken(clientApplication, scope));
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

## License

[LinkedIn](https://www.linkedin.com/in/chanaka-dushmantha/)

[chanakdushman@gmail.com]()
