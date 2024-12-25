package lshh.pollservice.infrastructure.jpa;

import lshh.pollservice.domain.entity.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleJpaRepository extends JpaRepository<Schedule, Long> {
}
