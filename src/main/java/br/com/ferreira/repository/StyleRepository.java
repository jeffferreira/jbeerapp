package br.com.ferreira.repository;

import br.com.ferreira.domain.Style;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Style entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

}
