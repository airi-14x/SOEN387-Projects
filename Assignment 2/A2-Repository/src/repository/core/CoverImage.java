/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.sql.Blob;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public class CoverImage {

    private String mimeType;
    private Blob image;
    private String imagePath;

    public CoverImage(String mimeType, Blob image) {
        this.mimeType = mimeType;
        this.image = image;
    }

    public CoverImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "CoverImage{mimeType=" + mimeType + "}";
    }
}
