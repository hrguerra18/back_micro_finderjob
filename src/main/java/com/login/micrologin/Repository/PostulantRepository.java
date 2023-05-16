package com.login.micrologin.Repository;

import com.login.micrologin.Entity.Postulant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulantRepository extends JpaRepository<Postulant, Long> {

}
