package ropandi.filling.service;

import ropandi.filling.model.FillingItemHModel;

public interface IFillingService {

	
	public int save(FillingItemHModel model);
	public FillingItemHModel findOne(String trxNo);
	
}
