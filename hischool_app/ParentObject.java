package com.mobile.sunrin.hischool;

import java.util.List;

/**
 * Created by parkjaemin on 2015. 7. 14..
 */
public class ParentObject {
    public String year;
    public String month;

    //Header and footer don’t need to be a String,
    //they can be a class or whatever depend on your need.
    public String textToHeader;

    //Parent should have a list of their child
    public List<ChildObject> childObjects;

    public ParentObject(String year, String month, String textToHeader, List<ChildObject> childObjects)
    {
        this.year= year;
        this.month = month;
        this.textToHeader = textToHeader;
        this.childObjects = childObjects;
    }
}