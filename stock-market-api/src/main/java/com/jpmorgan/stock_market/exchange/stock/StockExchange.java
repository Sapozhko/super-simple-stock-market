package com.jpmorgan.stock_market.exchange.stock;

import com.jpmorgan.model.stock.Stock;
import com.jpmorgan.model.stock.StockSymbol;
import com.jpmorgan.stock_market.exchange.Exchange;

/**
 * Stock Exchange interface
 */
public interface StockExchange extends Exchange<StockSymbol, Stock> {
}
