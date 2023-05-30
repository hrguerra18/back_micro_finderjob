package com.login.micrologin.Repository;

import com.login.micrologin.Entity.Offers;
import com.login.micrologin.Entity.PostulantOffer;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulantOfferRepository extends JpaRepository<PostulantOffer, Long> {
    @Query(value = "SELECT * FROM postulant_offert   WHERE pof_offert_id = :offert_id", nativeQuery = true)
    List<PostulantOffer> findAllByOfferId(Long offert_id);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM postulant_offert WHERE pof_offert_id = :offerId AND pof_postulant_id = :postulantId",nativeQuery = true)
    void deletePostulantOfferByPostulant(@Param("offerId")Long offerId, @Param("postulantId")Long postulantId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE postulant_offert SET pof_state = :stateId  WHERE pof_postulant_offert_id = :postulantOfferId",nativeQuery = true)
    int changeState(@Param("postulantOfferId")Long postulantOfferId, @Param("stateId")int stateId);

    @Query(value = "SELECT * FROM postulant_offert WHERE pof_postulant_id = :postulantId AND pof_state = :stateId", nativeQuery = true)
    List<PostulantOffer> finAllByStateAndPostulant(@Param("postulantId") Long postulantId, @Param("stateId")int stateId);

}
