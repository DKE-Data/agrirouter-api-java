package com.dke.data.agrirouter.impl.common.ssl;

import com.dke.data.agrirouter.api.exception.CouldNotCreateDynamicKeyStoreException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KeyStoreCreationServiceTest {

    @Test
    void givenValidPEMCreateAndReturnKeystoreFromPEMShouldReturnKeyStore() {
        var secret = "lL7qQ8lL4iI3zZ7qQ4xX1hH";
        var certificate =
                "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIE6zAdBgoqhkiG9w0BDAEDMA8ECKjNsB97Jcn1AgMCAAAEggTIskb9BXQ3w6u3\np9cxwMnDVm6QJWeQw+kjOnGn3qhZr+1vYdiCeDJHeKBX80PzAqBa+iX2P3Xo1Ajy\nYvrIuU0nVGtI9ZH3buyjNfN41XSLj7sffp1NHPJMeHLMa62EVgpjcrRFrLmlumqI\nKy03YMlaYiCF7VU6miJEo6TDORLIPc3dPnbNTT8EeQVIP3co5J5H9akwVfLIOmek\nzP2oFx5dQ7uny/Nm9Od57/V+abkWB2PNbQtKA16/4Fz7plqAyoFw4HOFK+FKJ50s\nGAIAmDBnV4Y6d1dwVRUNjQoZ41LbFQAILtn/W0IceA5B9PrXzSmVIcXeBE39EGZO\nHJPt/YREtTRZkBdK5+V91+yi/zqtWF6rjHOMxUQtiEiur3U0fs/LN8rwTOMrgCOY\njAIuAla06k11HoBc3jpIPBxaJTNuN+W2XWqJgB02ZS6iGkRHYlWr+qtK473Q2Ymh\nbKQqSfMM5TJUMzsUS8QMuKrnUc0eIINNdWIuFrF5T7Owm9viF9UdqPg8DYP03fZg\nhtY8dvVzieQtjpWMCEOHrgI42Zp/hV9Er7LeE86DUvgVRHqgJtROA6OtP5p/mtXl\ndImm7EXjor217/AADbTPokyzVNfFa8W0beCx77Fub13oaATLQAxouGG/GWLqSY6v\nRS48nnnbPdka6Z++FUSUrIdzAJ2ssQvluBsZS9PdWMFuu5tlOGdlEjhCcXPcSrzf\n2HPepqR/sDwxqSt3L6HcjsQCTcj24ebDBdsJyhfLaraW77P9a8Uc+VmyUzGF+r3G\nLtqo8W3UZozw6/xLlUROuTfrWewOEfMxFQFkQFEJADN5UdGvGsY1aiUgBxtNG5i4\ne9gRIDpbaJcE4am/tDqTD8ofTCYCP9UX4xWEwDvDQlBkshr0yDxgt+mVEyaJv7hA\n6o2tEW/d/pQ0uqFOcirUcMmoQmye+FJi7YMUTxmIw3Q+Y19MRmVyvwePU1dKIr87\neypeEU5HFd1DUlPDHMQMw9uYkwTaJ8C8LPvxKi3VD5SLqFKDYl5JLBf7FU5LEjzO\noph/1rkUKktsfye0RF6AOooD1SwfnsfWDprdwibIDbNBumE9nONESrbqlKJp5/OC\n3gwL7v8x9Xp+r3J1k01flSojRYQO+2XC5ABjChc7mbD1ROrb8SWTcmyUanJGzNxF\nz+AgX0B0RxLPZPBafNjtktjT9IeK0UrfmlhpIrBTSd65Vzb+dg1DsmRWq2L/+L1o\nUM1lo9KBya+p9wv1Qtu5vO7oP4jJJUfr+RW0jAOsxF6H5mrSCq5ww0vWdiDiu6+m\npQ15Wp3vxiAXtnej1T5B/j97uzD2Wpn13AGemJuzR9NBZ7E/oa10CTf00nG+D0Mj\nenS0iJYvd2VQmm8QUOg5uDBjEqgGlCMxEUBdENyG73kbO8zqU9vCfqRnwuzBzuG4\n2nKci9UgqnDJ9cwOLIheD/YO8lMeZoR0aoeJOMoMMF3YM0NmS5FLLb4NP8Fc03My\nms0Lawg8os3nDtLG/xREu7AEiuk5n8N1Qu/UMrllSC1W5FpNkyNLTK4FuOKdN/aS\nJ5/f9MH0gI4zWtNgKoQH93fU1v9Xrr/fFpDTBOfuoe9dQvRLpa5Mw55ftHAvweEu\nbupO8m9l0y/JJWbMu7Ql\n-----END ENCRYPTED PRIVATE KEY-----\n-----BEGIN CERTIFICATE-----\nMIIEPzCCAyegAwIBAgIOAJeZtIjQZANnEAEC/JwwDQYJKoZIhvcNAQELBQAwVjEL\nMAkGA1UEBhMCREUxIzAhBgNVBAoTGlNBUCBJb1QgVHJ1c3QgQ29tbXVuaXR5IElJ\nMSIwIAYDVQQDExlTQVAgSW50ZXJuZXQgb2YgVGhpbmdzIENBMB4XDTE4MDEwNTIx\nNTEwMVoXDTE5MDEwNTIxNTEwMVowgY0xCzAJBgNVBAYTAkRFMRwwGgYDVQQKExNT\nQVAgVHJ1c3QgQ29tbXVuaXR5MRUwEwYDVQQLEwxJb1QgU2VydmljZXMxSTBHBgNV\nBAMUQGNsaWVudGlkOjFlMWNkYTUwLTM3Y2UtNDMzZS1hNjU2LTFhOThmM2RkMDU2\nNnxpbnN0YW5jZWlkOmRrZS1kZXYwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEK\nAoIBAQC6Jmw4dFIhbtZavc8YYwAyfrlZUS6+GVtluN4OTLwOC2ROARwXExUgzau9\nCP+B48EWSDxyU+CZeVKDOVhp/KlDIP2wgELs2pzMjPr8M3Rz8wg/BGnrJmTY5UGh\n1BhClHKj7Pv4VLcWW7BXqsFQh/orTRZDhsedOTv4+4oXXe/ORJx6r+A1BBcRRYOx\nZkoHrI16DPgcLIku1aFGnWXwu3kfbbXSg/NPa0Z9fTPJhqeNLU5ple4pHqm5crUh\nIGo7d9Xr/aoyP6SJtfXyTBxeoZS1vf5RIQKw/s3bKKWPn34LHIKzLP+6zgUQAk7p\n2V69WtrUSFs1jFTQUTEi0VPiqZ2NAgMBAAGjgdIwgc8wSAYDVR0fBEEwPzA9oDug\nOYY3aHR0cHM6Ly90Y3MubXlzYXAuY29tL2NybC9UcnVzdENvbW11bml0eUlJL1NB\nUElvVENBLmNybDAMBgNVHRMBAf8EAjAAMCUGA1UdEgQeMByGGmh0dHA6Ly9zZXJ2\naWNlLnNhcC5jb20vVENTMA4GA1UdDwEB/wQEAwIGwDAdBgNVHQ4EFgQU3IYJevvw\nQMxj3qxT8km6ogbQB2cwHwYDVR0jBBgwFoAUlbez9Vje1bSzWEbg8qbJeE69LXUw\nDQYJKoZIhvcNAQELBQADggEBADnGIY81ngPJOuF3qL9wRHAYiXLlHMT5J54qfRGu\ncTAdDEC8DCjlXUAg+c6HNvBJbMV4oPfziN0f53Z3f0WpumTk/J6lQy0dByGtt+Dv\nki4+WjrooMdG7bPsHt1aZLIAa/TWY8B62G0r7zILAne5xgvuy+K1MH0dENR7KOdl\nwovrXg8fePWX/ejRhk4iXyf7mDS0t+0zRNTlOIYpPrU+h8bc03Afn7xJ/F5khxzF\nlPLIxyS1wrFj2V3FSrJqU0XR686u6E7Zjyh8Jjzvh5xazQcbuhC5ZVUL08xkZYJI\nF/ocLSZ++H2dacM9GTSB/5+ShM6aNxMeWWMrN4AYA9tmA50=\n-----END CERTIFICATE-----\n";
        var keyStore =
                new KeyStoreCreationService().createAndReturnKeystoreFromPEM(certificate, secret);
        assertNotNull(keyStore);
    }

    @Test
    void givenInvalidPEMCreateAndReturnKeystoreFromPEMShouldFail() {
        var secret = "lL7qQ8lL4iI3zZ7qQ4xX1hH";
        var certificate = "TOFUWURST";
        assertThrows(
                CouldNotCreateDynamicKeyStoreException.class,
                () -> new KeyStoreCreationService().createAndReturnKeystoreFromPEM(certificate, secret));
    }

    @Test
    void givenValidP12CreateAndReturnKeystoreFromP12ShouldReturnKeyStore() {
        var secret = "iI8rR5lL6fF0wW1yY6zZ1lL";
        var certificate =
                "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";
        var keyStore =
                new KeyStoreCreationService().createAndReturnKeystoreFromP12(certificate, secret);
        assertNotNull(keyStore);
    }

    @Test
    void givenInvalidP12CreateAndReturnKeystoreFromP12ShouldFail() {
        var secret = "lL7qQ8lL4iI3zZ7qQ4xX1hH";
        var certificate = "TOFUWURST";
        assertThrows(
                CouldNotCreateDynamicKeyStoreException.class,
                () -> new KeyStoreCreationService().createAndReturnKeystoreFromP12(certificate, secret));
    }

    @Test
    void givenValidCertificateAndPrivateKeyCreateKeyStoreInClasspathShouldReturnTmpKeystoreName()
            throws Throwable {
        var certificate =
                new KeyStoreCreationService()
                        .createCertificate(
                                "MIIEPzCCAyegAwIBAgIOAJeZtIjQZANnEAEC/JwwDQYJKoZIhvcNAQELBQAwVjELMAkGA1UEBhMCREUxIzAhBgNVBAoTGlNBUCBJb1QgVHJ1c3QgQ29tbXVuaXR5IElJMSIwIAYDVQQDExlTQVAgSW50ZXJuZXQgb2YgVGhpbmdzIENBMB4XDTE4MDEwNTIxNTEwMVoXDTE5MDEwNTIxNTEwMVowgY0xCzAJBgNVBAYTAkRFMRwwGgYDVQQKExNTQVAgVHJ1c3QgQ29tbXVuaXR5MRUwEwYDVQQLEwxJb1QgU2VydmljZXMxSTBHBgNVBAMUQGNsaWVudGlkOjFlMWNkYTUwLTM3Y2UtNDMzZS1hNjU2LTFhOThmM2RkMDU2NnxpbnN0YW5jZWlkOmRrZS1kZXYwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC6Jmw4dFIhbtZavc8YYwAyfrlZUS6+GVtluN4OTLwOC2ROARwXExUgzau9CP+B48EWSDxyU+CZeVKDOVhp/KlDIP2wgELs2pzMjPr8M3Rz8wg/BGnrJmTY5UGh1BhClHKj7Pv4VLcWW7BXqsFQh/orTRZDhsedOTv4+4oXXe/ORJx6r+A1BBcRRYOxZkoHrI16DPgcLIku1aFGnWXwu3kfbbXSg/NPa0Z9fTPJhqeNLU5ple4pHqm5crUhIGo7d9Xr/aoyP6SJtfXyTBxeoZS1vf5RIQKw/s3bKKWPn34LHIKzLP+6zgUQAk7p2V69WtrUSFs1jFTQUTEi0VPiqZ2NAgMBAAGjgdIwgc8wSAYDVR0fBEEwPzA9oDugOYY3aHR0cHM6Ly90Y3MubXlzYXAuY29tL2NybC9UcnVzdENvbW11bml0eUlJL1NBUElvVENBLmNybDAMBgNVHRMBAf8EAjAAMCUGA1UdEgQeMByGGmh0dHA6Ly9zZXJ2aWNlLnNhcC5jb20vVENTMA4GA1UdDwEB/wQEAwIGwDAdBgNVHQ4EFgQU3IYJevvwQMxj3qxT8km6ogbQB2cwHwYDVR0jBBgwFoAUlbez9Vje1bSzWEbg8qbJeE69LXUwDQYJKoZIhvcNAQELBQADggEBADnGIY81ngPJOuF3qL9wRHAYiXLlHMT5J54qfRGucTAdDEC8DCjlXUAg+c6HNvBJbMV4oPfziN0f53Z3f0WpumTk/J6lQy0dByGtt+Dvki4+WjrooMdG7bPsHt1aZLIAa/TWY8B62G0r7zILAne5xgvuy+K1MH0dENR7KOdlwovrXg8fePWX/ejRhk4iXyf7mDS0t+0zRNTlOIYpPrU+h8bc03Afn7xJ/F5khxzFlPLIxyS1wrFj2V3FSrJqU0XR686u6E7Zjyh8Jjzvh5xazQcbuhC5ZVUL08xkZYJIF/ocLSZ++H2dacM9GTSB/5+ShM6aNxMeWWMrN4AYA9tmA50=");
        var privateKey =
                new KeyStoreCreationService()
                        .createPrivateKey(
                                "MIIE6zAdBgoqhkiG9w0BDAEDMA8ECKjNsB97Jcn1AgMCAAAEggTIskb9BXQ3w6u3p9cxwMnDVm6QJWeQw+kjOnGn3qhZr+1vYdiCeDJHeKBX80PzAqBa+iX2P3Xo1AjyYvrIuU0nVGtI9ZH3buyjNfN41XSLj7sffp1NHPJMeHLMa62EVgpjcrRFrLmlumqIKy03YMlaYiCF7VU6miJEo6TDORLIPc3dPnbNTT8EeQVIP3co5J5H9akwVfLIOmekzP2oFx5dQ7uny/Nm9Od57/V+abkWB2PNbQtKA16/4Fz7plqAyoFw4HOFK+FKJ50sGAIAmDBnV4Y6d1dwVRUNjQoZ41LbFQAILtn/W0IceA5B9PrXzSmVIcXeBE39EGZOHJPt/YREtTRZkBdK5+V91+yi/zqtWF6rjHOMxUQtiEiur3U0fs/LN8rwTOMrgCOYjAIuAla06k11HoBc3jpIPBxaJTNuN+W2XWqJgB02ZS6iGkRHYlWr+qtK473Q2YmhbKQqSfMM5TJUMzsUS8QMuKrnUc0eIINNdWIuFrF5T7Owm9viF9UdqPg8DYP03fZghtY8dvVzieQtjpWMCEOHrgI42Zp/hV9Er7LeE86DUvgVRHqgJtROA6OtP5p/mtXldImm7EXjor217/AADbTPokyzVNfFa8W0beCx77Fub13oaATLQAxouGG/GWLqSY6vRS48nnnbPdka6Z++FUSUrIdzAJ2ssQvluBsZS9PdWMFuu5tlOGdlEjhCcXPcSrzf2HPepqR/sDwxqSt3L6HcjsQCTcj24ebDBdsJyhfLaraW77P9a8Uc+VmyUzGF+r3GLtqo8W3UZozw6/xLlUROuTfrWewOEfMxFQFkQFEJADN5UdGvGsY1aiUgBxtNG5i4e9gRIDpbaJcE4am/tDqTD8ofTCYCP9UX4xWEwDvDQlBkshr0yDxgt+mVEyaJv7hA6o2tEW/d/pQ0uqFOcirUcMmoQmye+FJi7YMUTxmIw3Q+Y19MRmVyvwePU1dKIr87eypeEU5HFd1DUlPDHMQMw9uYkwTaJ8C8LPvxKi3VD5SLqFKDYl5JLBf7FU5LEjzOoph/1rkUKktsfye0RF6AOooD1SwfnsfWDprdwibIDbNBumE9nONESrbqlKJp5/OC3gwL7v8x9Xp+r3J1k01flSojRYQO+2XC5ABjChc7mbD1ROrb8SWTcmyUanJGzNxFz+AgX0B0RxLPZPBafNjtktjT9IeK0UrfmlhpIrBTSd65Vzb+dg1DsmRWq2L/+L1oUM1lo9KBya+p9wv1Qtu5vO7oP4jJJUfr+RW0jAOsxF6H5mrSCq5ww0vWdiDiu6+mpQ15Wp3vxiAXtnej1T5B/j97uzD2Wpn13AGemJuzR9NBZ7E/oa10CTf00nG+D0MjenS0iJYvd2VQmm8QUOg5uDBjEqgGlCMxEUBdENyG73kbO8zqU9vCfqRnwuzBzuG42nKci9UgqnDJ9cwOLIheD/YO8lMeZoR0aoeJOMoMMF3YM0NmS5FLLb4NP8Fc03Myms0Lawg8os3nDtLG/xREu7AEiuk5n8N1Qu/UMrllSC1W5FpNkyNLTK4FuOKdN/aSJ5/f9MH0gI4zWtNgKoQH93fU1v9Xrr/fFpDTBOfuoe9dQvRLpa5Mw55ftHAvweEubupO8m9l0y/JJWbMu7Ql",
                                "lL7qQ8lL4iI3zZ7qQ4xX1hH");
        var tmpKeystoreName =
                new KeyStoreCreationService().createKeyStoreInClasspath(certificate, privateKey);
        assertNotNull(tmpKeystoreName);
    }

    @Test
    void givenValidEncryptedPrivateKeyCreatePrivateKeyShouldReturnPrivateKey() throws Throwable {
        var secret = "lL7qQ8lL4iI3zZ7qQ4xX1hH";
        var encryptedPrivateKey =
                "MIIE6zAdBgoqhkiG9w0BDAEDMA8ECKjNsB97Jcn1AgMCAAAEggTIskb9BXQ3w6u3p9cxwMnDVm6QJWeQw+kjOnGn3qhZr+1vYdiCeDJHeKBX80PzAqBa+iX2P3Xo1AjyYvrIuU0nVGtI9ZH3buyjNfN41XSLj7sffp1NHPJMeHLMa62EVgpjcrRFrLmlumqIKy03YMlaYiCF7VU6miJEo6TDORLIPc3dPnbNTT8EeQVIP3co5J5H9akwVfLIOmekzP2oFx5dQ7uny/Nm9Od57/V+abkWB2PNbQtKA16/4Fz7plqAyoFw4HOFK+FKJ50sGAIAmDBnV4Y6d1dwVRUNjQoZ41LbFQAILtn/W0IceA5B9PrXzSmVIcXeBE39EGZOHJPt/YREtTRZkBdK5+V91+yi/zqtWF6rjHOMxUQtiEiur3U0fs/LN8rwTOMrgCOYjAIuAla06k11HoBc3jpIPBxaJTNuN+W2XWqJgB02ZS6iGkRHYlWr+qtK473Q2YmhbKQqSfMM5TJUMzsUS8QMuKrnUc0eIINNdWIuFrF5T7Owm9viF9UdqPg8DYP03fZghtY8dvVzieQtjpWMCEOHrgI42Zp/hV9Er7LeE86DUvgVRHqgJtROA6OtP5p/mtXldImm7EXjor217/AADbTPokyzVNfFa8W0beCx77Fub13oaATLQAxouGG/GWLqSY6vRS48nnnbPdka6Z++FUSUrIdzAJ2ssQvluBsZS9PdWMFuu5tlOGdlEjhCcXPcSrzf2HPepqR/sDwxqSt3L6HcjsQCTcj24ebDBdsJyhfLaraW77P9a8Uc+VmyUzGF+r3GLtqo8W3UZozw6/xLlUROuTfrWewOEfMxFQFkQFEJADN5UdGvGsY1aiUgBxtNG5i4e9gRIDpbaJcE4am/tDqTD8ofTCYCP9UX4xWEwDvDQlBkshr0yDxgt+mVEyaJv7hA6o2tEW/d/pQ0uqFOcirUcMmoQmye+FJi7YMUTxmIw3Q+Y19MRmVyvwePU1dKIr87eypeEU5HFd1DUlPDHMQMw9uYkwTaJ8C8LPvxKi3VD5SLqFKDYl5JLBf7FU5LEjzOoph/1rkUKktsfye0RF6AOooD1SwfnsfWDprdwibIDbNBumE9nONESrbqlKJp5/OC3gwL7v8x9Xp+r3J1k01flSojRYQO+2XC5ABjChc7mbD1ROrb8SWTcmyUanJGzNxFz+AgX0B0RxLPZPBafNjtktjT9IeK0UrfmlhpIrBTSd65Vzb+dg1DsmRWq2L/+L1oUM1lo9KBya+p9wv1Qtu5vO7oP4jJJUfr+RW0jAOsxF6H5mrSCq5ww0vWdiDiu6+mpQ15Wp3vxiAXtnej1T5B/j97uzD2Wpn13AGemJuzR9NBZ7E/oa10CTf00nG+D0MjenS0iJYvd2VQmm8QUOg5uDBjEqgGlCMxEUBdENyG73kbO8zqU9vCfqRnwuzBzuG42nKci9UgqnDJ9cwOLIheD/YO8lMeZoR0aoeJOMoMMF3YM0NmS5FLLb4NP8Fc03Myms0Lawg8os3nDtLG/xREu7AEiuk5n8N1Qu/UMrllSC1W5FpNkyNLTK4FuOKdN/aSJ5/f9MH0gI4zWtNgKoQH93fU1v9Xrr/fFpDTBOfuoe9dQvRLpa5Mw55ftHAvweEubupO8m9l0y/JJWbMu7Ql";
        var privateKey =
                new KeyStoreCreationService().createPrivateKey(encryptedPrivateKey, secret);
        assertNotNull(privateKey);
    }

    @Test
    void givenInvalidEncryptedPrivateKeyCreatePrivateKeyShouldFail() {
        var secret = "lL7qQ8lL4iI3zZ7qQ4xX1hH";
        var encryptedPrivateKey = "TOFUWURST";
        assertThrows(
                IllegalArgumentException.class,
                () -> new KeyStoreCreationService().createPrivateKey(encryptedPrivateKey, secret));
    }

    @Test
    void givenValidEncryptedCertificateCreateCertificateShouldReturnCertificate() throws Throwable {
        var encryptedCertificate =
                "MIIEQDCCAyigAwIBAgIPAMGPhggNbxUUEAECACO7MA0GCSqGSIb3DQEBCwUAMFYxCzAJBgNVBAYTAkRFMSMwIQYDVQQKExpTQVAgSW9UIFRydXN0IENvbW11bml0eSBJSTEiMCAGA1UEAxMZU0FQIEludGVybmV0IG9mIFRoaW5ncyBDQTAeFw0xODAyMTQwNzI2MzJaFw0xOTAyMTQwNzI2MzJaMIGNMQswCQYDVQQGEwJERTEcMBoGA1UEChMTU0FQIFRydXN0IENvbW11bml0eTEVMBMGA1UECxMMSW9UIFNlcnZpY2VzMUkwRwYDVQQDFEBjbGllbnRpZDo0MjIzN2NkZC0wYjFkLTQyNzItODNlYS04MWVmNDgyYjI3ODd8aW5zdGFuY2VpZDpka2UtZGV2MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnbzz3hdS8sMNyZt/uGzMJeKgY+o9d7RekimomsrF5ve4IPa4RPSz9I6V0BexXbrUrPwZcZT9OAJyW8Ig3U0dy/1bcLrhtI+auLRXg6xS0I23+PCsNxKDGkf3F6IlPd+qG6XriverOQ1XFFiN1rTHmU6fL6bSkDNp/hJCBU9NwieUqoXl1UptAf5qSHyUFlaiWtAuJvCxw0V1kgJthljVhw8kRvdsNpD8lkNqw11/F5LzpJy0klSgGjGvCEDkh4FSGqSwtXK5UqQXy8d9IzYowEC3Kpl2Og64A/h6x+So5sJIVLKLXa1wIKV1KtzRnWZ6YFpuIosbVL2ahJko8O+nUQIDAQABo4HSMIHPMEgGA1UdHwRBMD8wPaA7oDmGN2h0dHBzOi8vdGNzLm15c2FwLmNvbS9jcmwvVHJ1c3RDb21tdW5pdHlJSS9TQVBJb1RDQS5jcmwwDAYDVR0TAQH/BAIwADAlBgNVHRIEHjAchhpodHRwOi8vc2VydmljZS5zYXAuY29tL1RDUzAOBgNVHQ8BAf8EBAMCBsAwHQYDVR0OBBYEFFc7w7CmcPbfin0wwL7jd8Sf2nMfMB8GA1UdIwQYMBaAFJW3s/VY3tW0s1hG4PKmyXhOvS11MA0GCSqGSIb3DQEBCwUAA4IBAQDuqP+HMbENyjsmj7Bbw/TIezUrUylG9SbsknxNhEyBDRRweM4vpRpjYXYQEhzg+uuPCT94pXxAJgCRs6WHsYWYnRiXjtUZ8XRKbbOIH+5ptghMspvi52LEwZDQtjkuhH0t/IzrCIz4+24xcgJbRiAqUjx021PWxlB/gkXOOTaDkc/AZmfabWZCr+K6iec4ITxf0+PwS36fK1fV+bz0CoEt3VhE+cv/X0FdansB9qCDccgCtMLKDBjGkF/wS/zCgv3PFVhxB4WUhYlE831aLUbTTLhaC7l7WX79bAAY6Fy+8d7cnaol32UnnxVMREE+2XN9m5TGCX5n6VPPYgyNMlOt";
        var certificate =
                new KeyStoreCreationService().createCertificate(encryptedCertificate);
        assertNotNull(certificate);
    }

    @Test
    void givenInvalidEncryptedCertificateCreateCertificateShouldFail() {
        var encryptedCertificate = "TOFUWURST";
        assertThrows(
                IllegalArgumentException.class,
                () -> new KeyStoreCreationService().createCertificate(encryptedCertificate));
    }
}
