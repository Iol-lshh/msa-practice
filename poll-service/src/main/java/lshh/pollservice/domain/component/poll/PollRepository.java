package lshh.pollservice.domain.component.poll;

import lshh.pollservice.domain.component.EntityRepository;
import lshh.pollservice.domain.entity.poll.Poll;

import java.util.List;

public interface PollRepository extends EntityRepository<Poll, Long> {
    Poll save(Poll poll);

    List<Poll> findAll();

    List<Poll> findList(Integer pageNo, Integer pageSize);
}
