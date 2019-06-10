package cn.fdongl.numberwangmrworker.lineformat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineFormatSource {

    public LineFormatSource(String source,String timeFormat){
        this.source = source;
        this.timeFormat = timeFormat;
    }

    String timeFormat;
    String source;

    public LineFormat buildLineFormat() throws ParseException {
        StringBuilder tableDefine = new StringBuilder();
        List<TokenReader> tokenReaders = new ArrayList<>();
        Map<String,Boolean>tokenFlag = new HashMap<>();
        int len = source.length();
        for(int n = 0;n<len;n++){
            char c = source.charAt(n);
            if(c=='%'){
                int next = n+1;
                while(next<len&&Character.isLetterOrDigit(source.charAt(next))){
                    next++;
                }
                String code = source.substring(n+1,next);
                tokenFlag.put(code,true);
                switch (code){
                    case "IP":
                        tableDefine.append("`ip` BIGINT,");
                        tokenReaders.add(source->{
                            String ip = source.readWords(1);
                            String[]ips = ip.split("\\.");
                            return String.valueOf((Long.parseLong(ips[0]) << 24) +
                                    (Long.parseLong(ips[1]) << 16) +
                                    (Long.parseLong(ips[2]) << 8) +
                                    Long.parseLong(ips[3]));
                        });
                        break;
                    case "W":
                        tokenReaders.add(new NullTokenReader());
                        break;
                    case "RB":
                        tableDefine.append("`readbytes` BIGINT,");
                        tokenReaders.add(source->{
                            String b = source.readWords(1);
                            try {
                                return String.valueOf(Long.parseLong(b));
                            }catch (Exception e){
                                return "0";
                            }
                        });
                        break;
                    case "SB":
                        tableDefine.append("`sendbytes` BIGINT,");
                        tokenReaders.add(source->{
                            String b = source.readWords(1);
                            try {
                                return String.valueOf(Long.parseLong(b));
                            }catch (Exception e){
                                return "0";
                            }
                        });
                        break;
                    case "TT":
                        tableDefine.append("`time` BIGINT,");
                        tokenReaders.add(source->{
                            String b = source.readWords(1);
                            try {
                                return String.valueOf(Long.parseLong(b));
                            }catch (Exception e){
                                return "0";
                            }
                        });
                        break;
                    case "TM":
                        tableDefine.append("`start` BIGINT,");
                        tokenReaders.add(new TimeReader(this.timeFormat));
                        break;
                    case "ED":
                        tableDefine.append("`end` BIGINT,");
                        tokenReaders.add(new TimeReader(this.timeFormat));
                        break;
                    case "MD":
                        tableDefine.append("`method` STRING,");
                        tokenReaders.add(source->{
                            String s = source.readWords(1);
                            switch(s){
                                case "POST":
                                case "GET":
                                case "PUT":
                                case "PATCH":
                                case "DELETE":
                                case "HEAD":
                                case "OPTIONS":
                                    return s;
                                default:
                                    throw new Exception("");
                            }
                        });
                        break;
                    case "PRT":
                        tableDefine.append("`protocol` STRING,");
                        tokenReaders.add(source->{
                            String s = source.readWords(1);
                            if(!s.startsWith("HTTP")){
                                throw new Exception("");
                            }
                            return s;
                        });
                        break;
                    case "URL":
                        tableDefine.append("`url` STRING,");
                        tokenReaders.add(source->{
                            String url = source.readWords(1);
                            int l = url.lastIndexOf('?');
                            if(l>=0){
                                url = url.substring(0,l);
                            }
                            return url;
                        });
                        break;
                    case "Q":
                        tableDefine.append("`query` STRING,");
                        tokenReaders.add(source->source.readWords(1));
                        break;
                    case "SRC":
                        tableDefine.append("`source` STRING,");
                        tokenReaders.add(source->source.readWords(1));
                        break;
                    case "CD":
                        tableDefine.append("`code` INT,");
                        tokenReaders.add(source->String.valueOf(Long.parseLong(source.readWords(1))));
                        break;
                    case "TH":
                        tableDefine.append("`thread` STRING,");
                        tokenReaders.add(source->source.readWords(1));
                        break;
                    case "PC":
                        tableDefine.append("`computer` STRING,");
                        tokenReaders.add(source->source.readWords(1));
                        break;
                    case "SD":
                        tableDefine.append("`session` STRING,");
                        tokenReaders.add(source->source.readWords(1));
                        break;
                    default:
                        throw new ParseException(source,n);
                }
                n = next-1;
            }else if(!Character.isLetterOrDigit(c)){
                tokenReaders.add(new TerminateTokenReader(c));
            }else{
                throw new ParseException(source,n);
            }
        }
        if(tableDefine.length()>0){
            tableDefine.deleteCharAt(tableDefine.length()-1);
        }
        return new LineFormat(tableDefine.toString(),tokenFlag,tokenReaders);
    }



}
