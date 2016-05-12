package es.hadoop.basic.manager;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.hadoop.basic.mapper.AirQualityMapper;
import es.hadoop.basic.reducer.AirQualityReducer;

public class AirQualityManager extends Configured implements Tool {

	// The Logger
	private static final Logger LOG = LoggerFactory.getLogger(AirQualityManager.class);
			
	@Override
	public int run(String[] args) throws Exception {
		final Job job = Job.getInstance(getConf()); 
		
		if (args.length != 2) {
			LOG.error("AirQualityManager needs two params: 1- input file path, 2- output dir path");
			
			// Error!
			System.exit(-1);
		}
		
		// The Job
		job.setJarByClass(AirQualityManager.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		// Map/Reduce classes
		job.setMapperClass(AirQualityMapper.class);
		job.setReducerClass(AirQualityReducer.class);
		
		// Input/Output Map/Reduce formats
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
		
		// Successful!
		return 0;
	}
	
	/**
	 * To run this example:
	 * 
	 * > hadoop jar arq.hadoop.basic.jar es.hadoop.basic.manager.AirQualityManager /input/fichero_de_carga.csv /output
	 * 
	 * 'fichero_de_carga.csv' previously loaded in HDFS directory:
	 * 
	 * > hadoop fs -mkdir /input
	 * > hadoop fs -put fichero_de_carga.csv /input
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// args - contains csv input file path
		ToolRunner.run(new AirQualityManager(), args);
	}

}
