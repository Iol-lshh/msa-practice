package lshh.pollservice.domain.component.poll;

import lshh.pollservice.common.exception.PersistencNotFoundException;
import lshh.pollservice.domain.entity.Poll;

import java.util.Optional;

public interface PollRepository {
    Poll save(Poll poll);

    Optional<Poll> findById(Long id);
    default Poll getById(Long id){
        return findById(id).orElseThrow(()->new PersistencNotFoundException("Poll not found"));
    }
}
