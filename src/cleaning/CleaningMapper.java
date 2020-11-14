import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleaningMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        /*
        * Per row, we take several preprocessing steps:
        *   (1) Remove all entries where [bot] is the creator of the pull request
        *   (2) Remove all entries where any column is null
        *   (3) Remove header row entries created by my importing script
        *
        * Next, we map each entry to a day entry with the same repository name.
        * This will allow us to reduce our data to day granularity in the
        * reducer step.
        * */

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

            // Extract the columns into variables
            String type = columns[0];
            String name = columns[1];
            String time = columns[2];
            String url = columns[4];

            /*
            * Times are in the format:
            *   2020-08-01 20:50:55 UTC
            *
            * We set all times to midnight on the day the event occurred.
            * */
            String truncatedTime = time.split(" ")[0] + " 00:00:00 UTC";

            // Comma separated key: "repoName,event,url,time"
            String mappedKey = name + "," + type + "," + url + "," + truncatedTime;


            context.write(new Text(mappedKey), new IntWritable(1));
        }

    }
}
