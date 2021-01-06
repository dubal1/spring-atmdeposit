package com.everis.atmdeposit.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
