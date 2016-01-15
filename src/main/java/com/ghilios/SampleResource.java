package com.ghilios;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by ghilios on 1/14/16.
 */
public class SampleResource extends ResourceSupport {
    private String name;

    public SampleResource(final SampleModel sample) {
        this.name = sample.getName();
    }

    public String getName() {
        return name;
    }
}
