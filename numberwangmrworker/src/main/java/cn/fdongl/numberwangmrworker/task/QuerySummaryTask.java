package cn.fdongl.numberwangmrworker.task;

import cn.fdongl.numberwangmrworker.lineformat.LineFormat;
import org.springframework.stereotype.Component;

@Component
public class QuerySummaryTask implements HiveTask{

    @Override
    public boolean accept(LineFormat lineFormat) {
        return true;
    }

    @Override
    public boolean isSummary() {
        return true;
    }

    @Override
    public String getSQL(String tableName) {
        return "select count(*) as value from " + tableName;
    }

    @Override
    public String getName() {
        return "站点访问量统计";
    }

    @Override
    public String getDescription() {
        return "统计所有请求的数量";
    }



}
