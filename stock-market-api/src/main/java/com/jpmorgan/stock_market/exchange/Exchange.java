package com.jpmorgan.stock_market.exchange;

import com.jpmorgan.model.Exchangeable;

import java.io.Serializable;

/**
 * Base interface for all Exchanges
 */
public interface Exchange<K extends Serializable, V extends Exchangeable> {
    V get(K key);
}
