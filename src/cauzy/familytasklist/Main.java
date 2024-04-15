package cauzy.familytasklist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cauzy.familytasklist.controller.TaskList;
import cauzy.familytasklist.model.enums.TaskLevel;
import cauzy.familytasklist.model.family.FamilyMember;
import cauzy.familytasklist.model.task.TaskFamily;
import cauzy.familytasklist.view.Console;

public class Main {

	public static void main(String[] args) {
		TaskList taskList = new TaskList();
		Thread reminder = new Thread(taskList);
		Console console = new Console(taskList);
		inicialFamily(taskList);
		reminder.start();
		console.startConsole();
		reminder.interrupt();
	}

	private static void inicialFamily(TaskList taskList) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		taskList.addNewPerson(new FamilyMember("Robert", 35));
		taskList.addNewPerson(new FamilyMember("Samantha", 30));
		taskList.addNewPerson(new FamilyMember("Wilian", 2));
		taskList.addNewPerson(new FamilyMember("Natan", 15));
		taskList.addNewTask( new TaskFamily("Clear the roons", LocalDate.parse("23/12/2024",fmt), TaskLevel.MEDIUM, taskList.getFamilyMemberById(3)));
		taskList.addNewTask( new TaskFamily("Clear the kitchen", LocalDate.parse("23/12/2023",fmt), TaskLevel.MEDIUM, taskList.getFamilyMemberById(1)));
		taskList.addNewTask( new TaskFamily("Clear the garage", LocalDate.parse("23/12/2030",fmt), TaskLevel.MEDIUM , taskList.getFamilyMemberById(3)));
		taskList.addNewTask( new TaskFamily("Wash the car", LocalDate.parse("23/12/2032",fmt), TaskLevel.MEDIUM, taskList.getFamilyMemberById(0)));
		taskList.addNewTask( new TaskFamily("Go to supermarket", LocalDate.parse("23/12/2020",fmt), TaskLevel.HARD, taskList.getFamilyMemberById(0)));
		taskList.addNewTask( new TaskFamily("Storage the toys", LocalDate.parse("23/12/2015",fmt), TaskLevel.EASY, taskList.getFamilyMemberById(2)));
		taskList.addNewTask( new TaskFamily("Take Natan to doctor", LocalDate.parse("23/12/2010",fmt), TaskLevel.HARD, taskList.getFamilyMemberById(1)));
		taskList.addNewTask( new TaskFamily("Call to eletricist", LocalDate.parse("23/12/2039",fmt), TaskLevel.MEDIUM, taskList.getFamilyMemberById(0)));
	}
}
