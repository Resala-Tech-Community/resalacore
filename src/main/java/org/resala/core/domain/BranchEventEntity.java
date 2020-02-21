package org.resala.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.resala.core.domain.key.BranchEventKey;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@IdClass(BranchEventKey.class)
public class BranchEventEntity implements Serializable {

    @ManyToOne()
    @Id
    private BranchEntity branchEntity;
    @ManyToOne
    @Id
    private EventEntity eventEntity;
}
