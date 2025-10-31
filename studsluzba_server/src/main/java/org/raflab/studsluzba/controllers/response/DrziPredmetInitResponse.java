package org.raflab.studsluzba.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DrziPredmetInitResponse {
    private List<DrziPredmetPoklapanjeInitResponse> potpunoPoklapanje;
    private List<DrziPredmetPoklapanjeInitResponse> delimicnoPoklapanje;
    private List<DrziPredmetBezPoklapanjaInitResponse> bezPoklapanja;
}