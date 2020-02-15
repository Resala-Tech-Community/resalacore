package org.resala.core.volunteer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class UniversitySpecializationEntity extends SimpleEntityParent {

    @ManyToOne
    private CollageEntity collage;
}
