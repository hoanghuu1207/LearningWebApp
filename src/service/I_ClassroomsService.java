package service;

import java.util.ArrayList;

import model.ClassroomsModel;

public interface I_ClassroomsService {
	
	ArrayList<ClassroomsModel> getClassroomsByStudentId(int studentId);
	
	ClassroomsModel selectById(int id);
}
