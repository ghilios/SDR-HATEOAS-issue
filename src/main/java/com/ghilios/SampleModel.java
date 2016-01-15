package com.ghilios;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by ghilios on 1/14/16.
 */
@JsonTypeName("Sample")
public class SampleModel {
    private String name;

    public SampleModel(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
