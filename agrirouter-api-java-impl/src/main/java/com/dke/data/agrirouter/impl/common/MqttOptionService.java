package com.dke.data.agrirouter.impl.common;

import com.dke.data.agrirouter.api.dto.onboard.OnboardingResponse;
import com.dke.data.agrirouter.api.env.Environment;
import com.dke.data.agrirouter.impl.EnvironmentalService;
import com.dke.data.agrirouter.impl.common.ssl.KeyStoreCreationUtils;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class MqttOptionService extends EnvironmentalService {

  public MqttOptionService(Environment environment) {
    super(environment);
  }

  public MqttConnectOptions createMqttConnectOptions(OnboardingResponse onboardingResponse)
      throws Exception {
    MqttConnectOptions options = new MqttConnectOptions();
    options.setSocketFactory(
        KeyStoreCreationUtils.getSocketFactory(
            this.environment.getRootCertificates(),
            onboardingResponse.getAuthentication().getCertificate(),
            onboardingResponse.getAuthentication().getSecret()));
    options.setKeepAliveInterval(60);
    options.setAutomaticReconnect(true);
    options.setCleanSession(true);
    return options;
  }
}
