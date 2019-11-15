/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Airi
 */
public class CompressionClient {

    public static void main(String[] args) {
        CompressionContext ctx = new CompressionContext();
        ctx.setCompressionStrategy(new ZipCompressionStrategy());
        ArrayList<File> fileList = new ArrayList<File>();
        ctx.createArchive(fileList);

        ArrayList<File> file2List = new ArrayList<File>();
        ctx.setCompressionStrategy(new RarCompressionStrategy());
        ctx.createArchive(file2List);
    }
}
