package cauzy.familytasklist.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cauzy.familytasklist.model.enums.AgeGroup;
import cauzy.familytasklist.model.enums.Status;
import cauzy.familytasklist.model.enums.TaskLevel;
import cauzy.familytasklist.model.family.FamilyMember;
import cauzy.familytasklist.model.task.TaskFamily;

public class TaskList implements Runnable {

	List<FamilyMember> peopleList = new ArrayList<>();
	List<TaskFamily> taskList = new ArrayList<>();

	public List<FamilyMember> getPeopleList() {
		return peopleList;
	}

	public FamilyMember getFamilyMemberById(int id) {
		return peopleList.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
	}

	public List<FamilyMember> getPeopleListByAgeGroup(AgeGroup ageGroup) {
		return peopleList.stream().filter(people -> people.getAgeGroup() == ageGroup).collect(Collectors.toList());
	}

	public List<TaskFamily> getTaskList() {
		return taskList;
	}

	public List<TaskFamily> getTaskListByStatus(Status status) {
		return taskList.stream().filter(task -> task.getStatus() == status).collect(Collectors.toList());
	}

	public List<TaskFamily> getTaskListByTaskLevel(TaskLevel taskLevel) {
		return taskList.stream().filter(task -> task.getTaskLevel() == taskLevel).collect(Collectors.toList());
	}

	public List<TaskFamily> getTaskListByMemberFamilyId(int id) {
		return taskList.stream().filter(task -> task.getFamilyMember().getId() == id).collect(Collectors.toList());
	}

	public void addNewPerson(FamilyMember person) {
		peopleList.add(person);
	}

	public void addNewTask(TaskFamily task) {
		taskList.add(task);
	}

	public void updateTaskById(TaskFamily newTaskFamily, int id) {
		TaskFamily taskFamily = taskList.stream().filter(task -> id == task.getId()).findFirst().orElse(null);
		if (taskFamily != null) {
			removeTaskById(taskFamily.getId());
			addNewTask(newTaskFamily);
		} else {
			System.out.println("Task wasn't founded");
		}
	}

	public void concludeTaskById(int id) {
		TaskFamily taskFamily = taskList.stream().filter(task -> id == task.getId()).findFirst().orElse(null);
		if (taskFamily != null) {
			removeTaskById(taskFamily.getId());
			taskFamily.concludeTask();
			addNewTask(taskFamily);
		} else {
			System.out.println("Task wasn't founded");
		}
	}

	public void removePeopleById(int id) {
		peopleList = peopleList.stream().filter(people -> people.getId() != id).collect(Collectors.toList());
	}

	public void removeTaskById(int id) {
		taskList = taskList.stream().filter(task -> task.getId() != id).collect(Collectors.toList());
	}

	public void reminderSystem() {
		List<TaskFamily> list = getTaskListByStatus(Status.PENDING);
		for (TaskFamily task : list) {
			System.out.println(task.getId() + " - " + task.getDescription() + " will expire in "
					+ LocalDate.now().until(task.getDeadLine(), ChronoUnit.DAYS) + " days");
		}
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			System.out.println();
			reminderSystem();
			try {
				Thread.sleep(1000 * 30);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
