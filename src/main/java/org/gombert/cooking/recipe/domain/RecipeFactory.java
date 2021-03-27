package org.gombert.cooking.recipe.domain;


import org.gombert.cooking.recipe.domain.exception.RecipeCreationException;
import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.gombert.cooking.recipe.domain.port.out.GetRecipePort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecipeFactory
{
    public static Recipe createRecipe(final TenantId tenantId, final CreateRecipeUseCase.CreateRecipeCommand createRecipeCommand) throws RecipeCreationException {
        if (createRecipeCommand == null)
            throw new RecipeCreationException("createRecipeCommand is null");

        final var clientGeneratedRecipeId = createRecipeCommand.getClientGeneratedId();
        final var recipeInfo = new Info(createRecipeCommand.getName(), createRecipeCommand.getDescription(), createRecipeCommand.getComment());
        final var methodSteps = convertMethodStepsFromDto(createRecipeCommand.getMethodSteps());
        final var recipeIngredients = convertRecipeIngredientsFromDtoOrEmptyList(createRecipeCommand.getRecipeIngredients());

        final var recipe = new Recipe(tenantId, clientGeneratedRecipeId, recipeInfo, recipeIngredients, methodSteps);
        return recipe;
    }

    public static Recipe reconstitueRecipe(final GetRecipePort.ReconstitueRecipeCommand reconstitueRecipeCommand) throws RecipeCreationException {
        if (reconstitueRecipeCommand == null)
            throw new RecipeCreationException("createRecipeCommand is null");

        final var recipeInfo = new Info(reconstitueRecipeCommand.getName(), reconstitueRecipeCommand.getDescription(), reconstitueRecipeCommand.getComment());
        final var methodSteps = convertMethodStepsFromDto(reconstitueRecipeCommand.getMethodSteps());
        final var recipeIngredients = convertRecipeIngredientsFromRepositoryOrEmptyList(reconstitueRecipeCommand.getRecipeIngredients());

        final var recipe = new Recipe(reconstitueRecipeCommand.getTenantId(), reconstitueRecipeCommand.getId(), recipeInfo, recipeIngredients, methodSteps);
        return recipe;
    }

    private static List<MethodStep> convertMethodStepsFromDto(final List<String> methodSteps)
    {
        return notNullStreamOfCollection(methodSteps).map(x -> new MethodStep(x)).collect(Collectors.toList());
    }

    static private Stream<String> notNullStreamOfCollection(Collection<String> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElseGet(Stream::empty);
    }

    private static List<RecipeIngredient> convertRecipeIngredientsFromDtoOrEmptyList(final List<CreateRecipeUseCase.CreateRecipeIngredientCommand> createRecipeIngredientCommands) throws RecipeCreationException
    {
        final var recipeIngredients = new ArrayList<RecipeIngredient>();
        if (createRecipeIngredientCommands == null)
            return recipeIngredients;

        for (final var recipeIngredientCommand : createRecipeIngredientCommands)
        {
            final var recipeIngredient = new RecipeIngredient(recipeIngredientCommand.getIngredient(), recipeIngredientCommand.getAmount(), recipeIngredientCommand.getUnit());
            recipeIngredients.add(recipeIngredient);
        }
        return recipeIngredients;
    }

    private static List<RecipeIngredient> convertRecipeIngredientsFromRepositoryOrEmptyList(final List<GetRecipePort.ReconstitueRecipeIngredientCommand> reconstitueRecipeIngredientCommands) throws RecipeCreationException
    {
        final var reconstitutedRecipeIngredients = new ArrayList<RecipeIngredient>();
        if (reconstitueRecipeIngredientCommands == null)
            return reconstitutedRecipeIngredients;

        for (final var recipeIngredient : reconstitueRecipeIngredientCommands)
        {
            final var reconstitutedRecipeIngredient = new RecipeIngredient(recipeIngredient.getIngredient(), recipeIngredient.getAmount(), recipeIngredient.getUnit());
            reconstitutedRecipeIngredients.add(reconstitutedRecipeIngredient);
        }
        return reconstitutedRecipeIngredients;
    }
}
