package cm.runner;

import cm.kafka.ContainerConsumer;

public class Runner {
	
	
	public static void main(String []  args){
		
		
		new Thread(new ContainerConsumer()).start();;
		
	}

}
