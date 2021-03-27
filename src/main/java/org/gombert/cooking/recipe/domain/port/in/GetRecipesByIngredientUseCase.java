package org.gombert.cooking.recipe.domain.port.in;


import org.gombert.cooking.recipe.domain.RecipeId;
import org.gombert.cooking.recipe.domain.TenantId;

import java.util.Collection;

public interface GetRecipesByIngredientUseCase
{
    Collection<RecipeId> getRecipesByIngredient(final TenantId tenantId, String ingredient);
}
