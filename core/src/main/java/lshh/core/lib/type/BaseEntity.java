package lshh.core.lib.type;

import java.time.LocalDateTime;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import static lshh.core.lib.util.TraceThreadManager.threadTraceId;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
	@Embedded
	protected Registration registration;

	@PrePersist
	protected void onCreate() {
		if (getRegistration() == null || getRegisteredDateTime() == null) {
			setRegistered();
		}
	}

	@PreUpdate
	protected void onUpdate() {
		setUpdated();
	}

	protected void setRegistered() {
		this.registration = Registration.from(threadTraceId(String.class));
	}

	protected void setUpdated() {
		this.registration.update(threadTraceId(String.class));
	}

	public String getGuid() {
		return this.registration.getGuid();
	}

	public LocalDateTime getRegisteredDateTime() {
		return this.registration.getRegisteredDateTime();
	}

	public LocalDateTime getUpdatedDateTime() {
		return this.registration.getUpdatedDateTime();
	}
}
