package cn.fdongl.numberwangentity.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result{

    Integer code;
    String msg;
    Object data;

    public static Result success(){
        return success(null);
    }

    public static Result success(Object data){
        return new Result(0,"errMsg:OK",data);
    }

    public static Result fail(String msg){
        return new Result(1,msg,null);
    }

    public static Result fail(FailMsg msg){
        return fail(msg.msg);
    }

}
