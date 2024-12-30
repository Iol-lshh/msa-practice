package lshh.codedelta.common.git.local;

import java.util.List;
import java.util.Optional;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.revwalk.RevCommit;

import lshh.codedelta.dto.CommitFileDiffOutDto;

public interface LocalGitRepository {
	List<DiffEntry> findDiffs(Git git, RevCommit oldCommit, RevCommit newCommit);

	List<DiffEntry> findDiffMappers(Git git, RevCommit oldCommit, RevCommit newCommit);

	String readFileContents(Git git, ObjectId id);

	String convertByteContentsToString(byte[] contents);

	Optional<RevCommit> findCommitByHash(Git git, String hash);

	List<CommitFileDiffOutDto> findDiffMapperFiles(Git git, RevCommit oldCommit, RevCommit newCommit);
}
