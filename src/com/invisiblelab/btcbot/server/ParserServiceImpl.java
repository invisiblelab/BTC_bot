package com.invisiblelab.btcbot.server;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.invisiblelab.btcbot.client.ParserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings({ "serial", "unused" })
public class ParserServiceImpl extends RemoteServiceServlet implements ParserService {

	@Override
	public Map<String, String> getActualBtcPrices() throws Exception {
		Document doc = Jsoup.connect("http://bitcoinwisdom.com/").get();

		String bitstamp = doc.select("#market_bitstampbtcusd").text();
		String btce = doc.select("#market_btcebtcusd").text();
		String bitfinex = doc.select("#market_bitfinexbtcusd").text();

		Map<String, String> prices = new HashMap<String, String>();

		prices.put("bitstamp", bitstamp);
		prices.put("btce", btce);
		prices.put("bitfinex", bitfinex);

		return prices;
	}
}
