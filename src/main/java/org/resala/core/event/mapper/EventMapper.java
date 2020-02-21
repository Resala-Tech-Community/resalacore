package org.resala.core.event.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.resala.core.domain.BranchEventEntity;
import org.resala.core.event.dto.EventReqParamDto;
import org.resala.core.event.dto.EventResponseDTO;

import java.sql.Timestamp;

@Mapper

public interface EventMapper {
    EventMapper instance = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "branchId", source = "branchEntity.id")
    @Mapping(target = "eventId", source = "eventEntity.id")
    @Mapping(target = "eventDate", source = "eventEntity.eventDate")
    @Mapping(target = "lastTimeToReg", source = "eventEntity.lastTimeToReg")
    @Mapping(target = "name", source = "eventEntity.name")
    @Mapping(target = "startTime", source = "eventEntity.startTime")
    EventResponseDTO toEventResponseDTO(BranchEventEntity entity);

    @Mapping(target = "branchEntity.id", source = "branch")
    @Mapping(target = "eventEntity.eventDate", source = "date")
    @Mapping(target = "eventEntity.id", source = "id")
    BranchEventEntity toBranchEventEntity(EventReqParamDto dto);

}
