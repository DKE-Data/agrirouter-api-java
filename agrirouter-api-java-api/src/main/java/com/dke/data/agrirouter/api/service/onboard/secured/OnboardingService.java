package com.dke.data.agrirouter.api.service.onboard.secured;

import com.dke.data.agrirouter.api.dto.onboard.OnboardingResponse;
import com.dke.data.agrirouter.api.exception.*;
import com.dke.data.agrirouter.api.service.parameters.AuthenticationUrlParameters;
import com.dke.data.agrirouter.api.service.parameters.SecuredOnboardingParameters;

/** Service for the onboarding process. */
public interface OnboardingService {

  /**
   * Oboarding of an endpoint.
   *
   * @param parameters-
   * @return -
   */
  OnboardingResponse onboard(SecuredOnboardingParameters parameters)
      throws CouldNotFindTimeZoneException, InvalidUrlForRequestException,
          ForbiddenRequestException, UnauthorizedRequestException, UnexpectedHttpStatusException,
          InvalidSignatureException, CouldNotCreatePrivateKeyException,
          CouldNotCreatePublicKeyException;

  /**
   * Verify the onboarding request to ensure correct signature and hashing.
   *
   * @param parameters -
   */
  void verify(SecuredOnboardingParameters parameters)
      throws CouldNotFindTimeZoneException, InvalidSignatureException,
          CouldNotCreatePrivateKeyException, CouldNotCreatePublicKeyException;

  /**
   * Generating the authentication URL.
   *
   * @param parameters -
   * @return -
   */
  String generateAuthenticationUrl(AuthenticationUrlParameters parameters);
}
