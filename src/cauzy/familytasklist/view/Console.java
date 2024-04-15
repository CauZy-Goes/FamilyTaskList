package cauzy.familytasklist.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import cauzy.familytasklist.controller.TaskList;
import cauzy.familytasklist.model.enums.AgeGroup;
import cauzy.familytasklist.model.enums.Status;
import cauzy.familytasklist.model.enums.TaskLevel;
import cauzy.familytasklist.model.family.FamilyMember;
import cauzy.familytasklist.model.task.TaskFamily;
import cauzy.familytasklist.util.FamilyPeople.ValidationFamilyPeople;
import cauzy.familytasklist.util.Task.ValidationTask;

public class Console {
	
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final Scanner scan = new Scanner(System.in);

	private final TaskList taskList;

	public Console(TaskList taskList) {
		this.taskList = taskList;
	}

	public void startConsole() {
		boolean exit = false;
		while (!exit) {
			System.out.println("\n--- TaskList Management System---");
			System.out.println("1. Add person in family");
			System.out.println("2. Remove person of family");
			System.out.println("3. List members of family");
			System.out.println("4. Filter members of family by ageGroup");
			System.out.println("5. Add task in taskList");
			System.out.println("6. Remove task of tasklist");
			System.out.println("7. Update task from tasklist");
			System.out.println("8. List tasks of tasklist");
			System.out.println("9. Filter task of tasklist by status");
			System.out.println("10. Filter task of tasklist by tasklevel");
			System.out.println("11. Filter task of tasklist by member of family");
			System.out.println("12. Sair");
			System.out.print("Choose a option: ");
			int option = scan.nextInt();
			scan.nextLine();

			switch (option) {
			case 1:
				addPerson();
				break;
			case 2:
				removePerson();
				break;
			case 3:
				listFamilyMembers();
				break;
			case 4:
				filterFamilyMembersByAgeGroup();
				break;
			case 5:
				addTask();
				break;
			case 6:
				removeTask();
				break;
			case 7:
				updateTask();
				break;
			case 8:
				listTasks();
				break;
			case 9:
				filterTaskByStatus();
				break;
			case 10:
				filterTaskByTaskLevel();
				break;
			case 11:
				filterTaskByMemberFamily();
				break;
			case 12:
				exit = true;
				break;
			default:
				System.out.println("Invalid Option");
				break;
			}
		}
		scan.close();
	}

	private void addPerson() {
		try {
			System.out.print("Name of person: ");
			String name = scan.nextLine();
			System.out.print("Age of person: ");
			int age = scan.nextInt();
			scan.nextLine();
			FamilyMember familyMember = new FamilyMember(name, age);
			if(!ValidationFamilyPeople.validateStringSizeMin(familyMember)) {
				System.out.println("invalid name, it need to have more than 3 letters !");
				return;
			}
			if(!ValidationFamilyPeople.ValidationMinAge(familyMember)) {
				System.out.println("invalid age, it can't to be less than 0 years");
				return;
			}
			taskList.addNewPerson(familyMember);
			System.out.println("Person was added");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		}
	}

	private void removePerson() {
		try {
			System.out.print("Id of person you want remove of family: ");
			taskList.removePeopleById(scan.nextInt());
			scan.nextLine();
			System.out.println("Person was removed");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		}
	}

	private void listFamilyMembers() {
		taskList.getPeopleList().forEach(System.out::println);
	}

	private void filterFamilyMembersByAgeGroup() {
		try {
			System.out.println("1 - " + AgeGroup.CHILD);
			System.out.println("2 - " + AgeGroup.TEEN);
			System.out.println("3 - " + AgeGroup.ADULT);
			System.out.print("Which AgeGroup you want to see: ");
			int option = scan.nextInt();
			scan.nextLine();
			switch (option) {
			case 1:
				taskList.getPeopleListByAgeGroup(AgeGroup.CHILD).forEach(System.out::println);
				;
				break;
			case 2:
				taskList.getPeopleListByAgeGroup(AgeGroup.TEEN).forEach(System.out::println);
				;
				break;
			case 3:
				taskList.getPeopleListByAgeGroup(AgeGroup.ADULT).forEach(System.out::println);
				;
				break;
			default:
				System.out.println("Invalid Option");
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		}
	}

	private void addTask() {
		try {
			System.out.print("Description of task: ");
			String description = scan.nextLine();
			System.out.print("DeadLine of task(dd/mm/yyyy): ");
			LocalDate date = LocalDate.parse(scan.nextLine(), fmt);
			System.out.println("1 - " + TaskLevel.EASY);
			System.out.println("2 - " + TaskLevel.MEDIUM);
			System.out.println("3 - " + TaskLevel.HARD);
			System.out.print("Which the taskLevel: ");
			int option = scan.nextInt();
			scan.nextLine();
			TaskLevel level;
			switch (option) {
			case 1:
				level = TaskLevel.EASY;
				break;
			case 2:
				level = TaskLevel.MEDIUM;
				break;
			case 3:
				level = TaskLevel.HARD;
				break;
			default:
				level  = null;
				System.out.println("Invalid Option");
				return;
			}
			System.out.println("Which member of family id will be responsible for the task: ");
			listFamilyMembers();
			FamilyMember familyMember =  taskList.getFamilyMemberById(scan.nextInt());
			scan.nextLine();
			if(familyMember == null) {
				System.out.println("Family member wasn't founded");
				return;
			}
			TaskFamily taskFamily = new TaskFamily(description, date, level, familyMember);
			
			if(ValidationTask.ValidationAssinment(taskFamily, familyMember.getAgeGroup())) {
				taskList.addNewTask(taskFamily);
				System.out.println("Task added with success !");
			} else {
				System.out.println("The familyMember don't have the requisiton to do the task");
			};
			
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date");
		}
	}

	private void removeTask() {
		try {
			listTasks();
			System.out.print("Task Id to be removed: ");
			taskList.removeTaskById(scan.nextInt());
			scan.nextLine();
			System.out.println("task removed");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		}
	}

	private void updateTask() {
		try {
			listTasks();
			System.out.print("Which task will be updated: ");
			int id = scan.nextInt();
			scan.nextLine();
			System.out.print("Description of the new task: ");
			String description = scan.nextLine();
			System.out.print("DeadLine of task(dd/mm/yyyy): ");
			LocalDate date = LocalDate.parse(scan.nextLine(), fmt);
			System.out.println("1 - " + TaskLevel.EASY);
			System.out.println("2 - " + TaskLevel.MEDIUM);
			System.out.println("3 - " + TaskLevel.HARD);
			System.out.println("Which the taskLevel: ");
			int option = scan.nextInt();
			scan.nextLine();
			TaskLevel level;
			switch (option) {
			case 1:
				level = TaskLevel.EASY;
				break;
			case 2:
				level = TaskLevel.MEDIUM;
				break;
			case 3:
				level = TaskLevel.HARD;
				break;
			default:
				level  = null;
				System.out.println("Invalid Option");
				return;
			}
			System.out.println("Which member of family id will be responsible for the task: ");
			listFamilyMembers();
			FamilyMember familyMember =  taskList.getFamilyMemberById(scan.nextInt());
			scan.nextLine();
			if(familyMember == null) {
				System.out.println("Family member wasn't founded");
				return;
			}
			TaskFamily taskFamily = new TaskFamily(description, date, level, familyMember, id);
			
			if(ValidationTask.ValidationAssinment(taskFamily, familyMember.getAgeGroup())) {
				taskList.updateTaskById(taskFamily, id);
				System.out.println("Task updated with success !");
			} else {
				System.out.println("The familyMember don't have the requisiton to do the task");
			};

		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		}
	}

	private void listTasks() {
		taskList.getTaskList().forEach(System.out::println);
	}

	private void filterTaskByStatus() {
		try {
			System.out.println("1 - " + Status.PENDING);
			System.out.println("2 - " + Status.CONCLUDED);
			System.out.println("3 - " + Status.OUT_OF_TIME);
			System.out.print("Select the stats you want to see: ");
			int option = scan.nextInt();
			scan.nextLine();
			switch (option) {
			case 1:
				taskList.getTaskListByStatus(Status.PENDING).forEach(System.out::println);
				break;
			case 2:
				taskList.getTaskListByStatus(Status.CONCLUDED).forEach(System.out::println);
				break;
			case 3:
				taskList.getTaskListByStatus(Status.OUT_OF_TIME).forEach(System.out::println);
				break;
			default:
				System.out.println("Invalid Option");
				return;
			}
			
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		}
	}

	private void filterTaskByTaskLevel() {
		try {
			System.out.println("1 - " + TaskLevel.EASY);
			System.out.println("2 - " + TaskLevel.MEDIUM);
			System.out.println("3 - " + TaskLevel.HARD);
			System.out.print("Select the stats you want to see: ");
			int option = scan.nextInt();
			scan.nextLine();
			switch (option) {
			case 1:
				taskList.getTaskListByTaskLevel(TaskLevel.EASY).forEach(System.out::println);
				break;
			case 2:
				taskList.getTaskListByTaskLevel(TaskLevel.MEDIUM).forEach(System.out::println);
				break;
			case 3:
				taskList.getTaskListByTaskLevel(TaskLevel.HARD).forEach(System.out::println);
				break;
			default:
				System.out.println("Invalid Option");
				return;
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		}
	}

	private void filterTaskByMemberFamily() {
		try {
			listFamilyMembers();
			System.out.print("which the member family id you want ot see:");
			int id = scan.nextInt();
			scan.nextLine();
			taskList.getTaskListByMemberFamilyId(id).forEach(System.out::println);
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		}
	}

}
