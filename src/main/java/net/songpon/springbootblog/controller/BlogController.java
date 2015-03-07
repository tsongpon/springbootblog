package net.songpon.springbootblog.controller;

import net.songpon.springbootblog.dao.BlogRepository;
import net.songpon.springbootblog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {

    private BlogRepository blogRepository;

    //Let spring inject BlogRepository for us
    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Blog> getAllBlog() {
        return blogRepository.listBlogs();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Blog getBlogById(@PathVariable("id") Long id) {
        return blogRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewBlog(@RequestBody Blog blog) {
        blogRepository.save(blog);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBlog(@PathVariable("id") Long id) {
        blogRepository.deleted(id);
    }
}
