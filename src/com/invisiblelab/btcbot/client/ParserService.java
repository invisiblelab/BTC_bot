package com.invisiblelab.btcbot.client;

import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("parser")
public interface ParserService extends RemoteService{

	Map<String, String> getActualBtcPrices() throws Exception;

}

