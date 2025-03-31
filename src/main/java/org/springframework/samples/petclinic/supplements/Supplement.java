package org.springframework.samples.petclinic.supplements;

import jakarta.xml.bind.annotation.XmlElement;

public class Supplement {

	@XmlElement
	private int id;

	@XmlElement
	private String name;

	@XmlElement
	private int stock;

	@XmlElement
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
		return String.format("%d.%02d", price / 100, price % 100);
	}

	public String getAvailability() {
		return stock > 0 ? "In Stock" : "Sold out";
	}

}
