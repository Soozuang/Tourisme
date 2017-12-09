package com.android.soozuang.ohmytrip.Model;

import java.util.ArrayList;
/**
 * Created by soozuang on 11/23/2017.
 */

public class SectionProvince {
    private String headerTitle;
    private ArrayList<Province> allPro;

    public SectionProvince(){
        allPro = new ArrayList<Province>();
    }

    public SectionProvince(String headerTitle, ArrayList<Province> allPro) {
        this.headerTitle = headerTitle;
        this.allPro = allPro;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<Province> getAllPro() {
        return allPro;
    }

    public void setAllPro(ArrayList<Province> allPro) {
        this.allPro = allPro;
    }
}
