package com.ghilios;

import com.google.common.collect.ImmutableList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

/**
 * Created by ghilios on 1/14/16.
 */
@Component
public class SampleRepository implements Repository<SampleModel, String> {

    public Page<SampleModel> findAll(final Pageable pageable) {
        final ImmutableList<SampleModel> results = ImmutableList.of(
                new SampleModel("a"),
                new SampleModel("b"),
                new SampleModel("c"),
                new SampleModel("d"),
                new SampleModel("e")
        );
        return new PageImpl<>(results, pageable, results.size());
    }
}
