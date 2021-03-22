package com.example.demo.data.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String task;

	@NotNull
	private String description;

	
	private String deadline;

	@NotNull
	private String priority;

	@NotNull
	private Boolean done;
	
	@OneToMany(mappedBy = "taskLink", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List <Subtask> subtaskLink;

	public Task() {

	}

	public Task(String task, String description, String deadline, String priority, Boolean done) {
		this.task = task;
		this.description = description;
		this.deadline = deadline;
		this.priority = priority;
		this.done = done;
	}
	
	

	public Task(int id, String task, String description, String deadline,
			 String priority, Boolean done, List<Subtask> subtaskLink) {
		super();
		this.id = id;
		this.task = task;
		this.description = description;
		this.deadline = deadline;
		this.priority = priority;
		this.done = done;
		this.subtaskLink = subtaskLink;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
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
	
	

	public List<Subtask> getSubtaskLink() {
		return subtaskLink;
	}

	public void setSubtaskLink(List<Subtask> subtaskLink) {
		this.subtaskLink = subtaskLink;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((done == null) ? 0 : done.hashCode());
		result = prime * result + id;
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((subtaskLink == null) ? 0 : subtaskLink.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
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
		Task other = (Task) obj;
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
		if (id != other.id)
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (subtaskLink == null) {
			if (other.subtaskLink != null)
				return false;
		} else if (!subtaskLink.equals(other.subtaskLink))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", task=" + task + ", description=" + description + ", deadline=" + deadline
				+ ", priority=" + priority + ", done=" + done + ", subtaskLink=" + subtaskLink + "]";
	}

	
}
