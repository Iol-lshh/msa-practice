package lshh.codedelta.common.git.origin;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Getter
public class OriginGitConfigMap {
	private String remoteUrl;
	private String localPath;
	private String branch;
	private String username;
	private String password;
}
