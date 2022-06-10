package ropandi.rack.service;

import java.util.List;
import java.util.Set;

import ropandi.rack.model.RackModel;

public interface IRackService {

	public List<RackModel> findAllRack();
	public RackModel findOne(Long rackCode);
	public int save(RackModel saveData);
	public int delete(Long rackCode);
	public List<RackModel> findAllById(Set<Long> rackCodes);
	
}
