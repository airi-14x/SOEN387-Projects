/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;


/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public class CoverImage {

    private String mimeType;
    private Blob image;
    private String imagePath;
    private InputStream input;
    byte[] imageData;


    public CoverImage(String mimeType, Blob image) {
        this.mimeType = mimeType;
        this.image = image;
    }
    
    public CoverImage(String mimeType, InputStream input) {
        this.mimeType = mimeType;
        this.input = input;
    }
    
    public CoverImage(String mimeType, byte[] image) {
        this.mimeType = mimeType;
        this.imageData = image;
    }

    public CoverImage() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    public InputStream getImageData() {
        return input;
    }
    
    public byte[] getImageBytes() {
        return imageData;
    }
    
    public void setImageData(InputStream input) {
        this.input = input;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public String getBase64Image() {
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        return base64Image;
    }
    
    @Override
    public String toString() {
        return "CoverImage{" + "mimeType=" + mimeType + ", image=" + image + ", imagePath=" + imagePath + '}';
    }

}
