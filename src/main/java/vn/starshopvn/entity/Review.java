package vn.starshopvn.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "review.findAll", query = "select r from Review r")
public class Review {

    @Id
    @Column(name = "reviewId", columnDefinition = "varchar(255)")
    private String reviewId; 

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment", columnDefinition = "nvarchar(MAX)", nullable = false)
    private String comment; 

    @Column(name = "mediaUrl", columnDefinition = "varchar(MAX)")
    private String mediaUrl; 
    
    @Column(name = "createdAt", columnDefinition = "datetime2", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "isVideo", columnDefinition = "boolean default false")
    private boolean isVideo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", nullable = false)
    private Product product; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private Account account; // Người dùng đã mua sản phẩm và đánh giá
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
