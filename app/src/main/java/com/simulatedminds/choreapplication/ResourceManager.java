package com.simulatedminds.choreapplication;

import java.util.ArrayList;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

//Simple Recipe Management Singleton
public class ResourceManager {

    public static final String intentIndexTitle = "selectedResource";
    private static ResourceManager instance = null;
    private ArrayList<Resource> resourceList;

    protected ResourceManager() {
        //This Exists to defeat instantiation

        String[] values = new String[]{
                "Soap", "Water", "Vacuum", "Broom", "Sponge", "Bucket"
        };

        String[] descriptions = new String[]{
                "Soapy", "Wet", "Suck", "Sweep", "Squeeze", "What?"
        };

        resourceList = new ArrayList<>();

        for (int i = 0; i < values.length ; i++) {
            Resource newResource = new Resource(values[i],descriptions[i]);
            resourceList.add(newResource);
        }
    }

    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    public ArrayList<Resource> getResourceList() {
        return resourceList;
    }

    public Resource getResourceAt(int index) {
        return resourceList.get(index);
    }
}