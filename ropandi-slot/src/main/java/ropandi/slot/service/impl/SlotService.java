package ropandi.slot.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ropandi.slot.entity.MstSlot;
import ropandi.slot.model.SlotModel;
import ropandi.slot.repo.SlotRepository;
import ropandi.slot.service.ISlotService;

@Service
public class SlotService implements ISlotService {
    @Autowired
	private SlotRepository slotRepository;
	
	@Override
	public SlotModel findOne(Long Id) {
		return slotRepository.findById(Id).map(s->{
			return SlotModel.builder().slotCode(Id)
					.slotName(s.getSlotName())
					.rackCode(s.getRackCode())
					.slotCapacity(s.getSlotCapacity())
					.usedQty(s.getUsedQty())
					.boxCode(s.getBoxCode()).build();
		}).get();
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
			System.out.println("data " + model.getSlotName());
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
