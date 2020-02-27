package org.resala.core.volunteer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.resala.core.volunteer.Dto.EditUniversityDto;
import org.resala.core.volunteer.entities.UniversityEntitiy;

@Mapper
public interface UniversityMapper {

    UniversityMapper instance = Mappers.getMapper(UniversityMapper.class);

    void updateUniversityMapper(UniversityEntitiy entity, @MappingTarget EditUniversityDto dto);
}
