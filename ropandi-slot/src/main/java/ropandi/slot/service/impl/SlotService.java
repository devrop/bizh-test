package ropandi.slot.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

import ropandi.slot.entity.MstSlot;
import ropandi.slot.feign.IBoxService;
import ropandi.slot.model.SlotModel;
import ropandi.slot.repo.SlotRepository;
import ropandi.slot.service.ISlotService;
import ropandi.slot.share.model.BoxModel;

@Service
public class SlotService implements ISlotService {
    @Autowired
	private SlotRepository slotRepository;
    @Autowired
	private IBoxService boxService; 
	
	@Override
	public SlotModel findOne(Long Id) {
		return Mono.just(slotRepository.findById(Id).get())
		.flatMap(b ->  Mono.zip(Mono.just(b),boxService.findOneBox(b.getBoxCode())))
					
        .flatMap(data -> {
        	final MstSlot ms = data.getT1();
        	final BoxModel mb = data.getT2();
        	SlotModel m = SlotModel.builder().slotCode(ms.getSlotCode())
        			.slotName(ms.getSlotName())
        			.slotCapacity(ms.getSlotCapacity())
        			.usedQty(ms.getUsedQty())
        			.boxCode(mb.getBoxCode())
        			.boxName(mb.getBoxName())
        			.rackCode(mb.getRackCode())
        			.rackName(mb.getRackName())
        			.build();
        	return Mono.just(m);
}).block();
		
		
		
		
		
	}

	@Override
	public List<SlotModel> findAll() {
		// TODO Auto-generated method stub
		return slotRepository.findAll().stream().map(s-> {
			return SlotModel.builder().slotCode(s.getSlotCode())
					.slotName(s.getSlotName())
					.rackCode(s.getRackCode())
					.slotCapacity(s.getSlotCapacity())
					.usedQty(s.getUsedQty())
					.boxCode(s.getBoxCode()).build();
					
		}).collect(Collectors.toList());
	}

	@Override
	public int save(SlotModel model) {
		// TODO Auto-generated method stub
		try{
			
			slotRepository.save(
					MstSlot.builder()
					       .slotCode(model.getSlotCode())
					       .slotName(model.getSlotName())
					       .boxCode(model.getBoxCode())
					       .rackCode(model.getRackCode())
					       .slotCapacity(model.getSlotCapacity())
					       .usedQty(model.getUsedQty())
					       .build()
					
					);
			
			return 1;
		}catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
		
	}

}
