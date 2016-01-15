package com.ghilios;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by ghilios on 1/14/16.
 */
@Component
public class SampleResourceAssembler extends ResourceAssemblerSupport<SampleModel, SampleResource> {

    public SampleResourceAssembler() {
        super(SampleModel.class, SampleResource.class);
    }

    @Override
    public SampleResource toResource(SampleModel entity) {
        return new SampleResource(entity);
    }
}
