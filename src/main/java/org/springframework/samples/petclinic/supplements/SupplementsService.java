package org.springframework.samples.petclinic.supplements;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SupplementsService {

	List<Supplement> fetch() {
		//
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://supplements-api:8080/supplements-api/supplements";
		ResponseEntity<List<Supplement>> exchange = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Supplement>>() {
				});
		return exchange.getBody();
	}

	public Page<Supplement> fetchPaginated(Pageable pageRequest) {
		List<Supplement> supplements = fetch();

		int start = (int) pageRequest.getOffset();
		int end = Math.min((start + pageRequest.getPageSize()), supplements.size());

		return new PageImpl<>(supplements.subList(start, end), pageRequest, supplements.size());
	}

}
