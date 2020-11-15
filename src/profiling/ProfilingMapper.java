import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ProfilingMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        if (value.toString().contains("type,name,time,user,url")) {
            /*
             * If this is a header, there is no processing to be performed;
             * we do not want it in our output.
             * */

            return;
        } else {
            String line = value.toString();
            String[] columns = line.split(",");

            // We receive an unexpected number of columns
            if(columns.length != 5) return;

            // Extract the repository column into a variable
            String name = columns[1];

            context.write(new Text(name), new IntWritable(1));
        }

    }
}
