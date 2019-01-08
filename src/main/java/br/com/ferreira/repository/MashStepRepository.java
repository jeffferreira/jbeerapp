package br.com.ferreira.repository;

import br.com.ferreira.domain.MashStep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MashStep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MashStepRepository extends JpaRepository<MashStep, Long> {

}
