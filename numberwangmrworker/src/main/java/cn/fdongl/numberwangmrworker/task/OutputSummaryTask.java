package cn.fdongl.numberwangmrworker.task;

import cn.fdongl.numberwangmrworker.lineformat.LineFormat;
import org.springframework.stereotype.Component;

@Component
public class OutputSummaryTask implements HiveTask{

    @Override
    public boolean accept(LineFormat lineFormat) {
        return lineFormat.getTokenFlag().get("SB")!=null;
    }

    @Override
    public boolean isSummary() {
        return true;
    }

    @Override
    public String getSQL(String tableName) {
        return "select sum(sendbytes) as value from " + tableName;
    }

    @Override
    public String getName() {
        return "发送字节数";
    }

    @Override
    public String getDescription() {
        return "服务器发送的字节数统计";
    }
    

}
