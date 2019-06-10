package cn.fdongl.numberwangmrworker.task;

import cn.fdongl.numberwangmrworker.lineformat.LineFormat;
import org.springframework.stereotype.Component;

@Component
public class TimeCountTask implements HiveTask{

    @Override
    public boolean accept(LineFormat lineFormat) {
        return lineFormat.getTokenFlag().get("IP")!=null;
    }

    @Override
    public boolean isSummary() {
        return false;
    }

    @Override
    public String getSQL(String tableName) {
        return "select `start` as `time`,count(*) as `count` from " + tableName + " group by `start`";
    }

    @Override
    public String getName() {
        return "time";
    }

    @Override
    public String getDescription() {
        return "请求的时间分布";
    }

    @Override
    public String getSchema() {
        return "time,count";
    }

    @Override
    public String getShow() {
        return "pie";
    }


}
