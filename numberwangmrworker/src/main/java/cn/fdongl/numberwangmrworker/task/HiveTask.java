package cn.fdongl.numberwangmrworker.task;


import cn.fdongl.numberwangmrworker.lineformat.LineFormat;

/**
 * 定义一个分析任务
 */
public interface HiveTask{

    /**
     * 指定该task要求的LineFormat
     * @param lineFormat
     * @return
     */
    boolean accept(LineFormat lineFormat);

    boolean isSummary();

    String getSQL(String tableName);

    String getName();

    String getDescription();

    default String getSchema(){
        return "";
    }

    default String getShow(){
        return "";
    }

}
