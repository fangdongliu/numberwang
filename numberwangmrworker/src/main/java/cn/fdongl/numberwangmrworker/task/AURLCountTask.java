package cn.fdongl.numberwangmrworker.task;

import cn.fdongl.numberwangmrworker.lineformat.LineFormat;
import org.springframework.stereotype.Component;

@Component
public class AURLCountTask implements HiveTask{

    @Override
    public boolean accept(LineFormat lineFormat) {
        return lineFormat.getTokenFlag().get("URL")!=null;
    }

    @Override
    public boolean isSummary() {
        return false;
    }

    @Override
    public String getSQL(String tableName) {
        return "select URL as `URL`,count(*) as `count` from " + tableName + " group by URL";
    }

    @Override
    public String getName() {
        return "URL";
    }

    @Override
    public String getDescription() {
        return "访问路径分布";
    }

    @Override
    public String getSchema() {
        return "URL,count";
    }

    @Override
    public String getShow() {
        return "pie";
    }


}
