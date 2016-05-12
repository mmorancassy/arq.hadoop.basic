package es.hadoop.basic.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Init {
	
	// The Logger
	private static final Logger LOG = LoggerFactory.getLogger(Init.class);

	/**
	 * To run this example:
	 * 
	 * > hadoop jar arq.hadoop.basic.jar
	 * 
	 * 'fichero_de_carga.csv' previously loaded in HDFS directory:
	 * 
	 * > hadoop fs -mkdir /input
	 * > hadoop fs -put fichero_de_carga.csv /input
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml");
		LOG.info("Deploying SpringData-Hadoop MapReduce...");
		appContext.registerShutdownHook();
	}
}
