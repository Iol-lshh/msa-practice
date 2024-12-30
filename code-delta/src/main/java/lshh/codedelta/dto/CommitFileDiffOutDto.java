package lshh.codedelta.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import lshh.core.lib.type.OutputDto;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.revwalk.RevCommit;

public record CommitFileDiffOutDto(
	CommitFileOutDto oldFile,
	CommitFileOutDto newFile,
	String commitRegisterPersonId,
	String commitComment,
	LocalDateTime commitDatetime,
	String changeType,
	String path
) implements OutputDto {

	public static CommitFileDiffOutDto of(DiffEntry diffEntry, RevCommit oldCommit, String oldContents, RevCommit newCommit, String newContents) {
		long datetime = Integer.valueOf(newCommit.getCommitTime()).longValue();
		LocalDateTime commitAt = Instant.ofEpochSecond(datetime)
			.atZone(ZoneId.systemDefault())
			.toLocalDateTime();
		return new CommitFileDiffOutDto(
			new CommitFileOutDto(oldCommit.getName(), diffEntry.getOldPath(), oldContents),
			new CommitFileOutDto(newCommit.getName(), diffEntry.getNewPath(), newContents),
			newCommit.getCommitterIdent().getName(),
			newCommit.getFullMessage(),
			commitAt,
			diffEntry.getChangeType().name(),
			diffEntry.getNewPath()
		);
	}
}
