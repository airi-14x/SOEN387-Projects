/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.sql.Blob;

/**
 *
 * @author Airi
 */
public class CoverImage {

    private String mime_type;
    private Blob image_data;

    public CoverImage(String mime_type, Blob image_data) {
        this.mime_type = mime_type;
        this.image_data = image_data;
    }

    @Override
    public String toString() {
        return "CoverImage{" + "mime_type=" + mime_type + ", image_data=" + image_data + '}';
    }

}
