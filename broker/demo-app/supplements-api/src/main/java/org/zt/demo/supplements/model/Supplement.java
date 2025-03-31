package org.zt.demo.supplements.model;

public class Supplement {

	private int id;
	private String name;
	private int stock;
	private int price;

	public Supplement(int id, String name, int stock, int price) {
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStock() {
		return stock;
	}

	public int getPrice() {
		return price;
	}

	public String getPriceString() {
		return String.format("%d.%02d", price/100, price%100);
	}

	public String getAvailability() {
		return stock > 0 ? "In Stock" : "Sold out";
	}
}
