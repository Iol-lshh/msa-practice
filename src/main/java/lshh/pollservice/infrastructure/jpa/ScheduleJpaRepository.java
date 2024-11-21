package lshh.pollservice.infrastructure.jpa;

import lshh.pollservice.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleJpaRepository extends JpaRepository<Schedule, Long> {
}
