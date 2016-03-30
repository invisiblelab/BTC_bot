package com.invisiblelab.btcbot.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("parser")
public interface ParserService {
	
	String getActualBtcPrices() throws Exception;

}
