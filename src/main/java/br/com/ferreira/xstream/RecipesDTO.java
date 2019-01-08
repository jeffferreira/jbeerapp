package br.com.ferreira.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("RECIPES")
public class RecipesDTO {

    @XStreamAlias("RECIPE")
    @XStreamAsAttribute
    private RecipeDTO recipe;
}
