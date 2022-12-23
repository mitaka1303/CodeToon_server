package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.system.CodeToon;
import codetoon.system.Memory;
import codetoon.system.Player;
import codetoon.util.Indentification;
import codetoon.util.converter.ConvertSource;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Loop extends MyMethod{
    String host;
    String methods;
    StringBuilder program = new StringBuilder();
    String count;
    @Override
    public Object newInstance() {
        return new Loop();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        host = map.get(CodeToon.HOST_MAP);
        count = map.get(0);
        methods = map.get(CodeToon.INSIDE_METHODS);
        program.append(map.get(CodeToon.INSIDE_METHODS));


        return "for";
    }

    @Override
    public void action(int i) {
        int count = IntegerArgument.getInstance().indentification(this.count);
        Player host = (Player) ObjectArgument.getInstance().indentification(this.host);
        ArrayList<MyMethod> methods = ConvertSource.convert(this.methods, host);

        methods = host.removeBlackList(methods);
        for(int l = 0; l < count ; l ++) {
            methods = ConvertSource.convert(this.methods, host);
            methods = host.removeBlackList(methods);
            if(!methods.isEmpty()) {
                for (int c = 0; c < methods.size(); c++) {
                    methods.get(c).action(c);

                }
            }
        }
    }
}
