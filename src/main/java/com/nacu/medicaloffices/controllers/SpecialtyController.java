package com.nacu.medicaloffices.controllers;

import com.nacu.medicaloffices.api.model.SpecialtyDTO;
import com.nacu.medicaloffices.services.SpecialtyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(SpecialtyController.BASE_URL)
public class SpecialtyController {

    public static final String BASE_URL = "/api/specialties";

    private final SpecialtyService service;

    public SpecialtyController(SpecialtyService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialtyDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SpecialtyDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialtyDTO create(@RequestBody SpecialtyDTO specialtyDTO) {
        return service.create(specialtyDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SpecialtyDTO update(@PathVariable Long id, @RequestBody SpecialtyDTO specialtyDTO) {
        return service.saveById(id, specialtyDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
