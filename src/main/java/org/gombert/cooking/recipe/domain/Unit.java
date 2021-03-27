package org.gombert.cooking.recipe.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.MODULE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
class Unit extends org.gombert.cooking.recipe.domain.BaseEntity
{
    final private String name;

    public Unit(final String name)
    {
        throwExecptionIfNull(name, "unit");
        throwExceptionIfEmpty(name, "unit");

        this.name = name;
    }
}
