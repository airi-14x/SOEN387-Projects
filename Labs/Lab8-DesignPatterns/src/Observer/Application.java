/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

/**
 *
 * @author Airi
 */
public class Application {

    public static void main(String[] args) {
        AbstractStockMarket market = new StockMarket();
        StockBroker buyer = new StockBuyer();
        StockViewer viewer = new StockViewer();

        market.addStockBroker(buyer);
        market.addStockBroker(viewer);
        market.addStock("ORC", 12.23);
        market.addStock("MSC", 45.78);

        System.out.println("===== Updating ORC =====");
        market.update("ORC", 12.34);
        System.out.println("===== Updating MSC =====");
        market.update("MSC", 44.68);

    }
}
