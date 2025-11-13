package vn.codegym.rap_chieu_phim.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Tiêu đề là bắt buộc")
    @Column(name = "title", nullable = false)
    private String title;

    @Future(message = "Thời gian bắt đầu phải lớn hơn thời gian hiện tại")
    @Column(name = "startdate", nullable = false)
    private LocalDate startDate;

    @Future(message = "Thời gian kết thúc phải lớn hơn thời gian bắt đầu")
    @Column(name = "enddate", nullable = false)
    private LocalDate endDate;

    @Min(value = 10000, message = "Mức giảm giá phải lớn hơn 10.000 VNĐ")
    @Column(name = "discount", nullable = false)
    private int discount;

    @NotBlank(message = "Chi tiết là bắt buộc")
    @Column(name = "detail", nullable = false)
    private String detail;

    public Promotion() {
    }

    public Promotion(Long id, String title, LocalDate startDate, LocalDate endDate, int discount, String detail) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Tiêu đề là bắt buộc") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Tiêu đề là bắt buộc") String title) {
        this.title = title;
    }

    public @Future(message = "Thời gian bắt đầu phải lớn hơn thời gian hiện tại") LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@Future(message = "Thời gian bắt đầu phải lớn hơn thời gian hiện tại") LocalDate startDate) {
        this.startDate = startDate;
    }

    public @Future(message = "Thời gian kết thúc phải lớn hơn thời gian bắt đầu") LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@Future(message = "Thời gian kết thúc phải lớn hơn thời gian bắt đầu") LocalDate endDate) {
        this.endDate = endDate;
    }

    public @Min(value = 10000, message = "Mức giảm giá phải lớn hơn 10.000 VNĐ") int getDiscount() {
        return discount;
    }

    public void setDiscount(@Min(value = 10000, message = "Mức giảm giá phải lớn hơn 10.000 VNĐ") int discount) {
        this.discount = discount;
    }

    public @NotBlank(message = "Chi tiết là bắt buộc") String getDetail() {
        return detail;
    }

    public void setDetail(@NotBlank(message = "Chi tiết là bắt buộc") String detail) {
        this.detail = detail;
    }
}


