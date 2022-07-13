package com.example.cocktailme;

public class FeatureFlag {
    public boolean useNewAlgorithm;
    public void reticulateSplines(){
        useNewAlgorithm = false;
        // useNewAlgorithm = true; // UNCOMMENT IF YOU ARE WORKING ON THE NEW SR ALGORITHM

        if(useNewAlgorithm){
            return enhancedSplineReticulation();
        }else{
            return oldFashionedSplineReticulation();
        }
    }

    public void String oldFashionedSplineReticulation(){

    }

    public void String enhancedSplineReticulation(){
        // TODO: implement better SR algorithm
    }
}
