package com.iemconnect.studentlog.validation;

import com.iemconnect.studentlog.exception.UserAlreadyResisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iemconnect.studentlog.dao.StudentDao;
import com.iemconnect.studentlog.model.Student;
import com.iemconnect.studentlog.service.StudentService;

@Component
public class RegistrationValidate {
	@Autowired
	StudentDao studentDao;
	
	public void validate(Student student) {
		if(studentDao.findByEnrollmentIgnoreCase(student.getEnrollment()).isPresent())
		{
			throw new UserAlreadyResisteredException("you enrollment already has been registered "+ student.getEnrollment()+":"+ student.getEmail());
		}
		if(studentDao.findByEmailIgnoreCase(student.getEmail()).isPresent()) {
			throw new UserAlreadyResisteredException("you email already has been registered "+ student.getEnrollment()+":"+ student.getEmail());
		}
		if(studentDao.findByUserName(student.getUserName()).isPresent()) {
			throw new UserAlreadyResisteredException("user_name already has been registered "+ student.getEnrollment()+":"+ student.getEmail());

		}
	}
}
