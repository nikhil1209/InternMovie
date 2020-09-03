package com.nikhil.internmovie.Util;

import java.util.ArrayList;
import java.util.List;

public class Favorite {
    public String fav;

    public Favorite() {
        fav="";
    }
    public void addElement(String s){
        fav=fav+s+" \n  ";
    }
    public String getElements(){
        return fav;
    }




}
