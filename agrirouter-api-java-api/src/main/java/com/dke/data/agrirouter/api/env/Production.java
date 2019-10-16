package com.dke.data.agrirouter.api.env;

import java.util.List;

/**
 * Abstraction of the production environment, currently no overrides because the default is
 * production already.
 */
public abstract class Production implements Environment {

  private static final String ENV_BASE_URL = "https://goto.my-agrirouter.com";
  private static final String API_PREFIX = "/api/v1.0";
  private static final String REGISTRATION_SERVICE_URL =
      "https://agrirouter-registration-service.cfapps.eu1.hana.ondemand.com";

  @Override
  public String getEnvironmentBaseUrl() {
    return ENV_BASE_URL;
  }

  @Override
  public String getApiPrefix() {
    return API_PREFIX;
  }

  @Override
  public String getRegistrationServiceUrl() {
    return REGISTRATION_SERVICE_URL;
  }

  @Override
  public List<String> getRootCertificates() {
    throw new RuntimeException("Not supported. Deprecated.");
  }
}
