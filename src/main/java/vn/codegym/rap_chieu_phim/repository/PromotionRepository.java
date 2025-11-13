package vn.codegym.rap_chieu_phim.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.rap_chieu_phim.model.Promotion;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository <Promotion, Long>{

    List<Promotion> findByDiscountGreaterThanEqual(int discount);
    List<Promotion> findByStartDateAfter(LocalDate startDate);
    List<Promotion> findByEndDateBefore(LocalDate endDate);

    Page<Promotion> findByDiscountGreaterThanEqual(int discount, Pageable pageable);

}
