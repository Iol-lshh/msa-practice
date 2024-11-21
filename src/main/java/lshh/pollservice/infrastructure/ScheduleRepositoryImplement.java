package lshh.pollservice.infrastructure;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.component.schedule.ScheduleRepository;
import lshh.pollservice.domain.entity.Schedule;
import lshh.pollservice.infrastructure.jpa.ScheduleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ScheduleRepositoryImplement implements ScheduleRepository {
    private final ScheduleJpaRepository jpaRepository;
    @Override
    public Optional<Schedule> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Schedule> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Schedule save(Schedule schedule) {
        return jpaRepository.save(schedule);
    }
}
