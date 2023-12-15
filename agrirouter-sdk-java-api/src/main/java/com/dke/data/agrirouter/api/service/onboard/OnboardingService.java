package com.dke.data.agrirouter.api.service.onboard;

import com.dke.data.agrirouter.api.dto.onboard.OnboardingError;
import com.dke.data.agrirouter.api.dto.onboard.OnboardingResponse;
import com.dke.data.agrirouter.api.service.parameters.AuthorizationRequestParameters;
import com.dke.data.agrirouter.api.service.parameters.OnboardingParameters;

import java.util.Optional;

/**
 * Service for the onboarding process.
 */
@SuppressWarnings("unused")
public interface OnboardingService {

    /**
     * Oboarding of an endpoint.
     *
     * @param parameters-
     * @return -
     */
    OnboardingResponse onboard(OnboardingParameters parameters);

    String generateAuthorizationUrl(AuthorizationRequestParameters parameters);

    /**
     * The last error as OnboardingError-Object, if the last onboarding failed as object read from the
     * JSON String
     *
     * @param errorResponse -
     * @return The last error as OnboardingError-Object
     */
    Optional<OnboardingError> getLastOnboardingError(String errorResponse);
}
