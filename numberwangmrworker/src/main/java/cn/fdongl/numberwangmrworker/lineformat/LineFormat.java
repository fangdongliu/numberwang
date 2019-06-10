package cn.fdongl.numberwangmrworker.lineformat;

import cn.fdongl.numberwangmrworker.lineformat.LineSource;
import cn.fdongl.numberwangmrworker.lineformat.NullTokenReader;
import cn.fdongl.numberwangmrworker.lineformat.TerminateTokenReader;
import cn.fdongl.numberwangmrworker.lineformat.TokenReader;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class LineFormat {

    public LineFormat(String tableDefine, Map<String, Boolean> tokenFlag, List<TokenReader> tokenReaders) {
        this.tableDefine = tableDefine;
        this.tokenFlag = tokenFlag;
        this.tokenReaders = tokenReaders;
    }

    String tableDefine;

    Map<String,Boolean> tokenFlag;

    List<TokenReader>tokenReaders = new ArrayList<>();

    public static void main(String[] args) {
        String input = "112.228.63.95 - - [31/May/2013:17:58:37 +0800] \"F_2132_lastact=1369994313%09home.php%09space; 800068868slid=slid_348_66%7C; pgv_si=s5073725440; 800068868mid=357_39; 800068868mh=1369994174677; 800068868slid_348_66=1369994174679; rTJF_2132_sid=t5r78z; rTJF_2132_visitedfid=47; rTJF_2132_viewid=tid_11528; pgv_info=ssi=s128437705; CNZZDATA4617788=cnzz_eid%3D1789936893-1369994169-http%253A%252F%252Fbbs.itcast.cn%26ntime%3D1369994169%26cnzz_a%3D2%26retime%3D1369994145512%26sin%3Dhttp%253A%252F%252Fwww.itcast.cn%252F%26ltime%3D1369994145512%26rtime%3D0; bdshare_firstime=1369994019057; rTJF_2132_sendmail=1; rTJF_2132_ulastactivity=f758teGVoXwkupTUD863%2BIa3x7gWKxFVCul36Liuw2X8DNqbsHQr; rTJF_2132_auth=a4a3Jd54aStyM2eEWIj98SAT%2B4NgBdENbVBldtqPb9831Wk%2FEy6Puad%2B7ysKOzI0B%2FqIjBufyq1kgV7pwXTOCNDLoQ; rTJF_2132_lastcheckfeed=44556%7C1369994235; rTJF_2132_lip=112.251.244.141%2C1365334398; rTJF_2132_security_cookiereport=ab78Mp7lP7YUATGXGItiaa%2Fw4AEuqDfq%2Bs5UTS0gVbIQ%2FiLkgJe1; rTJF_2132_connect_is_bind=0; tjpctrl=1369995915817; rTJF_2132_nofocus_forum=1; rTJF_2132_checkpm=1; rTJF_2132_home_diymode=1\" 400 226";
        LineFormatSource lineFormatSource = new LineFormatSource("%IP%W-%TM\"%MD%URL%PRT\"%CD%SB","2[2d/M/4y:2h");
        try {
            LineFormat format = lineFormatSource.buildLineFormat();
            System.out.println(format.read(input));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String read(String input) throws Exception {
        int len = tokenReaders.size();
        int currentToken = 0;
        StringBuilder result = new StringBuilder();
        LineSource source = new LineSource(input);
        while(currentToken < len){

            TokenReader reader = tokenReaders.get(currentToken++);
            String s = reader.read(source);
            if(reader instanceof NullTokenReader || reader instanceof TerminateTokenReader){
                continue;
            }
            if(currentToken!=1){
                result.append('\1');
            }
//            if(currentToken<len&&tokenReaders.get(currentToken) instanceof TerminateTokenReader){
//                reader = tokenReaders.get(currentToken++);
//                s = s + rtrim(reader.read(source));
//            }
            result.append(s);
        }
        return result.toString();
    }

    String rtrim(String s){
        return StringUtils.trimTrailingWhitespace(s);
    }

}
