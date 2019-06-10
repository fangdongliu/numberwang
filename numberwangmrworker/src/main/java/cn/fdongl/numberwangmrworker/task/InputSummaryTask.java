package cn.fdongl.numberwangmrworker.task;

import cn.fdongl.numberwangmrworker.lineformat.LineFormat;
import org.springframework.stereotype.Component;

@Component
public class InputSummaryTask implements HiveTask{

    @Override
    public boolean accept(LineFormat lineFormat) {
        return lineFormat.getTokenFlag().get("RB")!=null;
    }

    @Override
    public boolean isSummary() {
        return true;
    }

    @Override
    public String getSQL(String tableName) {
        return "select sum(readbytes) as value from " + tableName;
    }

    @Override
    public String getName() {
        return "读入字节数";
    }

    @Override
    public String getDescription() {
        return "服务器请求的字节数统计";
    }


}
