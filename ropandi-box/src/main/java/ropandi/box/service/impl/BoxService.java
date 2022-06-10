package ropandi.box.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ropandi.box.entity.MstBox;
import ropandi.box.feign.IRackService;
import ropandi.box.model.BoxModel;
import ropandi.box.repository.BoxRepository;
import ropandi.box.service.IBoxService;
import ropandi.box.share.model.RackModel;
@Service
public class BoxService implements IBoxService {

	@Autowired
	private IRackService rackService; 
	@Autowired
	private BoxRepository boxRepository; 
	
	@Override
	public List<BoxModel> findAll() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public BoxModel findBoxById(Long boxCode) {
		// TODO Auto-generated method stub
		
		return Mono.just(boxRepository.findById(boxCode).get())
				.flatMap(b -> {
					System.out.println("step d " + b.getBoxName());
					return Mono.just(b);
					
				})
				
				.flatMap(b ->  Mono.zip(Mono.just(b),rackService.findRackById(b.getRackCode()))
							)
		        .flatMap(data -> {
		        	System.out.println("zip 1 "+data.getT1().getBoxCode());
		        	System.out.println("zip 2 "+data.getT2().getRackName());
		        	final MstBox mb = data.getT1();
		        	final RackModel mr = data.getT2();
		        	return Mono.just(BoxModel.builder()
					.boxCode(mb.getBoxCode())
					.boxName(mb.getBoxName())
					.rackCode(mr.getRackCode())
					.rackName(mr.getRackName())
					.build()
					);
			
		}).block();
		
		
	}

	@Override
	public int save(BoxModel model) {
		try{
		boxRepository.save(MstBox
				.builder()
				.boxCode(model.getBoxCode())
				.boxName(model.getBoxName())
				.rackCode(model.getRackCode())
				.build()
				);
			
		// TODO Auto-generated method stub
		return 1;
		}catch(Exception e){
			return 0;	
		}
	}

}
