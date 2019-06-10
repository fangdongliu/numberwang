package cn.fdongl.numberwangmrworker.task;

import cn.fdongl.numberwangmrworker.lineformat.LineFormat;
import org.springframework.stereotype.Component;

@Component
public class ProtocolCountTask implements HiveTask{

    @Override
    public boolean accept(LineFormat lineFormat) {
        return lineFormat.getTokenFlag().get("PRT")!=null;
    }

    @Override
    public boolean isSummary() {
        return false;
    }

    @Override
    public String getSQL(String tableName) {
        return "select protocol as `protocol`,count(*) as `count` from " + tableName + " group by protocol";
    }

    @Override
    public String getName() {
        return "protocol";
    }

    @Override
    public String getDescription() {
        return "请求协议统计";
    }

    @Override
    public String getSchema() {
        return "protocol,count";
    }


}
