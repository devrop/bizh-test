package ropandi.filling.feign;

import reactor.core.publisher.Mono;
import ropandi.filling.share.model.SlotModel;

public interface ISlotService {

	
	public Mono<SlotModel> findOneSlotById(Long slotCode);
	
	public Mono<SlotModel> updateCapacity(SlotModel slotCode);
	
}
