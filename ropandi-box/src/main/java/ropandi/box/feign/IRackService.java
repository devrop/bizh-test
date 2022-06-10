package ropandi.box.feign;

import java.util.Set;

import reactor.core.publisher.Mono;
import ropandi.box.share.model.BaseResponse;
import ropandi.box.share.model.RackModel;;
public interface IRackService {

	
	public Mono<RackModel> findRackById(Long rackCode);
	
	public Mono<RackModel> findRackById(Set<Long> rackCodes);
	
	
}
