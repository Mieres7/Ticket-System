package codesyncgroup.ticketsystem.Repositories;

import codesyncgroup.ticketsystem.Entities.UnitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends CrudRepository<UnitEntity, Long> {
    public UnitEntity findByNameUnit(String nameUnit);
}
