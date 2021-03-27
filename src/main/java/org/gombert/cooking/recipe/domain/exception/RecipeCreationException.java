package org.gombert.cooking.recipe.domain.exception;

public class RecipeCreationException extends RuntimeException
{
    public RecipeCreationException(final String message)
    {
        super(message);
    }
}
