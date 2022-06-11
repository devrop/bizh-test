package ropandi.slot.service;

import java.util.List;

import reactor.core.publisher.Mono;
import ropandi.slot.model.SlotModel;

public interface ISlotService {

	public SlotModel findOne(Long Id);
	public List<SlotModel> findAll();
	public int save(SlotModel model);
	public Mono<SlotModel> updateSlotUsed(SlotModel model);
	
}
