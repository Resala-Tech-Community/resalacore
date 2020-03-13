package org.resala.core.volunteer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.resala.core.domain.VolunteerEntity;
import org.resala.core.volunteer.Dto.EditVolunteerDto;
import org.resala.core.volunteer.Dto.RegistrationParamsDTO;
import org.resala.core.volunteer.Dto.RegistrationPostDTO;

@Mapper
public interface VolunteerMapper {

    VolunteerMapper instance = Mappers.getMapper(VolunteerMapper.class);

    @Mapping(target = "code", qualifiedByName = "codeMapper")
    VolunteerEntity toVolunteerEntitiy(RegistrationPostDTO dto);

    @Mapping(source = "phone", target = "phoneNumber")
    VolunteerEntity toVolunteerEntity(RegistrationParamsDTO dto);

    void updateVolunteerFromDto(VolunteerEntity entity, @MappingTarget EditVolunteerDto dto);

    @Named("codeMapper")
    static String codeMapper(RegistrationPostDTO dto) {
        return  dto.getGender().firstLatter() + dto.getName().substring(0, 1)
                + dto.getPhoneNumber().substring(dto.getPhoneNumber().length() - 2, dto.getPhoneNumber().length() - 1);
    }

}
