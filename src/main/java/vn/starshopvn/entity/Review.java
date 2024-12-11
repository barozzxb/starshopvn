package vn.starshopvn.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Review {

    @Id
    @Column(name = "reviewid", columnDefinition = "varchar(255)")
    private String reviewId; 

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment", columnDefinition = "nvarchar(5000)", nullable = false)
    private String comment; 

    @Column(name = "mediaurl", columnDefinition = "varchar(1000)")
    private String mediaUrl; 
    
    @Column(name = "createdat", columnDefinition = "timestamp", nullable = false)
    private Timestamp createdAt;
    
    @Column(name = "isvideo", columnDefinition = "boolean default false")
    private boolean isVideo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", nullable = false)
    private Product product; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private Account account;
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = new Timestamp(System.currentTimeMillis());
        }
    }
}