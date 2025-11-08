package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DrziPredmetRequest {
    @NotNull
    private Long skolskaGodinaId;
}
