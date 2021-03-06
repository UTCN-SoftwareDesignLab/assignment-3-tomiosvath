package com.assignment3.consultation;

import com.assignment3.Constants;
import com.assignment3.consultation.model.Consultation;
import com.assignment3.consultation.model.dto.ConsultationRegisterDTO;
import com.assignment3.user.UserService;
import com.assignment3.patient.PatientService;
import com.assignment3.patient.model.Patient;
import com.assignment3.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;
    private final UserService userService;
    private final PatientService patientService;

    public List<ConsultationRegisterDTO> findAll() {
        return consultationRepository.findAll().stream()
                .map(consultationMapper::toDTO)
                .collect(Collectors.toList());

        /*List<ConsultationDTO> consultations = consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());

        DateTimeFormatter formatter = Constants.getDateTimeFormat();
        List<ConsultationRegisterDTO> consultationRegisterDTOS = new ArrayList<>();

        for (ConsultationDTO consultation : consultations){
            consultationRegisterDTOS.add(ConsultationRegisterDTO.builder()
                    .id(consultation.getId())
                    .doctorName(consultation.getDoctor().getUsername())
                    .patientName(consultation.getPatient().getName())
                    .date(consultation.getDate().format(formatter))
                    .author(consultation.getAuthor())
                    .details(consultation.getDetails())
                    .build());
        }

        return consultationRegisterDTOS;
        */
        /*return consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());*/
    }

    public Consultation findById(Long id){
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No consultation with id: " + id));
    }

    /*public ConsultationDTO create(ConsultationRegisterDTO consultation){
        Patient patient = patientService.findByName(consultation.getPatientName());
        User doctor = userService.findDoctorByName(consultation.getDoctorName());

        DateTimeFormatter formatter = Constants.getDateTimeFormat();
        LocalDateTime formatted = LocalDateTime.parse(consultation.getDate(), formatter);

        if (!consultationRepository.existsByDoctor_IdAndDate(doctor.getId(), formatted))
            return consultationMapper.toDto(consultationRepository.save(consultationMapper.fromDto(
                ConsultationDTO.builder()
                        .date(formatted)
                        .patient(patient)
                        .doctor(doctor)
                        .author(consultation.getAuthor())
                        .details(consultation.getDetails())
                        .build())));

        return null;
    }*/

    public void create(ConsultationRegisterDTO consultation){
        Patient patient = patientService.findByName(consultation.getPatientName());
        User doctor = userService.findDoctorByName(consultation.getDoctorName());

        DateTimeFormatter formatter = Constants.getDateTimeFormat();
        LocalDateTime formatted = LocalDateTime.parse(consultation.getDate(), formatter);

        if (!consultationRepository.existsByDoctor_IdAndDate(doctor.getId(), formatted))
            consultationRepository.save(Consultation.builder()
                    .id(consultation.getId())
                    .date(formatted)
                    .author(consultation.getAuthor())
                    .doctor(doctor)
                    .patient(patient)
                    .details(consultation.getDetails())
                    .build());
    }

    public void delete(Long id){
        Consultation consultation = findById(id);
        consultationRepository.delete(consultation);
    }

    public void edit(ConsultationRegisterDTO consultationDTO){
        DateTimeFormatter formatter = Constants.getDateTimeFormat();
        LocalDateTime formatted = LocalDateTime.parse(consultationDTO.getDate(), formatter);

        Consultation consultation = findById(consultationDTO.getId());
        consultation.setDate(formatted);
        consultation.setDetails(consultationDTO.getDetails());
        consultation.setAuthor(consultationDTO.getAuthor());

        consultationRepository.save(consultation);
    }

}
