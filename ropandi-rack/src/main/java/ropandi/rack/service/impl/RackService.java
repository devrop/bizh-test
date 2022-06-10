package ropandi.rack.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ropandi.rack.entity.MstRack;
import ropandi.rack.model.RackModel;
import ropandi.rack.repo.RackRepository;
import ropandi.rack.service.IRackService;
@Service
@Transactional
public class RackService implements IRackService {

	@Autowired
	private RackRepository rackRepository; 
	
	@Override
	public List<RackModel> findAllRack() {
		// TODO Auto-generated method stub
		return rackRepository.findAll().stream().map(m->RackModel.builder().rackCode(m.getRackCode()).rackName(m.getRackName()).build())
				.collect(Collectors.toList());
	}

	@Override
	public RackModel findOne(Long rackCode) {
		// TODO Auto-generated method stub
		return rackRepository.findById(rackCode).map(s-> RackModel.builder().rackCode(s.getRackCode()).rackName(s.getRackName()).build()).get();
	}

	@Override
	public int save(RackModel saveData) {
		// TODO Auto-generated method stub
		try{
			rackRepository.save(
					MstRack.builder().rackCode(saveData.getRackCode())
					.rackName(saveData.getRackName()).build()
					);
			
			return 1;
		}catch(Exception e){
			return 0;	
		}
		
		
	}

	@Override
	public int delete(Long rackCode) {
		// TODO Auto-generated method stub
		try{
		rackRepository.deleteById(rackCode);
		return 1;
		}catch (Exception e) {
			// TODO: handle exception
		return 0;
		}
	}

	@Override
	public List<RackModel> findAllById(Set<Long> rackCodes) {
		// TODO Auto-generated method stub
		return rackRepository.findAllByRackCode(rackCodes).stream().map(m->RackModel.builder().rackCode(m.getRackCode()).rackName(m.getRackName()).build())
				.collect(Collectors.toList());
	}

}
