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

    public CoverImage(String mimeType, Blob image) {
        this.mimeType = mimeType;
        this.image = image;
    }

    public CoverImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "CoverImage{mimeType=" + mimeType + "}";
    }
}
