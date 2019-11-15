/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Airi
 */
// Concrete Subject
public class StockMarket extends AbstractStockMarket {

    private Map<String, Double> stockList = new HashMap<>();

    @Override
    public void addStock(String stockSymbol, Double price) {
        stockList.put(stockSymbol, price);
    }

    @Override
    public void update(String stockSymbol, Double price) {
        stockList.put(stockSymbol, price);
        notifyStockBroker(stockList);
    }

}
