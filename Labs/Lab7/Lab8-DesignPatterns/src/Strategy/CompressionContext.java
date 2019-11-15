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
public class CompressionContext {

    private CompressionStrategy strategy;

    public void setCompressionStrategy(CompressionStrategy strategy) {
        this.strategy = strategy;
    }

    public void createArchive(ArrayList<File> files) {
        strategy.compressFiles(files);
    }

}
