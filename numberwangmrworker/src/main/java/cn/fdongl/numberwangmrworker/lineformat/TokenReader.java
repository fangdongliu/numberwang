package cn.fdongl.numberwangmrworker.lineformat;

@FunctionalInterface
public interface TokenReader {

    String read(LineSource input)throws Exception;

}
