package edu.uom.emailservice;

public class NewUserReceiver {

	public void receiveMessage(String message) {
		System.out.println("Received: " + message);
		String[] split = message.split("\\|");
		System.out.println(String.format("Sending email to user [%s] on [%s]", split[1], split[2]));
	}
}
