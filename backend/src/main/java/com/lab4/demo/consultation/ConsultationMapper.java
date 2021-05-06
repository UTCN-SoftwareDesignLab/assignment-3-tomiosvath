package com.lab4.demo.consultation;

import com.lab4.demo.consultation.model.Consultation;
import com.lab4.demo.consultation.model.dto.ConsultationDTO;
import com.lab4.demo.consultation.model.dto.ConsultationRegisterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    ConsultationDTO toDto(Consultation consultation);

    Consultation fromDto(ConsultationDTO consultationDTO);

}
