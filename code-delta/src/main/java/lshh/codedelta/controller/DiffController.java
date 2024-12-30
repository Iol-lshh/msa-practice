package lshh.codedelta.controller;

import lshh.codedelta.service.DiffService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/diff")
@RestController
public class DiffController {

	private final DiffService service;
	@GetMapping("/{oldId}/{newId}")
	public int execute(@PathVariable(name = "oldId") String oldId, @PathVariable(name = "newId") String newId){
		return service.executeDiffQueries(oldId, newId);
	}
}
