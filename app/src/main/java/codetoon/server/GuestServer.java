package codetoon.server;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import codetoon.system.*;

public class GuestServer extends Server {

    public GuestServer(Memorys _myMemorys, Memorys _opponentMemorys) {
        super(_myMemorys, _opponentMemorys);
    }

    public void connect(String ipAdress) {
        boolean connect = false;
        while (connect == false) {
            try {
                sock = new Socket(ipAdress, myPORT);
                returnSock = new Socket(ipAdress, opponentPORT);

                myOutStream = new ObjectOutputStream(sock.getOutputStream());
                opponentOutStream = new ObjectOutputStream(returnSock.getOutputStream());

                opponentReception = new Reception(sock, opponentMemorys);
                returnReception = new Reception(returnSock, myMemorys);
                System.out.println("connected");
                connect = true;
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e2) {
                    e.printStackTrace();
                }
            }
        }
        Thread thread = new Thread(this);
        thread.start();

    }

    public void end() {
        runServer = false;
        try {
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}