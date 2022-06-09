package ropandi.slot.service;

import java.util.List;

import ropandi.slot.model.SlotModel;

public interface ISlotService {

	public SlotModel findOne(Long Id);
	public List<SlotModel> findAll();
	public int save(SlotModel model);
	
}
