package org.nerv.testrabbitmq.workqueues;

import org.junit.Before;
import org.junit.Test;

public class TestWorker {
	
	@Before
	public void startWorker() throws Exception{
		
		
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Worker.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Worker.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
	}
	
	@Test
	public void sendOne() throws Exception{
		NewTask.main(new String[]{"First message."});
		NewTask.main(new String[]{"Second message.."});
		NewTask.main(new String[]{"Third message..."});
		NewTask.main(new String[]{"Fourth message...."});
		NewTask.main(new String[]{"Fifth message....."});
		
		System.out.println("message send over!!!");
		
		Thread.currentThread().join();
	}
	
	
	
}
