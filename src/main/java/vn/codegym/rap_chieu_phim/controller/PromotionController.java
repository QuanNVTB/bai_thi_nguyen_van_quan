package vn.codegym.rap_chieu_phim.controller;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.codegym.rap_chieu_phim.model.Promotion;
import vn.codegym.rap_chieu_phim.service.PromotionService;

import java.time.LocalDate;

@Controller
@RequestMapping("/promotions")
public class PromotionController {

    private PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    // Danh sách khuyến mãi
    @GetMapping
    public String listPromotions(Model model,
                                 @RequestParam(required = false) Integer discount,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        if (discount != null || startDate != null || endDate != null) {
            model.addAttribute("promotions", promotionService.searchPromotions(discount, startDate, endDate));
        } else {
            model.addAttribute("promotions", promotionService.getAllPromotions());
        }
        return "promotions";
    }

    // Thêm mới khuyến mãi
    @GetMapping("/add")
    public String showAddForm(Promotion promotion) {
        return "add-promotion";
    }

    @PostMapping("/add")
    public String addPromotion(@Valid Promotion promotion, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-promotion";
        }
        if (!promotion.getEndDate().isAfter(promotion.getStartDate())) {
            model.addAttribute("dateError", "Thời gian kết thúc phải lớn hơn thời gian bắt đầu ít nhất 1 ngày");
            return "add-promotion";
        }
        promotionService.savePromotion(promotion);
        return "redirect:/promotions";
    }

    // Sửa khuyến mãi
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Promotion promotion = promotionService.getPromotionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid promotion Id:" + id));

        // đảm bảo không null để hiển thị form
        if (promotion.getStartDate() == null) {
            promotion.setStartDate(LocalDate.now());
        }
        if (promotion.getEndDate() == null) {
            promotion.setEndDate(LocalDate.now().plusDays(1));
        }

        model.addAttribute("promotion", promotion);
        return "edit-promotion";
    }

    @PostMapping("/edit/{id}")
    public String editPromotion(@PathVariable("id") long id, @Valid Promotion promotion,
                                BindingResult result, Model model) {
        if (promotion.getEndDate() == null || promotion.getStartDate() == null) {
            model.addAttribute("dateError", "Bạn phải nhập đầy đủ ngày bắt đầu và kết thúc");
            return promotion.getId() != null ? "edit-promotion" : "add-promotion";
        }

        if (!promotion.getEndDate().isAfter(promotion.getStartDate())) {
            model.addAttribute("dateError", "Thời gian kết thúc phải lớn hơn thời gian bắt đầu ít nhất 1 ngày");
            return promotion.getId() != null ? "edit-promotion" : "add-promotion";
        }
        promotionService.savePromotion(promotion);
        return "redirect:/promotions";
    }

    // Xóa khuyến mãi
    @GetMapping("/delete/{id}")
    public String deletePromotion(@PathVariable("id") long id, Model model) {
        promotionService.deletePromotion(id);
        return "redirect:/promotions";
    }
}
