package lshh.codedelta.common.git.local;

import java.io.File;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.eclipse.jgit.api.Git;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lshh.codedelta.common.git.IllegalGitStateException;

@Aspect
@Component
public class GitOperationAspect {
	private static final ThreadLocal<Git> gitThreadLocal = new ThreadLocal<>();
	@Value("${git.repo-path}")
	String repoPath;

	@Around("@annotation(gitOperation)")
	public Object manageGitResource(ProceedingJoinPoint joinPoint, GitOperation gitOperation) throws Throwable {
		try (Git git = Git.open(new File(repoPath))) {
			gitThreadLocal.set(git);
			return joinPoint.proceed();
		} finally {
			gitThreadLocal.remove();
		}
	}

	public static Git git(){
		Git git = gitThreadLocal.get();
		if(git == null){
			throw new IllegalGitStateException("No Git instance in this context.");
		}
		return git;
	}
}
