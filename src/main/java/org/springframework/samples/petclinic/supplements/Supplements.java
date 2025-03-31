package org.springframework.samples.petclinic.supplements;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Supplements {

	private List<Supplement> supplements;

	@XmlElement
	public List<Supplement> getSupplementList() {
		if (supplements == null) {
			supplements = new ArrayList<>();
		}
		return supplements;
	}

}
