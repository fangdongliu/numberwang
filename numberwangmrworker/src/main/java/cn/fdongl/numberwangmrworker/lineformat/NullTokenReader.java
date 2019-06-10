package cn.fdongl.numberwangmrworker.lineformat;

public class NullTokenReader implements TokenReader{
    @Override
    public String read(LineSource input) throws Exception {
        input.readWords(1);
        return null;
    }
}
