/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public class CoverImage {

    private String mimeType;
    byte[] imageData;


    public CoverImage(String mimeType, Blob image) throws Exception {
        this(mimeType, image.getBinaryStream());
    }
    
    public CoverImage(String mimeType, InputStream input) throws Exception {
        this(mimeType, FileUtils.toByteArray(input));
    }
    
    public CoverImage(String mimeType, byte[] image) {
        this.mimeType = mimeType;
        this.imageData = image;
    }

    private CoverImage() {
    }
    
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImage(Blob image) throws Exception {
        this.imageData = FileUtils.toByteArray(image.getBinaryStream());
    }

    public void getImageData(OutputStream os) throws Exception {
        os.write(imageData);
    }
    
    @Override
    public String toString() {
        return "CoverImage{" + "mimeType=" + mimeType + "}";
    }

    private static class FileUtils
    {
        public static byte[] toByteArray(InputStream is) throws Exception
        {
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            byte[] buf = new byte[1000];
            for(;;)
            {
                int l = is.read(buf, 0, buf.length);
                if(l <=0 ) break;
                ba.write(buf, 0, l);
            }
            return ba.toByteArray();
        }
    }
}
