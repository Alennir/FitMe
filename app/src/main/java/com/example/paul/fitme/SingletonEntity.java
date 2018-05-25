package com.example.paul.fitme;

import java.util.ArrayList;
import java.util.List;

class SingletonEntity {


    private static SingletonEntity ourInstance = new SingletonEntity();

    private static UserEntity userEntity = new UserEntity("","",0,0,0);

    private static List<RecipeEntity> recipeEntity = new ArrayList<>();

    public static SingletonEntity getInstance() {
        if(ourInstance == null){
            ourInstance = new SingletonEntity();
        }

        return ourInstance;
    }

    public UserEntity getUser(){
        return userEntity;
    }

    public void addUser(UserEntity userEntity){
        this.userEntity = userEntity;
    }

    public List<RecipeEntity> getRecipeEntity() { return recipeEntity;}

    public void addRecipe(RecipeEntity recipeEntity) { this.recipeEntity.add(recipeEntity);}

    public void addAllRecipe(List<RecipeEntity> recipeList){ this.recipeEntity = recipeList;}

    public void resetAllRecipe() {
        this.recipeEntity = new ArrayList<>();
    }
}
