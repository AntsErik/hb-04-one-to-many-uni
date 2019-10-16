package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCoursesDemo {

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

			//create a course
			Course theCourse = new Course("Magic The Gathering - cardgame or a cult?");
			
			//add some reviews to the course
			theCourse.addReview(new Review("Interesting piece of writing, very educating!"));
			theCourse.addReview(new Review("Waste of time, will never get those 2 hours back!"));
			theCourse.addReview(new Review("At half way through realized I was in the wrong room!"));
			theCourse.addReview(new Review("Amazing!! Had never heard of MTG. Will get all my housemates involved!"));
			
			//save the course and use cascade to leverage saving revies as well
			System.out.println("\n\nsaving the course!");
			System.out.println("\n\n" + theCourse);
			System.out.println("\n\n" + theCourse.getReviews());
			session.save(theCourse);
			
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
