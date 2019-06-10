package cn.fdongl.numberwangmrworker.lineformat;

public class TerminateTokenReader implements TokenReader{

    char c;

    public TerminateTokenReader(char c){
        this.c = c;
    }

    @Override
    public String read(LineSource input) throws Exception {
        return input.readUntil(c);
    }
}
