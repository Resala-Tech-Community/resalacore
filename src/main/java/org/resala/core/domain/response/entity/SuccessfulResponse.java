package org.resala.core.domain.response.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SuccessfulResponse<T> implements Serializable {

    private T body;
    private String message;
}
