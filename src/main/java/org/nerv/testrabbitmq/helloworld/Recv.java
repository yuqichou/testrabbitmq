package org.nerv.testrabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @see <a href="http://www.rabbitmq.com/tutorials/tutorial-one-java.html">http://www.rabbitmq.com/tutorials/tutorial-one-java.html</a>
 * @author Yuqi Chou 
 * @version Oct 7, 2012  9:44:31 PM
 */
public class Recv {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);

		while (true) {
			System.out.println("blocking");
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			System.out.println("delivery");
			String message = new String(delivery.getBody());
			System.out.println(" [x] Received '" + message + "'");
		}

	}
}
