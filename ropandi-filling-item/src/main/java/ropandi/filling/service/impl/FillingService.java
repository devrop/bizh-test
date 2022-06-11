package ropandi.filling.service.impl;



import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Mono;
import ropandi.filling.entity.TrxFillingItemDtl;
import ropandi.filling.entity.TrxFillingItemHdr;
import ropandi.filling.exception.SlotFullCapacityException;
import ropandi.filling.feign.ISlotService;
import ropandi.filling.model.FillingItemDModel;
import ropandi.filling.model.FillingItemHModel;
import ropandi.filling.repo.FillingDRepository;
import ropandi.filling.repo.FillingHRepository;
import ropandi.filling.service.IFillingService;
import ropandi.filling.share.model.SlotModel;
@Service
@Transactional
public class FillingService implements IFillingService {

	@Autowired
	private FillingHRepository fillingHRepository;
	@Autowired
	private FillingDRepository fillingDRepository;
	@Autowired
	private ISlotService slotService;
	
	@Override
	public Mono<Integer> inputNew(FillingItemHModel model) {
		// TODO Auto-generated method stub
		try{
			//String a = slotService.findOneSlotById(model.getSlotCode()).block().getSlotName();
			//System.out.println("slot Code " +a);
			
			final String trxNo = genTrxNo();
			return slotService.findOneSlotById(model.getSlotCode())
					
			.flatMap(d -> {
				
				long remainCapacity = d.getSlotCapacity().longValue() - d.getUsedQty().longValue();
				
				if(remainCapacity < model.getQty()){
					throw SlotFullCapacityException
					.builder().slotCode(d.getSlotCode())
					.slotName(d.getSlotName())
					.remainingCapacity(remainCapacity)
					.build();
				}
				System.out.println("then in 1");
				long used = d.getUsedQty().longValue() - model.getQty().longValue();
				System.out.println("then in");
				return Mono.just(SlotModel
						.builder()
						.slotCode(d.getSlotCode())
						.usedQty(used)
						.build()
						);
				
			}) 
			.flatMap(slotService::updateCapacity)
			.flatMap(slot -> {
				this.fillingHRepository.save(
						TrxFillingItemHdr
						.builder()
						.trxNo(trxNo)
						.slotCode(slot.getSlotCode())
						.trxName(model.getTrxName())
						.qty(Long.valueOf(model.getDetails().size()))
						.fillType("I")
						.build()
						);
				
				model.getDetails().forEach(f -> {
					this.fillingDRepository.save(
							TrxFillingItemDtl.builder()
							.trxNo(trxNo)
							.itemName(f.getItemName())
							.build()
							
							);
					
				});
				return Mono.just(1);
			});
			
		
		
		
		
		}catch(Exception e){
			return Mono.just(0);
		}
	}

	@Override
	public Mono<FillingItemHModel> findOne(String trxNo) {
		// TODO Auto-generated method stub
		
		
		return Mono.just(fillingHRepository.findById(trxNo).get())
				
				.flatMap(hdr -> Mono.zip(Mono.just(hdr),Mono.just(fillingDRepository.findByTrxNo(trxNo)),slotService.findOneSlotById(hdr.getSlotCode()))
				        )
						.flatMap(data -> {
							
							final TrxFillingItemHdr h = data.getT1();
							final List<TrxFillingItemDtl> dt = data.getT2();
							final List<FillingItemDModel> dtd = dt
									.stream().map(dd ->{ 
										
									return FillingItemDModel.builder().trxNo(dd.getTrxNo())
											.itemName(dd.getItemName())
											.build();
									}
											).collect(Collectors.toList());
									
							final SlotModel slot = data.getT3();
							
							return Mono.just(FillingItemHModel.builder()
									.trxNo(trxNo)
									.trxName(h.getTrxName())
									.boxCode(slot.getBoxCode())
									.boxName(slot.getBoxName())
									.rackCode(slot.getRackCode())
									.rackName(slot.getRackName())
									.slotCode(slot.getSlotCode())
									.slotName(slot.getSlotName())
									.qty(h.getQty())
									.fillType(h.getFillType())
									.details(dtd)
									.build()
								  );
							
						});
				
				
				
				
				
	}

	@Override
	public List<FillingItemHModel> findAll() {
		// TODO Auto-generated method stub
		return fillingHRepository.findAll().stream().map(f-> {
			final List<TrxFillingItemDtl> dt = fillingDRepository.findByTrxNo(f.getTrxNo());
			final List<FillingItemDModel> dtd = dt
					.stream().map(dd ->{ 
						
					return FillingItemDModel.builder().trxNo(dd.getTrxNo())
							.itemName(dd.getItemName())
							.build();
					}
							).collect(Collectors.toList());
			
			return FillingItemHModel.builder()
					.trxNo(f.getTrxNo())
					.trxName(f.getTrxName())
					.qty(f.getQty())
					.fillType(f.getFillType())
					.details(dtd)
					.build();
			
			
		}).collect(Collectors.toList());
	}
	
	private static String genTrxNo(){
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    return buffer.toString();
		
	}

}
