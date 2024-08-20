package com.dke.data.agrirouter.test;

import com.dke.data.agrirouter.api.dto.onboard.OnboardingResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Provider for onboarding responses.
 */
public class OnboardingResponseRepository {

    private static final String FOLDER = "./onboarding-responses/";
    private static final String FILE_SUFFIX = ".json";

    /**
     * Read the onboarding response from the file system.
     *
     * @param identifier -
     * @return -
     * @throws IOException -
     */
    public static OnboardingResponse read(Identifier identifier) throws IOException {
        var path = Paths.get(FOLDER.concat(identifier.getFileName()).concat(FILE_SUFFIX));
        final var fileContent = new StringBuilder();
        Files.readAllLines(path).forEach(fileContent::append);
        return new Gson().fromJson(fileContent.toString(), OnboardingResponse.class);
    }

    /**
     * Save the onboarding response to the file system.
     *
     * @param identifier         -
     * @param onboardingResponse -
     * @throws IOException -
     */
    public static void save(Identifier identifier, OnboardingResponse onboardingResponse)
            throws IOException {
        var fileContent = new Gson().toJson(onboardingResponse);
        var path = Paths.get(FOLDER.concat(identifier.getFileName()).concat(FILE_SUFFIX));
        Files.write(path, fileContent.getBytes());
    }

    /**
     * Identifier for the onboarding responses.
     */
    public enum Identifier {
        FARMING_SOFTWARE("farming-software"),
        TELEMETRY_PLATFORM("telemetry-platform"),
        COMMUNICATION_UNIT("communication-unit"),
        MQTT_COMMUNICATION_UNIT("mqtt-communication-unit");

        private final String fileName;

        Identifier(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }
}
