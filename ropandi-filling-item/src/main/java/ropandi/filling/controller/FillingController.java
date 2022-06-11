package ropandi.filling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import ropandi.filling.model.FillingItemHModel;
import ropandi.filling.service.IFillingService;

@RestController
public class FillingController {

	@Autowired
	private IFillingService fillingService;
	
	@PostMapping("input")
	public Mono<Integer> newOrder(@RequestBody FillingItemHModel model){
		return fillingService.inputNew(model);
	}
	@GetMapping("all")
	public Mono<List<FillingItemHModel>> findAll(){
		return Mono.just(fillingService.findAll());
	}
	
	@GetMapping("detailTrx")
	public Mono<FillingItemHModel> findDetail(@RequestParam("trxNo") String trxNo){
		return fillingService.findOne(trxNo);
	}
	
	
}
