package lshh.codedelta.common.git.origin;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class GitLabOriginRepository implements OriginGitRepository {
	private String remoteUrl;
	private String localPath;
	private String branch;
	private String username;
	private String password;

	public static OriginGitRepository of(OriginGitConfigMap config){
		return GitLabOriginRepository.builder()
			.remoteUrl(config.remoteUrl())
			.localPath(config.localPath())
			.branch(config.branch())
			.username(config.username())
			.password(config.password())
			.build();
	}

}
