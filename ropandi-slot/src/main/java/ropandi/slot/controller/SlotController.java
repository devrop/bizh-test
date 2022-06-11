package ropandi.slot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import ropandi.slot.base.BaseResponse;
import ropandi.slot.entity.MstSlot;
import ropandi.slot.model.SlotModel;
import ropandi.slot.service.ISlotService;

@RestController
public class SlotController {

	@Autowired
	private ISlotService slotService;
	
	@PostMapping("save")
	public Mono<BaseResponse<Integer>> save(@RequestBody SlotModel slot){
		int result =slotService.save(slot);
		BaseResponse<Integer> res = new BaseResponse<>();
		res.setStatus(0);
		res.setContents(result);
		return Mono.just(res);
	}
	@GetMapping("findById/{id}")
	public Mono<SlotModel> findOne(@PathVariable("id") Long id){
		SlotModel slot = slotService.findOne(id);
		
		return Mono.just(slot);
	}
	@GetMapping("all")
	public ResponseEntity<BaseResponse<List<SlotModel>>> findAll(){
		List<SlotModel> slots = slotService.findAll();
		BaseResponse<List<SlotModel>> response = new BaseResponse<>();
		response.setStatus(0);
		response.setTotalRecords(1);
		response.setContents(slots);
		return new ResponseEntity<BaseResponse<List<SlotModel>>>(response,HttpStatus.OK);
	}
	
	@PostMapping("updateCapacity")
	public Mono<SlotModel> updateCapacity(@RequestBody SlotModel slot){
		
		System.out.println("call this" + slot.getSlotCode() );
		return slotService.updateSlotUsed(slot);
	}
}
