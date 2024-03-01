package com.iemconnect.studentlog.service;

import java.util.Date;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemconnect.studentlog.dao.StudentDao;
import com.iemconnect.studentlog.exception.UserAlreadyResisteredException;
import com.iemconnect.studentlog.exception.UserNotFoundCustomException;
import com.iemconnect.studentlog.model.Student;
import com.iemconnect.studentlog.model.StudentEntity;
import com.iemconnect.studentlog.validation.RegistrationValidate;
import com.iemconnect.studentlog.validation.StudentValidate;

@Service
public class StudentService {
	
	@Autowired
	private StudentDao studentDao;
	@Autowired
	StudentValidate studentValidate;
	@Autowired 
	RegistrationValidate registrationValidate;

	public StudentEntity createStudent(Student studentBean) throws UserNotFoundCustomException {

		try {
			studentValidate.validate(studentBean.getEnrollment(),studentBean.getEmail());
			registrationValidate.validate(studentBean);
			StudentEntity student = new StudentEntity();
            setAuditFields(student,studentBean);
            mapStudentBeanToStudentEntity(student,studentBean);

				return studentDao.save(student);
		} catch (Exception e){
				System.out.println("exception ::::: " + e.getMessage());
				throw e;
		}
	}

	private void setAuditFields(StudentEntity student,Student studentbean) {
		Date now =new Date();
		student.setCreatedAt(now);
		student.setCreatedBy(studentbean.getName());
		student.setLastUpdatedBy(studentbean.getName());
		student.setLastUpdatedAt(now);
		
	}

	private void mapStudentBeanToStudentEntity(StudentEntity student, Student studentBean) {
		student.setName(studentBean.getName());
        student.setEnrollment(studentBean.getEnrollment());
        student.setEmail(studentBean.getEmail());
        student.setCurryear(studentBean.getCurryear());
        student.setBranch(studentBean.getPassword());
        student.setPhone(studentBean.getPhone());
        student.setSemester(studentBean.getSemester());
        student.setUserName(studentBean.getUserName());
		student.setPassword(studentBean.getPassword());
	}

	
	
	
}
