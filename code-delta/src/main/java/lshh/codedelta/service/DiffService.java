package lshh.codedelta.service;

import static lshh.codedelta.common.git.local.GitOperationAspect.*;

import java.util.List;
import java.util.Optional;

import lshh.codedelta.dto.CommitFileDiffOutDto;
import lshh.codedelta.entity.CommitHistoryEntity;
import lshh.codedelta.entity.QueryDiffEntity;
import lshh.codedelta.infrastructure.CommitRepository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lshh.codedelta.common.git.local.GitOperation;
import lshh.codedelta.common.git.IllegalGitStateException;
import lshh.codedelta.common.git.local.LocalGitRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiffService {
	private final LocalGitRepository localGitRepository;
	private final CommitRepository repository;

	@GitOperation
	@Transactional
	public int executeDiffQueries(String oldId, String newId) {
		int result = 0;
		// git
		RevCommit oldCommit = localGitRepository.findCommitByHash(git(), oldId)
			.orElseThrow(IllegalGitStateException::new);
		RevCommit newCommit = localGitRepository.findCommitByHash(git(), newId)
			.orElseThrow(IllegalGitStateException::new);
		List<CommitFileDiffOutDto> diffMappers = localGitRepository.findDiffMapperFiles(git(), oldCommit, newCommit);
		// db
		for (CommitFileDiffOutDto fileDiffDto : diffMappers) {
			CommitHistoryEntity commitHistory = CommitHistoryEntity.of(fileDiffDto);
			Optional<CommitHistoryEntity> optionalComparingCommitHistory = repository.findByCommitPoint(
				fileDiffDto.oldFile().commitHash());
			List<QueryDiffEntity> diffs = optionalComparingCommitHistory
				.map(commitHistoryEntity -> QueryDiffEntity.ofAll(fileDiffDto, commitHistoryEntity, commitHistory))
				.orElseGet(() -> QueryDiffEntity.ofAll(fileDiffDto, commitHistory));
			commitHistory.addAll(diffs);
			repository.save(commitHistory);
			result += diffs.size();
		}
		return result;
	}
}
