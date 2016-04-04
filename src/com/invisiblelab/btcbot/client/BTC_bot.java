package com.invisiblelab.btcbot.client;

import com.invisiblelab.btcbot.client.ParserServiceAsync;
import com.invisiblelab.btcbot.client.ParserService;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import java.util.Map;
import java.util.HashMap;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class BTC_bot implements EntryPoint{

	private ParserServiceAsync parserService = GWT.create(ParserService.class);

	public void onModuleLoad() {

		final ContentPanel panel = new ContentPanel();
		panel.setHeading("BTC prices");
		panel.setWidget(new HTML("Stocks are loading."));

		Timer timer = new Timer() {
			@Override
			public void run() {
				serviceCall();

				BasicGrid g = new BasicGrid(actualPrices);
				Grid grid = g.instance;

				panel.setWidget(grid);
				panel.forceLayout();
			}
		};

		timer.scheduleRepeating(5000);

		Viewport viewPort = new Viewport();
		viewPort.setWidget(panel);

		RootPanel.get().add(viewPort);	
	}

	public Map<String, String> actualPrices;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void serviceCall(){

		if (parserService == null){
			parserService = GWT.create(ParserService.class);
		}

		AsyncCallback callback = new AsyncCallback<HashMap<String, String>>() {

			@Override
			public void onFailure(Throwable caught) {

				GWT.log(caught.getMessage());

			}

			@Override
			public void onSuccess(HashMap<String, String> result) {	
				actualPrices = result;

				Info.display("Notification:", "Actual BTC prices loaded!");
			}		
		};

		try {

			parserService.getActualBtcPrices(callback);

		} catch (Exception e){
			System.out.println("Exception thrown.");
		}
	}
}
