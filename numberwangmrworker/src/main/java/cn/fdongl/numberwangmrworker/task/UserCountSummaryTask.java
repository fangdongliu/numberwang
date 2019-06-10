package cn.fdongl.numberwangmrworker.task;

import cn.fdongl.numberwangmrworker.lineformat.LineFormat;
import org.springframework.stereotype.Component;

@Component
public class UserCountSummaryTask implements HiveTask{

    @Override
    public boolean accept(LineFormat lineFormat) {
        return lineFormat.getTokenFlag().get("SD")!=null;
    }

    @Override
    public boolean isSummary() {
        return true;
    }

    @Override
    public String getSQL(String tableName) {
        return "select count(*) as value from " + tableName+" group by session";
    }

    @Override
    public String getName() {
        return "用户访问次数统计";
    }

    @Override
    public String getDescription() {
        return "网站访问用户";
    }


}
