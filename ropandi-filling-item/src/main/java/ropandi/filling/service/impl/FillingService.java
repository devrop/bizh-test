package ropandi.filling.service.impl;



import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Mono;
import ropandi.filling.entity.TrxFillingItemHdr;
import ropandi.filling.model.FillingItemDModel;
import ropandi.filling.model.FillingItemHModel;
import ropandi.filling.repo.FillingDRepository;
import ropandi.filling.repo.FillingHRepository;
import ropandi.filling.service.IFillingService;
@Service
@Transactional
public class FillingService implements IFillingService {

	private FillingHRepository fillingHRepository;
	private FillingDRepository fillingDRepository;
	@Override
	public int save(FillingItemHModel model) {
		// TODO Auto-generated method stub
		fillingHRepository.save(
				TrxFillingItemHdr.builder().trxNo(model.getTrxNo())
				.trxName(model.getTrxName())
				.qty(Long.valueOf(model.getDetails().size()))
				.build()
				);
		//Map<String,Integer> slotMap = model.getDetails()
			//	.stream().collect(groupingBy(FillingItemDModel::getSlotCode,  Collectors.summingInt(FillingItemDModel::getSlotCode)));
		
		return 0;
	}

	@Override
	public FillingItemHModel findOne(String trxNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
