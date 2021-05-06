package com.lab4.demo.patient;

import com.lab4.demo.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.PATIENT;

@RestController
@RequestMapping(PATIENT)
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> allPatients(){
        return patientService.findAll();
    }

    @PostMapping
    public void create(@RequestBody PatientDTO patient) {
        patientService.create(patient);
    }

    @PatchMapping
    public void edit(@RequestBody PatientDTO patient) {
        patientService.edit(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        patientService.delete(id);
    }
}
