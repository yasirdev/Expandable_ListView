package com.yasir.android.expandablelistview;

/**
 * Created by YasirRajpoot on 03/09/2016.
 */
public class RowItem {
    String title;
    int id, image;

    public RowItem()
    {

    }

    public RowItem(int id, String title , int image)
    {
        this.id = id;
        this.title = title;
        this.image = image;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
