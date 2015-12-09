package com.jpmorgan.stock_market.repository.trade;

import com.jpmorgan.model.trade.Trade;
import com.jpmorgan.stock_market.repository.Repository;

import java.util.List;

/**
 * Trade Repository interface
 */
public interface TradeRepository extends Repository<Long, Trade> {
    List<Trade> lastMinutes(int minutes);
}
