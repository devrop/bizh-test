package ropandi.filling.service;

import java.util.List;

import reactor.core.publisher.Mono;
import ropandi.filling.model.FillingItemHModel;

public interface IFillingService {

	
	public Mono<Integer> inputNew(FillingItemHModel model);
	public Mono<FillingItemHModel> findOne(String trxNo);
	
	public List<FillingItemHModel> findAll();
}
