package lshh.codedelta.infrastructure;

import java.util.Optional;

import lshh.codedelta.entity.CommitHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepository extends JpaRepository<CommitHistoryEntity, Long> {
	Optional<CommitHistoryEntity> findByCommitPoint(String commitPoint);
}
