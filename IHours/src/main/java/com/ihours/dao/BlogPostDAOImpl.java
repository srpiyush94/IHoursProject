package com.ihours.dao;

import java.util.List;

import org.hibernate.*;
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
	
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(post);
		tx.commit();
		session.clear();
	
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


    public void updateBlogPost(BlogPost blogPost) {
		System.out.println(blogPost.getApproved()+"BlogPostImpl");
    	/*Session session=sessionFactory.openSession();
		session.update(blogPost);
		*/
		session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		session.update(blogPost);
		tx.commit();
		session.clear();
		session.close();
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
		   
		   Transaction tx=session.beginTransaction();
		   
		   Query query=session.createQuery("from BlogComment where blogPost.id="+blogId);
		    
		     List<BlogComment> blogComments=query.list();
		     System.out.println("blogComments");
		     tx.commit();
		     session.close();
		     return blogComments;
	}

	public boolean  addBlogComment(BlogComment blogComment) {

		session=sessionFactory.openSession();
	
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(blogComment);
		tx.commit();
		session.clear();
		session.close();	
		return true;
		
	}

}
