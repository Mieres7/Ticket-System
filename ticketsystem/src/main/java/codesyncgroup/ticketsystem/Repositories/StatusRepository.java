package codesyncgroup.ticketsystem.Repositories;

import codesyncgroup.ticketsystem.Entities.StatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<StatusEntity, Long> {

}
