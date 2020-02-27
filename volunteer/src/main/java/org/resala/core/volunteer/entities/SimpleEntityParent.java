package org.resala.core.volunteer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public  abstract class SimpleEntityParent {
    @Id
    private Long id;
    private String name;

    public abstract void setId(Long networkTypeId);
}

