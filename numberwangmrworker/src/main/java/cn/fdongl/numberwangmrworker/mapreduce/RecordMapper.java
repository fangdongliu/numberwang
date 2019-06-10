package cn.fdongl.numberwangmrworker.mapreduce;

import cn.fdongl.numberwangmrworker.lineformat.LineFormat;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RecordMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    public static LineFormat lineFormat;

    @Override
    protected void map(LongWritable key, Text value, Context context){
        try {
            context.write(new Text(lineFormat.read(value.toString())),NullWritable.get());
        } catch (Exception e) {}
    }
}
