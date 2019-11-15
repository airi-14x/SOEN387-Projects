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
public class ZipCompressionStrategy implements CompressionStrategy {

    @Override
    public void compressFiles(ArrayList<File> files) {
        System.out.println("In ZIP Compression Strategy");
    }

}
