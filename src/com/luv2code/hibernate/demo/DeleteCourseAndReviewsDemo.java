package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class DeleteCourseAndReviewsDemo {

	public static void main(String [] args){
		
//		//Adding standard log4j.properties
//		BasicConfigurator.configure();
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			//start a transaction
			session.beginTransaction();
			
			//get the course
			int theId = 10;
			Course theCourse = session.get(Course.class,  theId);
			
			//print the course
			System.out.println("\n\nDeleting the course...");
			System.out.println(theCourse);
			
			//print out the course reviews
			System.out.println(theCourse.getReviews());
			
			//delete the course
			session.delete(theCourse);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("\n\nDone!");
		}
		finally {
			
			//add cleanup mode
			session.close();
			
			factory.close();
		}
	}
}
