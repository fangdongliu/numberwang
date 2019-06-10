package cn.fdongl.numberwangmrworker.lineformat;

import lombok.Data;

@Data
public class LineSource {

    public LineSource(){
        this.current = 0;
        this.len = 0;
        this.valid = true;
    }

    public LineSource(String source){
        this.source = source;
        this.valid = true;
        this.len = source.length();
        this.current = 0;
    }

    public String readWords(int n){
        boolean isBlank = true;

        while(current<len&&Character.isSpaceChar(source.charAt(current)))current++;
        int lastIndex = current;
        while(n>0){
            if(current==len){
                return source.substring(lastIndex,len);
            }
            char c = source.charAt(current);
            if(isBlank){
                if(!Character.isSpaceChar(c)){
                    isBlank = false;
                }
            }else{
                if(Character.isSpaceChar(c)||c=='"'){
                    isBlank = true;
                    n--;
                }
            }
            current++;
        }
        current--;
        return source.substring(lastIndex,current);
    }

    public String readUntil(char c){
        int lastIndex = this.current;
        for(;current<len;current++){
            if(source.charAt(current)==c){
                current++;
                return source.substring(lastIndex,current-1);
            }
        }
        return source.substring(lastIndex,len);
    }

    boolean valid;

    int len;

    String source;

    String buildResult;

    int current;

}
