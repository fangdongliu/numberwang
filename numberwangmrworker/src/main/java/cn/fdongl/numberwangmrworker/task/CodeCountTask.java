package cn.fdongl.numberwangmrworker.task;

import cn.fdongl.numberwangmrworker.lineformat.LineFormat;
import org.springframework.stereotype.Component;

@Component
public class CodeCountTask implements HiveTask{

    @Override
    public boolean accept(LineFormat lineFormat) {
        return lineFormat.getTokenFlag().get("CD")!=null;
    }

    @Override
    public boolean isSummary() {
        return false;
    }

    @Override
    public String getSQL(String tableName) {
        return "select code as `code`,count(*) as `count` from " + tableName + " group by code";
    }

    @Override
    public String getName() {
        return "code";
    }

    @Override
    public String getDescription() {
        return "返回状态码分布";
    }

    @Override
    public String getSchema() {
        return "code,count";
    }

    @Override
    public String getShow() {
        return "pie";
    }


}
