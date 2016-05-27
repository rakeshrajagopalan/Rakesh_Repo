package chapter7.comparator;

import java.util.Comparator;

public class Message implements Comparator<Message> {

	private int messId;

	private int messTime;

	private String message;

	public int getMessId() {
		return messId;
	}

	public void setMessId(int messId) {
		this.messId = messId;
	}

	public int getMessTime() {
		return messTime;
	}

	public void setMessTime(int messTime) {
		this.messTime = messTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int compare(Message o1, Message o2) {
		if (o1.messTime > o2.messTime) {
			return 1;
		} else if (o1.messTime == o2.messTime) {
			if(o1.getMessId() > o2.getMessId()) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

}
