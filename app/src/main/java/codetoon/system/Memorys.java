package codetoon.system;

import java.util.ArrayList;
import codetoon.variable.*;
import java.io.Serializable;

public class Memorys implements Serializable{

    public ArrayList<Memory> memory;

    public void setInstance(int size, int x, int y, int w, int h){
        memory = new ArrayList<>();
        for(int i = 0; i < size; i ++){
          for(int c = 0; c < size; c ++){
            Memory t = new Memory(x + c * (w / size), y + i * (h / size), w / size, h / size, c, i);
            
            memory.add(t);
          }
        }
        System.out.println("null");
        Variables.createVariable("memory", () -> new MemoryVariable(memory));

    }
    public Memory get(int i){
        return memory.get(i);
    }
    
}
