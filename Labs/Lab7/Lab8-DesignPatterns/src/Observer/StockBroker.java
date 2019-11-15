/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import java.util.Map;

/**
 *
 * @author Airi
 */
// Observer Interface
public interface StockBroker {

    void update(Map<String, Double> stockList);
}
