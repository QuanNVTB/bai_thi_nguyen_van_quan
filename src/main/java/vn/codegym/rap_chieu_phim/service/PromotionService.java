package vn.codegym.rap_chieu_phim.service;

import org.springframework.stereotype.Service;
import vn.codegym.rap_chieu_phim.model.Promotion;
import vn.codegym.rap_chieu_phim.repository.PromotionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    private PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion savePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public Optional<Promotion> getPromotionById(Long id) {
        return promotionRepository.findById(id);
    }

    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }


    // Tìm theo điều kiện đơn giản
    public List<Promotion> searchPromotions(Integer discount, LocalDate startDate, LocalDate endDate) {
        List<Promotion> results = promotionRepository.findAll();
        if (discount != null) results.retainAll(promotionRepository.findByDiscountGreaterThanEqual(discount));
        if (startDate != null) results.retainAll(promotionRepository.findByStartDateAfter(startDate));
        if (endDate != null) results.retainAll(promotionRepository.findByEndDateBefore(endDate));
        return results;
    }
}
