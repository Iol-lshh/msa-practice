package lshh.pollservice.domain;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.component.schedule.ScheduleFactory;
import lshh.pollservice.domain.component.schedule.ScheduleRepository;
import lshh.pollservice.domain.entity.Schedule;
import lshh.pollservice.dto.schedule.ScheduleCreateCommand;
import lshh.pollservice.dto.schedule.ScheduleDetail;
import lshh.pollservice.dto.schedule.ScheduleSimple;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository repository;
    private final ScheduleFactory factory;

    public List<ScheduleSimple> list() {
        return repository.findAll().stream().map(ScheduleSimple::from).toList();
    }

    public ScheduleDetail detail(Long id) {
        return ScheduleDetail.from(repository.getById(id));
    }

    public ScheduleDetail create(ScheduleCreateCommand command) {
        Schedule schedule = factory.generate(command);
        repository.save(schedule);
        return ScheduleDetail.from(schedule);
    }
}
