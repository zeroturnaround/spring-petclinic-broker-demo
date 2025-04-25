package org.zt.demo.supplements;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.zt.demo.supplements.model.Supplement;

public class SupplementsRepository {

	private List<Supplement> getSupplementsData() {
		List<Supplement> supplements = new ArrayList<>();
		int index = 0;

		for (String line : readContent().subList(1, readContent().size())) {
			String[] split = line.split(",");
			supplements.add(new Supplement(index++, split[0].trim(), Integer.valueOf(split[2].trim()), Integer.valueOf(split[1].trim())));
		}

		return supplements;
	}

	private List<String> readContent() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("supplements.csv");
		String content = new Scanner(inputStream).useDelimiter("\\A").next();
		List<String> split = Arrays.asList(content.split("\n"));
		return split;
	}

	public List<Supplement> getSupplements() {
		return getSupplementsData().stream().filter(supplement -> supplement.getStock() >= 0).collect(Collectors.toList());
	}
}
