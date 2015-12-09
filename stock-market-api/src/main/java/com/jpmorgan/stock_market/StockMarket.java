package com.jpmorgan.stock_market;

import com.jpmorgan.model.stock.StockSymbol;
import com.jpmorgan.model.trade.Trade;

import java.math.BigDecimal;

/**
 * Stock Market interface
 */
public interface StockMarket {
    Trade process(StockSymbol stockSymbol, BigDecimal price);
}
