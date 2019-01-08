package br.com.ferreira.repository;

import br.com.ferreira.domain.Mash;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Mash entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MashRepository extends JpaRepository<Mash, Long> {

}
