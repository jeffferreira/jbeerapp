package br.com.ferreira.repository;

import br.com.ferreira.domain.Fermentable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Fermentable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FermentableRepository extends JpaRepository<Fermentable, Long> {

}
