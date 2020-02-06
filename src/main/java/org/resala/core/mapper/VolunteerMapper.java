package org.resala.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.resala.core.Dto.EditVolunteerDto;
import org.resala.core.entities.VolunteerEntity;

@Mapper
public interface VolunteerMapper {

    VolunteerMapper instance = Mappers.getMapper(VolunteerMapper.class);

    void updateVolunteerFromDto(VolunteerEntity entity, @MappingTarget  EditVolunteerDto dto);
}
