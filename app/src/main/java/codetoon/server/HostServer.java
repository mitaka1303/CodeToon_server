package codetoon.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.ObjectOutputStream;
import codetoon.system.*;

public class HostServer extends Server{

    ServerSocket svSock;
    ServerSocket svReturnSock;

    public HostServer(Memorys _myMemorys, Memorys _opponentMemorys) {
        super(_myMemorys, _opponentMemorys);
    }

    public void setUpServer() {
        try {
            svSock = new ServerSocket(myPORT);
            svReturnSock = new ServerSocket(opponentPORT);
            
            sock = svSock.accept();
            returnSock = svReturnSock.accept();

            myOutStream = new ObjectOutputStream(sock.getOutputStream());
            opponentOutStream = new ObjectOutputStream(returnSock.getOutputStream());

            opponentReception = new Reception(sock, myMemorys);
            returnReception = new Reception(returnSock, opponentMemorys);
            
            System.out.println("connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(this);
        thread.start();
    }

    public void end() {
        runServer = false;
        try {
            sock.close();
            svSock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}