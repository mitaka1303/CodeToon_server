package codetoon.map;

import codetoon.main.*;
import codetoon.system.*;
import codetoon.util.*;
import java.awt.*;
import codetoon.server.*;

/**
 * 実際の先頭画面を描画する、描画スクリーンクラス。
 * このクラスには、小クラスとして、Fieldクラスを含んでいる。
 * FieldクラスはMemoryをContainerとして収容するものである。
 **/
public class PazzleStage extends Map {
  Memorys myMemorys;
  Memorys opponentMemorys;
  public final int MEMORY_SIZE;
  private final Field field;
  private final Console c;

  public PazzleStage(int size, boolean isHost) {
    myMemorys = new Memorys();
    opponentMemorys = new Memorys();
    MEMORY_SIZE = size;
    field = new Field(5, 20, 130, 83);
    field.setMemoryCapability(MEMORY_SIZE);
    c = new Console(140, 50, 120, 60);
    c.setHost(Admin.getInstance());
    c.setVisible(true);
    if (isHost) {
      HostServer host = new HostServer(myMemorys, opponentMemorys);
      host.setUpServer();
    } else {
      String ipAdress = "";
      GuestServer guest = new GuestServer(myMemorys, opponentMemorys);
      guest.connect(ipAdress);
    }
    CodeToon.isGameStart = true;
  }

  public Console getConsole() {
    return c;
  }

  @Override
  /** フィールドを描画 **/
  public void display(Graphics g) {
    // background(#505050);
    field.display(g);
    MyText.setText("My Memory List", 100, 120, Color.WHITE, 80);
  }

  private class Field {
    private int x, y, w, h;
    int size;

    public Field(int x, int y, int w, int h) {
      this.x = x * Main.DW;
      this.y = y * Main.DH;
      this.w = w * Main.DW;
      this.h = h * Main.DH;
    }

    public void setMemoryCapability(int size) {
      this.size = size;
      myMemorys.setInstance(size, x, y, w, h);
    }

    public void display(Graphics g) {

      for (int i = 0; i < size * size; i++) {
        myMemorys.get(i).display(g);
      }

    }
  }
}