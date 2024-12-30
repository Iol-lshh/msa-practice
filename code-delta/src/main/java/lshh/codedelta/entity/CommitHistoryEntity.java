package lshh.codedelta.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lshh.codedelta.dto.CommitFileDiffOutDto;
import lshh.core.lib.type.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "COMMIT_HIST")
public class CommitHistoryEntity extends BaseEntity {
	@Id
	@Column(name = "COMMIT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "COMMIT_POIN")
	String commitPoint;
	@Column(name = "COMP_COMMIT_POIN")
	String comparingCommitPoint;
	@Column(name = "COMMIT_REGP_ID")
	String commitRegisterPersonId;
	@Column(name = "COMMIT_REG_DTM")
	LocalDateTime commitRegisteredDateTime;
	@Column(name = "COMMIT_COMT")
	String commitComment;
	@Column(name = "APPR_YN")
	String approveYn;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commitHistory")
	List<QueryDiffEntity> queryDiffs;

	public static CommitHistoryEntity of(CommitFileDiffOutDto diffMapper) {
		return CommitHistoryEntity.builder()
			.commitPoint(diffMapper.newFile().commitHash())
			.comparingCommitPoint(diffMapper.oldFile().commitHash())
			.commitRegisterPersonId(diffMapper.commitRegisterPersonId())
			.commitRegisteredDateTime(diffMapper.commitDatetime())
			.commitComment(diffMapper.commitComment())
			.approveYn("N")
			.queryDiffs(new ArrayList<>())
			.build();
	}

	public void addAll(List<QueryDiffEntity> diffs) {
		if (diffs.isEmpty()) {
			return;
		}
		queryDiffs.addAll(diffs);
	}
}
