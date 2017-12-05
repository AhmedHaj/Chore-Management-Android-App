package com.simulatedminds.choreapplication;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

//Simple Recipe Management Singleton
public class ResourceManager extends AppCompatActivity {

    public static final String intentIndexTitle = "selectedResource";
    private static ResourceManager instance;
    private static ArrayList<Resource> resourceList;

    protected ResourceManager() {
        //This Exists to defeat instantiation

        resourceList = new ArrayList<>();
    }

    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }


    public static void deleteResource(Resource resource){
        resourceList.remove(resource);
    }


    public static void addResource(Resource resource){
        resourceList.add(resource);
    }

    public static ArrayList<Resource> getResourceList() {
        return resourceList;
    }

    public static Resource getResourceAt(int index) {
        return resourceList.get(index);
    }
}