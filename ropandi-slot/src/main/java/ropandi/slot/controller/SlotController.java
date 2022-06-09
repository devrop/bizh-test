package ropandi.slot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ropandi.slot.base.BaseResponse;
import ropandi.slot.entity.MstSlot;
import ropandi.slot.model.SlotModel;
import ropandi.slot.service.ISlotService;

@RestController
public class SlotController {

	@Autowired
	private ISlotService slotService;
	
	@PostMapping("save")
	public ResponseEntity<BaseResponse<Integer>> save(@RequestBody SlotModel slot){
		slotService.save(slot);
		
		return null;
	}
	@GetMapping("findById")
	public ResponseEntity<BaseResponse<SlotModel>> findOne(@RequestParam("slotId") Long id){
		SlotModel slot = slotService.findOne(id);
		BaseResponse<SlotModel> response = new BaseResponse<SlotModel>();
		response.setStatus(0);
		response.setTotalRecords(1);
		response.setContents(slot);
		return new ResponseEntity<BaseResponse<SlotModel>>(response,HttpStatus.OK);
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
}
