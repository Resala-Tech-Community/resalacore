package org.resala.core.volunteer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@Getter
@Setter
public abstract class UniversityEntitiy extends SimpleEntityParent {

    public UniversityEntitiy(){

    }

    public UniversityEntitiy(Long id, String name, List<Long> collage){
        this.id = id;
        this.name = name;

        if(collage != null)
            for(Long collageId : collage)
            {
                CollageEntity collageEntity = new CollageEntity();
                collageEntity.setId(collageId);
            }
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Please enter Name")
    private String name;

    @OneToMany(mappedBy = "university")
    private List<CollageEntity> collage;



}