package com.team23.tickets.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericRequestDTO implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;

    public Object request;
}
