package cauzy.familytasklist.model.task;

import java.time.LocalDate;

import cauzy.familytasklist.model.enums.Status;
import cauzy.familytasklist.model.enums.TaskLevel;
import cauzy.familytasklist.model.family.FamilyMember;

public final class TaskFamily extends Task {

	private LocalDate deadLine;
	private TaskLevel taskLevel;
	private Status status;
	private FamilyMember familyMember;

	public TaskFamily(String description, LocalDate deadLine, TaskLevel taskLevel, FamilyMember familyMember) {
		super(description);
		this.deadLine = deadLine;
		this.taskLevel = taskLevel;
		this.familyMember = familyMember;
		setStatus();
	}
	
	
	
	@Override
	public String toString() {
		switch(status) {
		case PENDING :
			return id + " - " + description + " need to do by " + familyMember.getName() + " until " + deadLine + " Status " + status;
		case CONCLUDED :
			return id + " - " + description + " was conclused by " + familyMember.getName() + " Status " + status;
		case OUT_OF_TIME :
			return id + " - " + description + " need to do by " + familyMember.getName() + " Status " + status;
		default : 
			return "erro";
		}
	}



	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	private void setStatus() {
		if (deadLine.isAfter(LocalDate.now())) {
			status = Status.PENDING;
		} else {
			status = Status.OUT_OF_TIME;
		}
	}
	

	public void concludeTask() {
		status = Status.CONCLUDED;
	}

	public Status getStatus() {
		return status;
	}

	public LocalDate getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(LocalDate deadLine) {
		this.deadLine = deadLine;
	}

	public TaskLevel getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(TaskLevel taskLevel) {
		this.taskLevel = taskLevel;
	}
}
