package com.jpmorgan.application;

import com.jpmorgan.application.context.Context;
import com.jpmorgan.model.stock.StockSymbol;
import com.jpmorgan.stock_market.StockMarket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Main Application class (runner)
 */
public class Application {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final StockMarket stockMarket;

    public Application() {
        stockMarket = Context.getInjector().getInstance(StockMarket.class);
    }

    public static void main(String... args) {
        new Application().run(args);
    }

    public void run(String... args) {
        logger.info("Application starting...");
        Scanner in = new Scanner(System.in);
        logger.info("Application started");

        boolean repeat = true;

        while (repeat) {
            logger.info("Please enter Stock Symbol");
            StockSymbol stockSymbol = StockSymbol.valueOf(in.next());

            logger.info("Please enter Price");
            BigDecimal price = in.nextBigDecimal();

            stockMarket.process(stockSymbol, price);

            logger.info("One more Stock? (Y/N)");
            String answer = in.next();

            if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("NO")) {
                repeat = false;
            }
        }

        logger.info("Application stopping...");
        in.close();
        logger.info("Application stopped");
    }


}
