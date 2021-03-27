package org.gombert.cooking.recipe.domain.exception;

public class RecipeRepositoryNotFound extends Exception
{
    public RecipeRepositoryNotFound(final String message)
    {
        super(message);
    }
}
