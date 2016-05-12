package es.hadoop.basic.mapper;

import java.io.IOException;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirQualityMapper extends Mapper<Object, Text, Text, DoubleWritable> {

	// The Logger
	private static final Logger LOG = LoggerFactory.getLogger(AirQualityMapper.class);
	
	// CSV Separator
	private static final String SEPARATOR = ";";
	
	/**
	 * @param value - Represents single lines from csv file
	 * 
	 */
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
		
		LOG.info("map function - INI");
		
		final String[] values = value.toString().split(SEPARATOR);
		
		final String co = format(values[1]);
		final String province = format(values[10]);
		
		LOG.info("CO value in " + province + " is: " + co );

		if (NumberUtils.isNumber(co.toString())) {
			context.write(new Text(province), new DoubleWritable(NumberUtils.toDouble(co)));
						
		}	
		
		LOG.info("map function - END");

	}
	
	private String format(String value) {
		return value.trim();
	}

}
