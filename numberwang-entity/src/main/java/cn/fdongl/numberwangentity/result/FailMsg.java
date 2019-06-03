package cn.fdongl.numberwangentity.result;

public enum FailMsg {
    /**
     * 参数过长
     */
    PARAM_TOO_LONG("参数过长");


    /**
     * 失败信息
     */
    String msg;

    FailMsg(String msg){
        this.msg = msg;
    }
}
