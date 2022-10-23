package codetoon.server;
import java.io.Serializable;
import codetoon.system.*;
public class testClassWrapper implements Serializable{
    boolean valid = false;
    Memorys memorys;
    testClassWrapper(boolean _valid, Memorys _memorys){
        memorys = _memorys;
        valid = _valid;
    }
}
