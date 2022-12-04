package codetoon.map;

import codetoon.server.Server;
import codetoon.system.CodeToon;
import codetoon.util.*;
import codetoon.main.*;
import codetoon.util.animation.Animation;
import codetoon.util.box.Box;
import codetoon.util.box.DrawingTextBox;
import codetoon.util.box.ContainerBox;

import java.awt.*;
public class Title extends Map{
    private ContainerBox box;
    public Title(){
    box = new ContainerBox(60, 40, 70, 40, new ContainerData<Box, Integer>(){
      @Override
      public void action(int i){
        switch(i){
          case 0:
             Server.isHost = true;
             Main.getInstance().run(new CreateSection());
             Server.server.startServer(null);
              break;
          case 1:
              System.out.println("途中からゲームを始める。");
              Server.isHost = false;
              //Main.getInstance().run(new PazzleStage(CodeToon.MEMORY_SIZE));
              Main.getInstance().run(new JoinServer());

              break;
          case 2: System.exit(0); break;
            case 3:CodeToon.DEBUG = true;Main.getInstance().run(new PazzleStage(5));
        }
      }
      @Override
      public int getCount(){
        return 4;
      }
      @Override
      public Box set(Integer i){
        return switch (i.intValue()) {
            case 0-> new DrawingTextBox("新規でセッションを作成する");
            case 1-> new DrawingTextBox("既存セッションに参加する");
            case 2-> new DrawingTextBox("終わる");
            case 3-> new DrawingTextBox("デバッグする");
            default-> null;
        };
      }
    });
  }

    @Override
    public void setup(Graphics h) {
        box.draw();
        Animation.create(h).draw("CODETOON Ver 1.0", 10,   10,
                new Animation.Properties()
                        .font("", Font.ITALIC, 60)
                        .center()
        );
         Animation.create(h).draw("Test Animation ", 30, 30,
                 new Animation.Properties()
                         .size(60)
                         .center()
                 );
    }
    public void display(Graphics g){
        box.draw();
  }

}
