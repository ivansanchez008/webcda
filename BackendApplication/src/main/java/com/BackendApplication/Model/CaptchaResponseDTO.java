package com.BackendApplication.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaResponseDTO {

    private boolean success;

    @JsonProperty("error-codes")
    private List<String>errorCodes;
}
