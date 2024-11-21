package lshh.pollservice.domain;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.component.schedule.ScheduleRepository;
import lshh.pollservice.domain.component.poll.PollFactory;
import lshh.pollservice.domain.component.poll.PollRepository;
import lshh.pollservice.domain.entity.Poll;
import lshh.pollservice.dto.poll.*;
import lshh.pollservice.dto.poll.schedule.PollScheduleCreateCommand;
import lshh.pollservice.dto.poll.schedule.PollScheduleDetail;
import lshh.pollservice.dto.poll.schedule.PollScheduleUpdateCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PollService {
    private final PollRepository repository;
    private final PollFactory factory;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public PollScheduleDetail create(PollScheduleCreateCommand command) {
        command.options().forEach(option->scheduleRepository.getById(option.scheduleId()));
        Poll poll = factory.generate(command);
        repository.save(poll);
        return PollScheduleDetail.from(poll);
    }

    @Transactional
    public PollSimple update(PollScheduleUpdateCommand command) {
        // 상태 변경
        // 값 변경
        return null;
    }

    @Transactional(readOnly = true)
    public List<PollSimple> all() {
        return null;
    }

    @Transactional(readOnly = true)
    public List<PollSimple> list(Integer pageNo, Integer pageSize) {
        return null;
    }

    @Transactional(readOnly = true)
    public PollSimple detail(Long id) {
        return null;
    }

    @Transactional
    public PollSimple book(PollBookCommand command) {
        return null;
    }

    @Transactional
    public PollSimple open(PollOpenCommand command) {
        return null;
    }

    @Transactional
    public PollSimple close(PollCloseCommand command) {
        return null;
    }

    @Transactional
    public PollSimple quit(PollQuitCommand command) {
        return null;
    }
}
