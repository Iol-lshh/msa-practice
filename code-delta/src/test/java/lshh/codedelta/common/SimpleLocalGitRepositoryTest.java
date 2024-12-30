package lshh.codedelta.common;

import static lshh.codedelta.common.git.local.GitOperationAspect.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lshh.codedelta.common.git.local.GitOperationAspect;
import lshh.codedelta.common.git.local.SimpleLocalGitRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@EnableAspectJAutoProxy
class SimpleLocalGitRepositoryTest {

	@Autowired
	private SimpleLocalGitRepository utd;

	@Value("${git.repo-path}")
	String repoPath;
	@Autowired
	private GitOperationAspect aspect;

	@BeforeAll
	public static void initTest() {
		mockStatic(GitOperationAspect.class);
	}

	@Test
	public void 정상_gitRepo_동작() {
		assertNotNull(repoPath);
		assertNotEquals("", repoPath);
		assertNotNull(aspect);
	}

	@Test
	public void 정상_Git_동작() throws IOException {
		try (Git git = Git.open(new File(repoPath))) {
			when(GitOperationAspect.git()).thenReturn(git);
			assertNotNull(git());
		}
	}

	@Test
	public void 정상_findCommitByHash_조회() throws IOException {
		try (Git git = Git.open(new File(repoPath))) {
			when(GitOperationAspect.git()).thenReturn(git);
//			Optional<RevCommit> oldOption = utd.findCommitByHash(git(), "");
//			Optional<RevCommit> newOption = utd.findCommitByHash(git(), "");
//
//			assertNotNull(oldOption.orElse(null));
//			assertNotNull(newOption.orElse(null));
		}
	}

	@Test
	public void 정상_findDiffMapperFiles_작동() throws IOException {
		try (Git git = Git.open(new File(repoPath))) {
			when(GitOperationAspect.git()).thenReturn(git);
//			Optional<RevCommit> oldOption = utd.findCommitByHash(git(), "");
//			Optional<RevCommit> newOption = utd.findCommitByHash(git(), "");
//			assertNotNull(oldOption.orElse(null));
//			assertNotNull(newOption.orElse(null));
//
//			List<CommitFileDiffOutDto> result = utd.findDiffMapperFiles(git(), oldOption.get(), newOption.get());
//
//			assertNotNull(result);
//			assertNotEquals(0, result.size());
//			log.warn(result.toString());
		}
	}
}