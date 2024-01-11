package codesyncgroup.ticketsystem.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import codesyncgroup.ticketsystem.Entities.TicketEntity;

@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, Long> {
}
