package org.gombert.cooking.recipe.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.gombert.cooking.recipe.domain.exception.RecipeCreationException;


@Getter(AccessLevel.MODULE)
@EqualsAndHashCode
class MethodStep extends BaseEntity
{
    final private String description;

    public MethodStep(final String description) throws RecipeCreationException
    {
        throwExecptionIfNull(description, "MethodStep description");
        throwExceptionIfEmpty(description, "MethodStep description");

        this.description = description;
    }

}
