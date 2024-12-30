package lshh.codedelta.common.git.local;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.springframework.stereotype.Component;

import lshh.codedelta.common.git.IllegalGitStateException;
import lshh.codedelta.dto.CommitFileDiffOutDto;

@Component
public class SimpleLocalGitRepository implements LocalGitRepository {

	@Override
	public List<DiffEntry> findDiffs(Git git, RevCommit oldCommit, RevCommit newCommit) {
		Repository repository = git.getRepository();
		try (var reader = repository.newObjectReader()) {
			CanonicalTreeParser oldTreeParser = new CanonicalTreeParser();

			oldTreeParser.reset(reader, oldCommit.getTree());

			CanonicalTreeParser newTreeParser = new CanonicalTreeParser();
			newTreeParser.reset(reader, newCommit.getTree());

			return new Git(repository).diff()
				.setOldTree(oldTreeParser)
				.setNewTree(newTreeParser)
				.call();
		} catch (IOException | GitAPIException e) {
			throw new IllegalGitStateException(e);
		}
	}

	@Override
	public List<DiffEntry> findDiffMappers(Git git, RevCommit oldCommit, RevCommit newCommit) {
		List<DiffEntry> list = findDiffs(git, oldCommit, newCommit);
		List<DiffEntry> result = new ArrayList<>();
		for (DiffEntry diff : list) {
			if (diff.getNewPath().endsWith("Mapper.java")) {
				String fileContent = readFileContents(git, diff.getNewId().toObjectId());
				if (fileContent.contains("@Mapper")
					&& !fileContent.contains("package kr.co.milkt.nextgen.deltapatcher")) {
					result.add(diff);
				}
			}
		}
		return result;
	}

	@Override
	public String readFileContents(Git git, ObjectId id) {
		try {
			byte[] contents = git.getRepository()
				.open(id)
				.getBytes();
			return convertByteContentsToString(contents);
		} catch (IOException e) {
			throw new IllegalGitStateException(e);
		}
	}

	@Override
	public String convertByteContentsToString(byte[] contents) {
		return new String(contents, StandardCharsets.UTF_8);
	}

	@Override
	public Optional<RevCommit> findCommitByHash(Git git, String hash) {
		try (RevWalk revWalk = new RevWalk(git.getRepository())) {
			ObjectId headId = git.getRepository().resolve("HEAD");
			revWalk.markStart(revWalk.parseCommit(headId));
			for (RevCommit commit : revWalk) {
				if (commit.getName().startsWith(hash)) {
					return Optional.of(commit);
				}
			}
			return Optional.empty();
		} catch (IOException e) {
			throw new IllegalGitStateException(e);
		}
	}

	@Override
	public List<CommitFileDiffOutDto> findDiffMapperFiles(Git git, RevCommit oldCommit, RevCommit newCommit) {
		List<DiffEntry> diffEntries = findDiffMappers(git, oldCommit, newCommit);
		return diffEntries.stream()
			.map(diffEntry -> CommitFileDiffOutDto.of(
					diffEntry,
					oldCommit,
					readFileContents(git, diffEntry.getOldId().toObjectId()),
					newCommit,
					readFileContents(git, diffEntry.getNewId().toObjectId())
				)
			)
			.toList();
	}
}
