package org.resala.core.volunteer.mapper;

import javax.annotation.Generated;
import org.resala.core.volunteer.Dto.EditVolunteerDto;
import org.resala.core.volunteer.Dto.RegistrationPostDTO;
import org.resala.core.volunteer.entities.VolunteerEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-18T23:12:16+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
public class VolunteerMapperImpl implements VolunteerMapper {

    @Override
    public VolunteerEntity toVolunteerEntitiy(RegistrationPostDTO dto) {
        if ( dto == null ) {
            return null;
        }

        VolunteerEntity volunteerEntity = new VolunteerEntity();

        volunteerEntity.setName( dto.getName() );
        volunteerEntity.setEMail( dto.getEMail() );
        volunteerEntity.setGender( dto.getGender() );
        volunteerEntity.setPhoneNumber( dto.getPhoneNumber() );

        return volunteerEntity;
    }

    @Override
    public void updateVolunteerFromDto(VolunteerEntity entity, EditVolunteerDto dto) {
        if ( entity == null ) {
            return;
        }

        dto.name = entity.getName();
        dto.joinDate = entity.getJoinDate();
        dto.notes = entity.getNotes();
        dto.identificationNumber = entity.getIdentificationNumber();
        dto.miniCamp = entity.getMiniCamp();
        dto.tshirt = entity.getTshirt();
        dto.gender = entity.getGender();
        dto.phoneNumber = entity.getPhoneNumber();
        dto.birthDate = entity.getBirthDate();
    }
}
