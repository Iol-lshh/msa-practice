package lshh.pollservice.infrastructure;

import lshh.core.lib.component.persistence.EntityRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractRepositoryWithJpa<ENTITY, ID> implements EntityRepository<ENTITY, ID> {
    protected abstract JpaRepository<ENTITY, ID> jpaRepository();

    @Override
    public Optional<ENTITY> findById(ID id) {
        return jpaRepository().findById(id);
    }

    @Override
    public List<ENTITY> findAll() {
        return jpaRepository().findAll();
    }

    @Override
    public List<ENTITY> findList(Integer pageNo, Integer pageSize) {
        return jpaRepository().findAll(Pageable.ofSize(pageSize).withPage(pageNo)).toList();
    }

    @Override
    public ENTITY save(ENTITY entity) {
        return jpaRepository().save(entity);
    }
}
