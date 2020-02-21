package org.resala.core.domain.key;

import lombok.Getter;
import lombok.Setter;
import org.resala.core.domain.BranchEntity;
import org.resala.core.domain.EventEntity;

import java.io.Serializable;

@Getter
@Setter
public class BranchEventKey implements Serializable {

    private BranchEntity branchEntity;
    private EventEntity eventEntity;

    BranchEventKey() {
        
    }
}

