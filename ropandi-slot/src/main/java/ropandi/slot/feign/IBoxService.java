package ropandi.slot.feign;
import reactor.core.publisher.Mono;
import ropandi.slot.share.model.BoxModel;
public interface IBoxService {

	
	public Mono<BoxModel> findOneBox(Long id);
}
