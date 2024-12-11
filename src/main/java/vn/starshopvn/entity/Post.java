package vn.starshopvn.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Posts")
@NamedQuery(name = "post.findAll", query = "select p from Post p")
public class Post {

	@Id
	@Column(name = "poid", columnDefinition = "nvarchar(255)")
	private String poid;
	
	@Column(name = "createdat", columnDefinition = "timestamp")
	private Timestamp createdat;
	
	@Column(name = "genre", columnDefinition = "nvarchar(255)")
	private String genre;
	
	@Column(name = "title", columnDefinition = "nvarchar(255)")
	private String title;
	
	@Column(name = "author", columnDefinition = "nvarchar(255)")
	private String author;
	
	@Column(name = "content", columnDefinition = "nvarchar(5000)")
	private String content;
	
	@Column(name = "image", columnDefinition = "nvarchar(255)")
	private String image;
	
	@Column(name = "tag", columnDefinition = "nvarchar(255)")
	private String tag;
	
	@Column(name = "iscensored", columnDefinition = "boolean")
	private boolean iscensored;
	
	@ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Account account;
}