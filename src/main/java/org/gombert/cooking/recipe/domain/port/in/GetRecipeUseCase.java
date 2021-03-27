package org.gombert.cooking.recipe.domain.port.in;


import org.gombert.cooking.recipe.domain.Recipe;
import org.gombert.cooking.recipe.domain.RecipeId;
import org.gombert.cooking.recipe.domain.TenantId;
import org.gombert.cooking.recipe.domain.exception.RecipeNotFoundException;

public interface GetRecipeUseCase
{
    public Recipe getRecipe(final TenantId tenantId, final RecipeId recipeId) throws RecipeNotFoundException;
}
