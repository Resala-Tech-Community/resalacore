package org.resala.core.event.services;

import org.resala.core.domain.BranchEntity;
import org.resala.core.domain.BranchEventEntity;
import org.resala.core.event.dto.EventReqParamDto;
import org.resala.core.event.dto.EventResponseDTO;
import org.resala.core.event.mapper.EventMapper;
import org.resala.core.event.repository.BranchEventRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchEventService {

    private final BranchEventRepository branchEventRepository;

    public BranchEventService(final BranchEventRepository branchEventRepository) {
        this.branchEventRepository = branchEventRepository;
    }

    public List<EventResponseDTO> findByParam(EventReqParamDto params) {
        Example<BranchEventEntity> example = Example.of(EventMapper.instance.toBranchEventEntity(params)
                , ExampleMatcher.matching());
        List<BranchEventEntity> result = branchEventRepository.findAll(example, Sort.by(Sort.Direction.DESC, "eventEntity.eventDate"));
        return getCollect(result);
    }

    private List<EventResponseDTO> getCollect(List<BranchEventEntity> result) {
        return result.stream().map(entity -> {
            return EventMapper.instance.toEventResponseDTO(entity);
        }).collect(Collectors.toList());
    }

}
