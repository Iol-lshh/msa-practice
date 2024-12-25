package lshh.pollservice.domain.component;

import lshh.pollservice.common.exception.PersistenceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EntityRepository<ENTITY, ID> {
    default ENTITY getById(ID id){
        return findById(id).orElseThrow(()->new PersistenceNotFoundException("Entity not found"));
    }
    Optional<ENTITY> findById(ID id);
    List<ENTITY> findAll();
    List<ENTITY> findList(Integer pageNo, Integer pageSize);
    ENTITY save(ENTITY entity);
}
