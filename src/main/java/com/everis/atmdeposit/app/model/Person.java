package com.everis.atmdeposit.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Model Person.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    /**
     * field id.
     */
    private String id;
    /**
     * field document.
     */
    @NotEmpty(message = "'document' No debe ser vacio!")
    @Size(min = 8, max = 8, message = "'document' debe tener 8 caracteres")
    private String document;
    /**
     * flied fingerprint.
     */
    private Boolean fingerprint;
    /**
     * field blacklist.
     */
    private Boolean blacklist;
}
