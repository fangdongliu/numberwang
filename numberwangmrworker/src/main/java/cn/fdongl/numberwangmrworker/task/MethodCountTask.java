package cn.fdongl.numberwangmrworker.task;

import cn.fdongl.numberwangmrworker.lineformat.LineFormat;
import org.springframework.stereotype.Component;

@Component
public class MethodCountTask implements HiveTask{

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
        return "select method as `method`,count(*) as `count` from " + tableName + " group by method";
    }

    @Override
    public String getName() {
        return "method";
    }

    @Override
    public String getDescription() {
        return "请求方法统计";
    }

    @Override
    public String getSchema() {
        return "method,count";
    }

    @Override
    public String getShow() {
        return "pie";
    }


}
