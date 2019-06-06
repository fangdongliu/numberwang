package cn.fdongl.numberwangentity.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableResult {
    String name;

    String path;

    String description;

    Integer count;

    String show;

    public String toJson(){
        return "{" +
                "\"name\":\"" + name +"\","+
                "\"path\":\"" + path + "\","+
                "\"description\":\"" + description+ "\","+
                "\"count\":\"" + count + "\","+
                "\"show\":\"" + show + "\""+
                '}';
    }

}
