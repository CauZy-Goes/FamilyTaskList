package cauzy.familytasklist.controller;

import java.util.ArrayList;
import java.util.List;

import cauzy.familytasklist.model.family.FamilyMember;
import cauzy.familytasklist.model.task.TaskFamily;

public class TaskList {
	
	List<FamilyMember> peopleList = new ArrayList<>();
	List<TaskFamily> taskList = new ArrayList<>();
	
	public List<FamilyMember> getPeopleList() {
		return peopleList;
	}
	
	public List<TaskFamily> getTaskList() {
		return taskList;
	}
	
	public void addNewPeople(FamilyMember people) {
		peopleList.add(people);
	}
	
	public void addNewTask(TaskFamily task) {
		taskList.add(task);
	}
	
}
