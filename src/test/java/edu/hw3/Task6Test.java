package edu.hw3;

import edu.hw3.task6.MyStockMarket;
import edu.hw3.task6.StockMarket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {
    @Test
    @DisplayName("Работа с \"MyStockMarket\"")
    void myStockMarketing() {
        MyStockMarket store = new MyStockMarket(
            new StockMarket.Stock(250, "Ubuntu"), new StockMarket.Stock(290, "Iphone"),
            new StockMarket.Stock(180, "Yandex"), new StockMarket.Stock(450, "Samsung"),
            new StockMarket.Stock(400, "Tinkoff"), new StockMarket.Stock(103, "Airbus")
        );

        assertEquals(new StockMarket.Stock(450, "Samsung"), store.mostValuableStock());

        StockMarket.Stock doritosStock = new StockMarket.Stock(130, "Doritos");
        StockMarket.Stock microsoftStock = new StockMarket.Stock(850, "Microsoft");
        StockMarket.Stock vkStock = new StockMarket.Stock(660, "vk");
        store.add(microsoftStock);
        store.add(doritosStock);
        store.add(vkStock);

        assertEquals(microsoftStock, store.mostValuableStock());

        store.remove(doritosStock);

        assertEquals(microsoftStock, store.mostValuableStock());

        store.remove(doritosStock);

        assertEquals(microsoftStock, store.mostValuableStock());

        store.remove(microsoftStock);

        assertEquals(vkStock, store.mostValuableStock());
    }
}
