package org.resala.core.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class CollageEntity extends SimpleEntityParent {

    @ManyToOne
    private UniversityEntitiy university;
}
