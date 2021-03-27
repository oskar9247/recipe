package org.gombert.cooking.recipe.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@ToString(callSuper = false)
@EqualsAndHashCode
public class RecipeId extends BaseEntity
{
    @Getter(AccessLevel.PUBLIC)
    final private UUID id;

    public RecipeId(final String tenantId)
    {
        this(UUID.fromString(tenantId));
    }

    public RecipeId(final UUID id)
    {
        throwExecptionIfNull(id, "recipeId");

        this.id = id;
    }
}
