package com.invisiblelab.btcbot.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ParserServiceAsync {
	@SuppressWarnings("rawtypes")
	String getActualBtcPrices(AsyncCallback callback);
}
