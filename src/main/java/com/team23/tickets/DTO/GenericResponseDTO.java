package com.team23.tickets.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class GenericResponseDTO implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;

    public String message;
    public Object objectResponse;
    public int statusCode;
}
