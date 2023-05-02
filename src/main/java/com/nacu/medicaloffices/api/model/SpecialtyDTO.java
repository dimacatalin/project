package com.nacu.medicaloffices.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpecialtyDTO {

    private Long id;
    private String name;

    @Builder
    public SpecialtyDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
