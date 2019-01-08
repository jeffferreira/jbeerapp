package br.com.ferreira.repository;

import br.com.ferreira.domain.Hop;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Hop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HopRepository extends JpaRepository<Hop, Long> {

}
