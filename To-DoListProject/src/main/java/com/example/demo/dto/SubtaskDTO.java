package com.example.demo.dto;


public class SubtaskDTO {

	private int subtaskid;

	private int taskid;

	private String subtask;

	private String description;

	private String deadline;

	private String priority;

	private Boolean done;

	public SubtaskDTO() {

	}

	public SubtaskDTO(int taskid, String subtask, String description, String deadline, String priority,
			Boolean done) {
		this.taskid = taskid;
		this.subtask = subtask;
		this.description = description;
		this.deadline = deadline;
		this.priority = priority;
		this.done = done;
	}

	public int getSubtaskid() {
		return subtaskid;
	}

	public void setSubtaskid(int subtaskid) {
		this.subtaskid = subtaskid;
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public String getSubtask() {
		return subtask;
	}

	public void setSubtask(String subtask) {
		this.subtask = subtask;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "SubtaskDTO [subtaskid=" + subtaskid + ", taskid=" + taskid + ", subtask=" + subtask + ", description="
				+ description + ", deadline=" + deadline + ", priority=" + priority + ", done=" + done + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((done == null) ? 0 : done.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + subtaskid;
		result = prime * result + ((subtask == null) ? 0 : subtask.hashCode());
		result = prime * result + taskid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubtaskDTO other = (SubtaskDTO) obj;
		if (deadline == null) {
			if (other.deadline != null)
				return false;
		} else if (!deadline.equals(other.deadline))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (done == null) {
			if (other.done != null)
				return false;
		} else if (!done.equals(other.done))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (subtaskid != other.subtaskid)
			return false;
		if (subtask == null) {
			if (other.subtask != null)
				return false;
		} else if (!subtask.equals(other.subtask))
			return false;
		if (taskid != other.taskid)
			return false;
		return true;
	}

}
