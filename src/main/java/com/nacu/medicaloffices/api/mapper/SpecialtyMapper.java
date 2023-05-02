package com.nacu.medicaloffices.api.mapper;

import com.nacu.medicaloffices.api.model.SpecialtyDTO;
import com.nacu.medicaloffices.domain.Specialty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {
    SpecialtyDTO specialtyToSpecialtyDTO(Specialty specialty);
    Specialty specialtyDTOtoSpecialty(SpecialtyDTO specialtyDTO);
}
