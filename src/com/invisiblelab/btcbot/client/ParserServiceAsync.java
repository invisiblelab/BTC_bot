package com.invisiblelab.btcbot.client;

import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ParserServiceAsync {

	void getActualBtcPrices(AsyncCallback<Map<String, String>> callback);

}
