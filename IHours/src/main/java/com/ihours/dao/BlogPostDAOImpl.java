package com.ihours.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihours.model.BlogComment;
import com.ihours.model.BlogPost;

@SuppressWarnings("deprecation")
@Repository
public class BlogPostDAOImpl implements BlogPostDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	private Session session;
	
	public boolean createBlogPost(BlogPost post) {
	
		session=sessionFactory.openSession();
		session.saveOrUpdate(post);
		Transaction tx=session.beginTransaction();
		tx.commit();
		session.clear();
		session.close();	
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BlogPost> list(int approved) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from BlogPost where approved="+approved);
		List<BlogPost> blogPosts=query.list();
		session.close();
		return blogPosts;
	}

	public List<BlogPost> findLatest5Post() {
		// TODO Auto-generated method stub
		return null;
	}

	public BlogPost findPostById(int id) {
	
		Session session=sessionFactory.openSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
		session.close();
		return blogPost;
	}

	@Transactional
    public void updateBlogPost(BlogPost blogPost) {
		
    	Session session=sessionFactory.openSession();
		session.update(blogPost);
}

	   public void deleteById(int id) {
		session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		BlogPost post = session.byId(BlogPost.class).load(id);
		session.delete(post);
		tx.commit();
		session.clear();
		session.close();	
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BlogComment> getBlogComments(int blogId) {
		   Session session=sessionFactory.openSession();
		   Query query=session.createQuery("from BlogComment where blogPost.id="+blogId);
		    
		     List<BlogComment> blogComments=query.list();
		     System.out.println("blogComments");
		     session.close();
		     return blogComments;
	}

	public void addBlogComment(BlogComment blogComment) {
		Session session=sessionFactory.openSession();
		session.save(blogComment);
		session.flush();
		session.close();
		
	}

}
