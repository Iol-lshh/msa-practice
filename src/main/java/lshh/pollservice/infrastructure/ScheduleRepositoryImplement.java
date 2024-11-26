package lshh.pollservice.infrastructure;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.component.schedule.ScheduleRepository;
import lshh.pollservice.domain.entity.Schedule;
import lshh.pollservice.infrastructure.jpa.ScheduleJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ScheduleRepositoryImplement extends AbstractRepositoryWithJpa<Schedule, Long>
        implements ScheduleRepository {
    private final ScheduleJpaRepository jpaRepository;

    @Override
    protected JpaRepository<Schedule, Long> jpaRepository() {
        return this.jpaRepository;
    }
}
