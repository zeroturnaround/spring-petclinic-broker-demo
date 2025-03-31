package org.springframework.samples.petclinic.supplements;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SupplementsController {

	private final SupplementsService supplementsService;

	public SupplementsController(SupplementsService supplementsService) {
		this.supplementsService = supplementsService;
	}

	@GetMapping("/supplements.html")
	public String showSupplementsList(@RequestParam(defaultValue = "1") int page, Model model) {
		Supplements supplements = new Supplements();
		Page<Supplement> paginated = findPaginated(page);
		supplements.getSupplementList().addAll(paginated.toList());
		return addPaginationModel(page, paginated, model);
	}

	private String addPaginationModel(int page, Page<Supplement> paginated, Model model) {
		List<Supplement> listVets = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("listSupplements", listVets);
		return "supplements/supplementList";
	}

	private Page<Supplement> findPaginated(int page) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return supplementsService.fetchPaginated(pageable);
	}

	@GetMapping({ "/supplements" })
	public @ResponseBody Supplements showResourcesVetList() {
		Supplements supplements = new Supplements();
		supplements.getSupplementList().addAll(supplementsService.fetch());
		return supplements;
	}

}
