package org.resala.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class SimpleEntityParent implements Serializable {
    @Id
    private Long id;
    private String name;
}

