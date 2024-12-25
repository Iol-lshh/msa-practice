package lshh.pollservice.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.pollservice.domain.component.schedule.ScheduleFactory;
import lshh.pollservice.domain.component.schedule.ScheduleRepository;
import lshh.pollservice.domain.entity.schedule.Schedule;
import lshh.pollservice.dto.schedule.ScheduleCreate;
import lshh.pollservice.dto.schedule.ScheduleDetail;
import lshh.pollservice.dto.schedule.ScheduleSimple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository repository;
    private final ScheduleFactory factory;

    @Transactional(readOnly = true)
    public List<ScheduleSimple> list(Integer pageNo, Integer pageSize) {
        return repository.findList(pageNo, pageSize).stream().map(ScheduleSimple::from).toList();
    }

    @Transactional(readOnly = true)
    public ScheduleDetail detail(Long id) {
        return ScheduleDetail.from(repository.getById(id));
    }

    @Transactional
    public ScheduleDetail create(ScheduleCreate command) {
        Schedule schedule = factory.generate(command);
        schedule = repository.save(schedule);
        log.info("Schedule created: {}", schedule.getId());
        return ScheduleDetail.from(schedule);
    }

    public List<ScheduleSimple> all() {
        return repository.findAll().stream().map(ScheduleSimple::from).toList();
    }
}
