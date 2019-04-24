package com.dke.data.agrirouter.api.service.parameters

import com.dke.data.agrirouter.api.dto.onboard.OnboardingResponse
import com.dke.data.agrirouter.api.exception.IllegalParameterDefinitionException
import com.dke.data.agrirouter.api.service.ParameterValidation
import lombok.ToString
import javax.validation.constraints.NotNull

/**
 * Parameters class. Encapsulation for the services.
 */
@ToString
class CloudOffboardingParameters : ParameterValidation {

    @NotNull
    lateinit var onboardingResponse: OnboardingResponse

    @NotNull
    lateinit var endpointIds: List<String>

    var applicationMessageID: String = ""

    override fun validate() {
        super.validate()
        if (endpointIds.isEmpty()) {
            throw IllegalParameterDefinitionException("There have to be endpoint IDs to delete.")
        }
    }

}