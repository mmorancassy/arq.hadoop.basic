package es.hadoop.basic.reducer;

import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirQualityReducer extends Reducer<Text, DoubleWritable, Text, Text> {

	// The Logger
	private static final Logger LOG = LoggerFactory.getLogger(AirQualityReducer.class);
	
	private final DecimalFormat df = new DecimalFormat("#.##");
	
	/**
	 * @param values - mapper values
	 * 
	 */
	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> values, Reducer<Text, DoubleWritable, Text, Text>.Context context) throws IOException, InterruptedException {
		
		LOG.info("reduce function - INI");
		
		int measures = 0;
		double totalCo = 0.0f;

		for (DoubleWritable coValue : values) {
			totalCo += coValue.get();
			measures++;
		}

		if (measures > 0) {
			context.write(key, new Text(df.format(totalCo / measures)));
		}
		
		LOG.info("reduce function - END");
	}
	
}
