package cn.fdongl.numberwangmrworker.lineformat;

import java.text.ParseException;
import java.util.Date;

public class TimeReader implements TokenReader{

    public TimeReader(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    String timeFormat;

    @Override
    public String read(LineSource input) throws Exception {
        Date date = new Date(0);
        int len = timeFormat.length();
        int nWords = timeFormat.charAt(0)-'0';
        String time = input.readWords(nWords);

        int cur = 0;
        for(int i=1;i<len;i++){
            char c = timeFormat.charAt(i);
            if(Character.isDigit(c)){
                i++;
                char next = timeFormat.charAt(i);
                int num = Integer.parseInt(time.substring(cur,cur+(c-'0')));
                cur+=(c-'0');
                switch (next){
                    case 'y':
                        date.setYear(num-1900);
                        break;
                    case 'm':
                        date.setMonth(num-1);
                        break;
                    case 'd':
                        date.setDate(num);
                        break;
                    case 'h':
                        date.setHours(num);
                        break;
                    default:
                        throw new ParseException("日期解析格式定义出错",1);
                }
            }else if(c == 'M'){
                String num = time.substring(cur,cur+3);
                cur+=3;
                switch (num){
                    case "Jan":
                        date.setMonth(0);
                        break;
                    case "Feb":
                        date.setMonth(1);
                        break;
                    case "Mar":
                        date.setMonth(2);
                        break;
                    case "Apr":
                        date.setMonth(3);
                        break;
                    case "May":
                        date.setMonth(4);
                        break;
                    case "Jun":
                        date.setMonth(5);
                        break;
                    case "Jul":
                        date.setMonth(6);
                        break;
                    case "Aug":
                        date.setMonth(7);
                        break;
                    case "Sep":
                        if(time.charAt(cur)=='t'){
                            cur++;
                        }
                        date.setMonth(8);
                        break;
                    case "Oct":
                        date.setMonth(9);
                        break;
                    case "Nov":
                        date.setMonth(10);
                        break;
                    case "Dec":
                        date.setMonth(11);
                        break;
                }
            }else{
                cur++;
            }
        }
        return String.valueOf(date.getTime());
    }
}
