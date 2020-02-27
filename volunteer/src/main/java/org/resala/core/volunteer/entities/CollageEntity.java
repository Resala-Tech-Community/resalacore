package org.resala.core.volunteer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
public class CollageEntity extends SimpleEntityParent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UniversityEntitiy university;
    @ManyToMany(mappedBy = "collageEntity")
    private Collection<UniversityEntitiy> universityEntitiys;

    public Collection<UniversityEntitiy> getUniversityEntitiys() {
        return universityEntitiys;
    }

    public void setUniversityEntitiys(Collection<UniversityEntitiy> universityEntitiys) {
        this.universityEntitiys = universityEntitiys;
    }

    @Override
    public void setId(Long networkTypeId) {

    }
}
