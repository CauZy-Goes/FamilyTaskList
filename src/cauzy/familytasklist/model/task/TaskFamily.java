package cauzy.familytasklist.model.task;

import java.time.LocalDate;

import cauzy.familytasklist.model.enums.Status;
import cauzy.familytasklist.model.enums.TaskLevel;

public final class TaskFamily extends Task {

	private LocalDate deadLine;
	private TaskLevel taskLevel;
	private Status status;

	public TaskFamily(String description, LocalDate deadLine, TaskLevel taskLevel) {
		super(description);
		this.deadLine = deadLine;
		this.taskLevel = taskLevel;
		setStatus();
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
