package ropandi.box.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import ropandi.box.model.BoxModel;
import ropandi.box.service.IBoxService;
import ropandi.box.share.model.*;
@RestController
public class BoxController {

	@Autowired
	IBoxService boxService;
	
	@PostMapping("save")
	public Mono<RestResponse> save(@RequestBody BoxModel model){
		final int result= boxService.save(model);

		RestResponse response = new RestResponse();
		if (result == 1) {
			response.setStatus(0);
			response.setMessage("OK");
		} else {
			response.setStatus(1);
			response.setMessage("ERROR");
		}

		return Mono.just(response);
		
	} 
	
	@GetMapping("findById/{id}")
	public Mono<BoxModel> findById(@PathVariable("id") Long boxCode) {
       
		BoxModel bm = boxService.findBoxById(boxCode);
		/*
		RestResponse response = new RestResponse();
		response.setStatus(0);
		response.setMessage("OK");
		response.setTotalRecords(1);
		response.setContents(bm);
		*/
		return Mono.just(bm);
		
		//return boxService.findBoxById(boxCode);
	}
	
	@GetMapping("test")
	public Mono<String> test(){
		return Mono.just("Hello World");
	}
	
}
