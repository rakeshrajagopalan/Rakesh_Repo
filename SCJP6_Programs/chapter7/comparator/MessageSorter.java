package chapter7.comparator;

import java.util.Set;
import java.util.TreeSet;

public class MessageSorter {

	private static Set<Message> messageList = new TreeSet<>();
	
	private MessageSorter() {}
	
	public static void addMessage(Message newMessage) {
		messageList.add(newMessage);
	}
	
	public static void flushMessage() {
		
	}
 	
}
