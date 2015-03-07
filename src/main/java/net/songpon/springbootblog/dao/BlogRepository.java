package net.songpon.springbootblog.dao;

import net.songpon.springbootblog.domain.Blog;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
@Repository
public class BlogRepository {
    //This is for demo purpose DO NOT use in production code!!
    private static Map<Long, Blog> blogRepo = new ConcurrentHashMap<>();
    private static Long keyHolder = 1L;

    public BlogRepository() {
        Blog javaBlog = new Blog();
        javaBlog.setId(keyHolder++);
        javaBlog.setTitle("Java is cool");
        javaBlog.setText("How java cool");
        javaBlog.setAuthor("tum");

        Blog springBlog = new Blog();
        springBlog.setId(keyHolder++);
        springBlog.setTitle("Spring is cool");
        springBlog.setText("What is spring framework");
        springBlog.setAuthor("tum");

        blogRepo.put(javaBlog.getId(), javaBlog);
        blogRepo.put(springBlog.getId(), springBlog);
    }

    public Blog save(Blog blog) {
        if (blog.getId() == null) {
            blog.setId(keyHolder++);
        }
        blogRepo.put(blog.getId(), blog);

        return blog;
    }

    public List<Blog> listBlogs() {
        return new ArrayList<Blog>(blogRepo.values());
    }

    public Blog findById(Long id) {
        return blogRepo.get(id);
    }

    public void deleted(Long id) {
        blogRepo.remove(id);
    }
}
