package ropandi.rack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import ropandi.rack.base.BaseResponse;
import ropandi.rack.base.RestResponse;
import ropandi.rack.model.RackModel;
import ropandi.rack.service.IRackService;

@RestController
public class RackController {

	@Autowired
	IRackService rackService;

	@PostMapping("save")
	public Mono<RestResponse> saveRack(@RequestBody RackModel rackModel) {
		final int result = rackService.save(rackModel);

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

	@DeleteMapping("delete")
	public Mono<RestResponse> saveRack(@RequestParam Long rackCode) {
		final int result = rackService.delete(rackCode);

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

	@GetMapping("findAll")
	public Mono<RestResponse> findAll() {

		List<RackModel> listAll = rackService.findAllRack();
		RestResponse response = new RestResponse();
			response.setStatus(0);
			response.setMessage("OK");
			response.setTotalRecords(listAll.size());
			response.setContents(listAll);
		return Mono.just(response);
	}
	
	
	@GetMapping("findById/{id}")
	public Mono<RackModel> findById(@PathVariable("id") Long rackCode) {

		
		RackModel rack = rackService.findOne(rackCode);
		/*
		RestResponse response = new RestResponse();
		response.setStatus(0);
		response.setMessage("OK");
		response.setTotalRecords(1);
		response.setContents(rack);
		return Mono.just(response);
		*/
		return Mono.just(rack);
	}
	
	
	@PostMapping("rack/findAllById")
	public Mono<BaseResponse<List<RackModel>>> findAllById(@RequestBody RackModel rackModel) {

		List<RackModel> listAll = rackService.findAllById(rackModel.getRackCodes());
		BaseResponse<List<RackModel>> response = new BaseResponse<>();
			response.setStatus(0);
			response.setMessage("OK");
			response.setTotalRecords(listAll.size());
			response.setContents(listAll);
		return Mono.just(response);
	}
	
}
