package org.nerv.testrabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @see <a href="http://www.rabbitmq.com/tutorials/tutorial-one-java.html">http://www.rabbitmq.com/tutorials/tutorial-one-java.html</a>
 * @author Yuqi Chou 
 * @version Oct 7, 2012  9:47:15 PM
 */
public class Send {
	
	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
	    String message = "Hello World!";
	    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		
		
	    message = "Hello World Second!";
	    channel.basicPublish("", 
	    					QUEUE_NAME, 
	    					null, 
	    					message.getBytes());
	    
	    channel.close();
	    connection.close();
	    
	    System.out.println("end!");
	}
}
