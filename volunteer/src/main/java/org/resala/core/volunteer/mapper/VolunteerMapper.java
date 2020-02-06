package org.resala.core.volunteer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.resala.core.volunteer.Dto.EditVolunteerDto;
import org.resala.core.volunteer.entities.VolunteerEntity;

@Mapper
public interface VolunteerMapper {

    VolunteerMapper instance = Mappers.getMapper(VolunteerMapper.class);

    void updateVolunteerFromDto(VolunteerEntity entity, @MappingTarget EditVolunteerDto dto);
}
