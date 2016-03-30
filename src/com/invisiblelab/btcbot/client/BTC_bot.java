package com.invisiblelab.btcbot.client;

import com.invisiblelab.btcbot.shared.FieldVerifier;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BTC_bot implements EntryPoint {
	
	private ParserServiceAsync parserService = GWT.create(ParserService.class);
	
	public void onModuleLoad() {
		
		String label = serviceCall();
		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
    	vlc.add(createLabel(label), new VerticalLayoutData(1, 0.25, new Margins(10)));
    	
    	ContentPanel panel = new ContentPanel();
    	panel.setHeading("BTC prices");
    	panel.add(vlc);
    	
    	 Viewport viewPort = new Viewport();
         viewPort.setWidget(panel);
         
    	 RootPanel.get().add(viewPort);	
	}
	
	@SuppressWarnings("rawtypes")
	private String serviceCall(){
		
		if (parserService == null){
			parserService = GWT.create(ParserService.class);			
		}
		
		 AsyncCallback callback = new AsyncCallback() {
		      public void onFailure(Throwable caught) {
		        // TODO: Do something with errors.
		      }

		      public void onSuccess(Object rslt) {
		        
		      }
		    };
		    
		   String rslt = parserService.getActualBtcPrices(callback);
		   
		   return rslt;
	}
	
    private Label createLabel(String text){
    	Label label = new Label(text);
    	label.getElement().getStyle().setProperty("whitespace", "nowrap");
    	label.addStyleName("pad-text gray-bg");
    	
    	return label;
    }
}
