package com.invisiblelab.btcbot.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

public class BasicGrid extends Composite {


	public class NameModel {
		private int id;
		private String name;
		private String price;
		private String change;

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public void setPrice(String price){
			this.price = price;
		}

		public String getPrice(){
			return price;
		}

		public void setChange(String change){
			this.change = change;
		}

		public String getChange(){
			return change;
		}
	}

	public Grid instance;

	// Property access definitions for the values in the NameModel
	public interface GridProperties extends PropertyAccess<NameModel> {
		ModelKeyProvider<NameModel> id();
		ValueProvider<NameModel, String> name();
		ValueProvider<NameModel, String> price();
		ValueProvider<NameModel, String> change();
	}

	// Setup the property access definitions for the values for the grid columns
	public static GridProperties gridProperties = GWT.create(GridProperties.class);

	public BasicGrid(Map<String, String> actualPrices) {
		// Setup the ListStore.
		ListStore<NameModel> listStore = new ListStore<NameModel>(gridProperties.id());

		NameModel stamp = new NameModel();
		NameModel e = new NameModel();
		NameModel finex = new NameModel();

		stamp.setName("BITSTAMP");
		stamp.setPrice(actualPrices.get("bitstamp"));
		stamp.setChange("10 %");

		e.setId(2);
		e.setName("BTCe");
		e.setPrice(actualPrices.get("btce"));
		e.setChange("11 %");

		finex.setId(3);
		finex.setName("BITFINEX");
		finex.setPrice(actualPrices.get("bitfinex"));
		finex.setChange("12 %");

		listStore.add(stamp);
		listStore.add(e);
		listStore.add(finex);

		// Setup the grid columns
		ColumnConfig<NameModel, String> bitStamp = new ColumnConfig<NameModel, String>(gridProperties.name(), 100, "EXCHANGE");
		ColumnConfig<NameModel, String> btce = new ColumnConfig<NameModel, String>(gridProperties.price(), 100, "PRICE");
		ColumnConfig<NameModel, String> bitfinex = new ColumnConfig<NameModel, String>(gridProperties.change(), 100, "CHANGE");
		List<ColumnConfig<NameModel, ?>> columns = new ArrayList<ColumnConfig<NameModel, ?>>();
		columns.add(bitStamp);
		columns.add(btce);
		columns.add(bitfinex);

		ColumnModel<NameModel> columnModel = new ColumnModel<NameModel>(columns);

		// Setup the grid view for styling
		GridView<NameModel> gridView = new GridView<NameModel>();
		gridView.setAutoExpandColumn(bitStamp);
		gridView.setAutoExpandColumn(btce);
		gridView.setAutoExpandColumn(bitfinex);

		// Setup the grid
		Grid<NameModel> grid = new Grid<NameModel>(listStore, columnModel, gridView);
		// Setup a size if not depending on the parent container to give it a size. 
		grid.setPixelSize(300, 200);

		instance = grid;
	}
}
