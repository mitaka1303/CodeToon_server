package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.system.CodeToon;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Lock extends MyMethod{
    private Player parcent = null;
    private int pass = 0;
    private int parcent_pass = 0;
    @Override
    public Object newInstance() {
        return new Lock();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        Object t = ObjectArgument.getInstance().indentification(map.get(CodeToon.PARCENT_ARGUMENT));
        if(t instanceof Player){
            parcent = (Player) t;
            if(map.get(1) != null){
                parcent_pass = IntegerArgument.getInstance().indentification(map.get(0));
                pass = IntegerArgument.getInstance().indentification(map.get(1));
            }else{
                pass = IntegerArgument.getInstance().indentification(map.get(0));
            }
        }
        return null;
    }
    @Override
    public void action(int i) {
        if(parcent != null){
            parcent.setPassWord(pass, parcent_pass);
            System.out.println(parcent.getName() + "に" + pass + "のパスワードを設定しました。");

        }
    }
}
