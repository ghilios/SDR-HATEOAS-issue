package com.ghilios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ghilios on 1/14/16.
 */
@BasePathAwareController
@ExposesResourceFor(SampleModel.class)
public class SampleController implements ResourceProcessor<RepositoryLinksResource> {

    @Autowired
    private SampleRepository repository;

    @Autowired
    private SampleResourceAssembler resourceAssembler;

    @Autowired
    private PagedResourcesAssembler assembler;

    @ResponseBody
    @RequestMapping(
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public PagedResources<SampleModel> findAll(
            final Pageable pageable) {
        final Page<SampleModel> analystSummaries = repository.findAll(pageable);
        return assembler.toResource(analystSummaries, resourceAssembler);
    }

    @ResponseBody
    @RequestMapping(
            value = "/someOtherMethod",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public PagedResources<SampleModel> someOtherMethod(
            final Pageable pageable) {
        final Page<SampleModel> analystSummaries = repository.findAll(pageable);
        return assembler.toResource(analystSummaries, resourceAssembler);
    }

    @Override
    public RepositoryLinksResource process(final RepositoryLinksResource resource) {
        resource.add(ControllerLinkBuilder.linkTo(SampleController.class).withRel("samples"));
        return resource;
    }
}
