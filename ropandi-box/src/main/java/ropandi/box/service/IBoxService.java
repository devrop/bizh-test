package ropandi.box.service;

import java.util.List;

import reactor.core.publisher.Mono;
import ropandi.box.model.BoxModel;

public interface IBoxService {

	public List<BoxModel> findAll();
	public BoxModel findBoxById(Long boxCode);
	public int save(BoxModel model);
	
	
	
	
}
