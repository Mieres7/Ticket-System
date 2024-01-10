package codesyncgroup.ticketsystem.Repositories;

import codesyncgroup.ticketsystem.Entities.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {
}
