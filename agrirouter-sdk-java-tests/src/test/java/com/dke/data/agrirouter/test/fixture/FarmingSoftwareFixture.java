package com.dke.data.agrirouter.test.fixture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.dke.data.agrirouter.api.dto.onboard.OnboardingResponse;
import com.dke.data.agrirouter.api.enums.CertificationType;
import com.dke.data.agrirouter.api.enums.Gateway;
import com.dke.data.agrirouter.api.env.QA;
import com.dke.data.agrirouter.api.service.onboard.secured.OnboardingService;
import com.dke.data.agrirouter.api.service.parameters.SecuredOnboardingParameters;
import com.dke.data.agrirouter.impl.onboard.secured.OnboardingServiceImpl;
import com.dke.data.agrirouter.test.AbstractIntegrationTest;
import com.dke.data.agrirouter.test.OnboardingResponseRepository;
import java.io.IOException;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/** Class to onboard endpoints for different reasons. */
@SuppressWarnings("ALL")
class FarmingSoftwareFixture extends AbstractIntegrationTest {

  /**
   * Create a new registration token by using the following link:
   *
   * <p>https://agrirouter-qa.cfapps.eu10.hana.ondemand.com/application/905152eb-c526-47a3-b871-aa46d065bb4c/authorize?response_type=onboard&state=my-custom-state-to-identify-the-request&redirect_uri=
   */
  @Test
  @Disabled("Please replace the placeholder for the registration code to run the test case.")
  void onboardFarmingSoftwareAndSaveToFile() throws IOException {
    OnboardingService onboardingService = new OnboardingServiceImpl(new QA() {});
    SecuredOnboardingParameters onboardingParameters = new SecuredOnboardingParameters();
    onboardingParameters.setRegistrationCode("bba7438c45");
    onboardingParameters.setApplicationId(farmingSoftware.getApplicationId());
    onboardingParameters.setCertificationVersionId(farmingSoftware.getCertificationVersionId());
    onboardingParameters.setCertificationType(CertificationType.P12);
    onboardingParameters.setGatewayId(Gateway.REST.getKey());
    onboardingParameters.setUuid(UUID.randomUUID().toString());
    onboardingParameters.setPrivateKey(farmingSoftware.getPrivateKey());
    onboardingParameters.setPublicKey(farmingSoftware.getPublicKey());
    final OnboardingResponse onboardingResponse = onboardingService.onboard(onboardingParameters);
    assertNotNull(onboardingResponse);
    assertNotNull(onboardingResponse.getCapabilityAlternateId());
    assertNotNull(onboardingResponse.getDeviceAlternateId());
    assertNotNull(onboardingResponse.getSensorAlternateId());
    assertNotNull(onboardingResponse.getAuthentication());
    assertNotNull(onboardingResponse.getAuthentication().getCertificate());
    assertNotNull(onboardingResponse.getAuthentication().getSecret());
    assertNotNull(onboardingResponse.getAuthentication().getType());
    assertNotNull(onboardingResponse.getConnectionCriteria());
    assertNotNull(onboardingResponse.getConnectionCriteria().getMeasures());
    assertNotNull(onboardingResponse.getConnectionCriteria().getCommands());
    OnboardingResponseRepository.save(
        OnboardingResponseRepository.Identifier.FARMING_SOFTWARE, onboardingResponse);
  }

  /**
   * Create a new registration token by using the following link:
   *
   * <p>https://agrirouter-qa.cfapps.eu10.hana.ondemand.com/application/905152eb-c526-47a3-b871-aa46d065bb4c/authorize?response_type=onboard&state=my-custom-state-to-identify-the-request&redirect_uri=
   */
  @Test
  @Disabled("Please replace the placeholder for the registration code to run the test case.")
  void onboardAndDeactivateFarmingSoftwareAndSaveToFile() throws IOException {
    OnboardingService onboardingService = new OnboardingServiceImpl(new QA() {});
    SecuredOnboardingParameters onboardingParameters = new SecuredOnboardingParameters();
    onboardingParameters.setRegistrationCode("1f91fb7279");
    onboardingParameters.setApplicationId(farmingSoftware.getApplicationId());
    onboardingParameters.setCertificationVersionId(farmingSoftware.getCertificationVersionId());
    onboardingParameters.setCertificationType(CertificationType.P12);
    onboardingParameters.setGatewayId(Gateway.REST.getKey());
    onboardingParameters.setUuid(UUID.randomUUID().toString());
    onboardingParameters.setPrivateKey(farmingSoftware.getPrivateKey());
    onboardingParameters.setPublicKey(farmingSoftware.getPublicKey());
    final OnboardingResponse onboardingResponse = onboardingService.onboard(onboardingParameters);
    assertNotNull(onboardingResponse);
    assertNotNull(onboardingResponse.getCapabilityAlternateId());
    assertNotNull(onboardingResponse.getDeviceAlternateId());
    assertNotNull(onboardingResponse.getSensorAlternateId());
    assertNotNull(onboardingResponse.getAuthentication());
    assertNotNull(onboardingResponse.getAuthentication().getCertificate());
    assertNotNull(onboardingResponse.getAuthentication().getSecret());
    assertNotNull(onboardingResponse.getAuthentication().getType());
    assertNotNull(onboardingResponse.getConnectionCriteria());
    assertNotNull(onboardingResponse.getConnectionCriteria().getMeasures());
    assertNotNull(onboardingResponse.getConnectionCriteria().getCommands());
    OnboardingResponseRepository.save(
        OnboardingResponseRepository.Identifier.FARMING_SOFTWARE_DEACTIVATED, onboardingResponse);
  }

  /**
   * Create a new registration token by using the following link:
   *
   * <p>https://agrirouter-qa.cfapps.eu10.hana.ondemand.com/application/905152eb-c526-47a3-b871-aa46d065bb4c/authorize?response_type=onboard&state=my-custom-state-to-identify-the-request&redirect_uri=
   */
  @Test
  @Disabled("Please replace the placeholder for the registration code to run the test case.")
  void onboardAndRemoveFarmingSoftwareAndSaveToFile() throws IOException {
    OnboardingService onboardingService = new OnboardingServiceImpl(new QA() {});
    SecuredOnboardingParameters onboardingParameters = new SecuredOnboardingParameters();
    onboardingParameters.setRegistrationCode("ff28b2fa22");
    onboardingParameters.setApplicationId(farmingSoftware.getApplicationId());
    onboardingParameters.setCertificationVersionId(farmingSoftware.getCertificationVersionId());
    onboardingParameters.setCertificationType(CertificationType.P12);
    onboardingParameters.setGatewayId(Gateway.REST.getKey());
    onboardingParameters.setUuid(UUID.randomUUID().toString());
    onboardingParameters.setPrivateKey(farmingSoftware.getPrivateKey());
    onboardingParameters.setPublicKey(farmingSoftware.getPublicKey());
    final OnboardingResponse onboardingResponse = onboardingService.onboard(onboardingParameters);
    assertNotNull(onboardingResponse);
    assertNotNull(onboardingResponse.getCapabilityAlternateId());
    assertNotNull(onboardingResponse.getDeviceAlternateId());
    assertNotNull(onboardingResponse.getSensorAlternateId());
    assertNotNull(onboardingResponse.getAuthentication());
    assertNotNull(onboardingResponse.getAuthentication().getCertificate());
    assertNotNull(onboardingResponse.getAuthentication().getSecret());
    assertNotNull(onboardingResponse.getAuthentication().getType());
    assertNotNull(onboardingResponse.getConnectionCriteria());
    assertNotNull(onboardingResponse.getConnectionCriteria().getMeasures());
    assertNotNull(onboardingResponse.getConnectionCriteria().getCommands());
    OnboardingResponseRepository.save(
        OnboardingResponseRepository.Identifier.FARMING_SOFTWARE_REMOVED, onboardingResponse);
  }
}
