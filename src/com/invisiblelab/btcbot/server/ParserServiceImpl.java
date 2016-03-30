package com.invisiblelab.btcbot.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.invisiblelab.btcbot.client.ParserService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ParserServiceImpl extends RemoteServiceServlet implements ParserService {

	@Override
	public String getActualBtcPrices() throws Exception {
		Document doc = Jsoup.connect("http://bitcoinwisdom.com/").get();
		Elements bitstamp = doc.select("#market_bitstampbtcusd span");
		String html = bitstamp.outerHtml();
		
		return html;
	}

}
