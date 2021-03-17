package br.com.cep.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResponseError {
    private String message;
    private Set<String> errors = new HashSet<>();
}
