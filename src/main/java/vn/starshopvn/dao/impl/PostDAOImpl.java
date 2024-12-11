package vn.starshopvn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.PostDAO;
import vn.starshopvn.entity.Post;

public class PostDAOImpl implements PostDAO {

    @Override
    public List<Post> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Post> list = enma.createNamedQuery("post.findAll", Post.class);

        List<Post> posts = list.getResultList();

        // Kiểm tra và xử lý content của mỗi bài viết
        posts = posts.stream()
                     .map(post -> {
                         if (post.getContent() == null) {
                             post.setContent("Content not available.");
                         }
                         return post;
                     })
                     .toList();

        System.out.println("Genres fetched from database: " + posts);
        return posts;
    }
}
