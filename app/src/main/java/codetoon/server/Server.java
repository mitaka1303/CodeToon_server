package codetoon.server;
import java.io.IOException;
import java.net.Socket;
import java.io.ObjectOutputStream;
import codetoon.system.*;

abstract class Server implements Runnable {
    int myPORT = 50000;
    int opponentPORT = 60000;

    boolean runServer = true;
    boolean myCopy_nextSendValid = true;
    boolean opponent_nextSendValid = true;

    Memorys myMemorys;
    Memorys opponentMemorys;

    Socket sock;
    Socket returnSock;
    ObjectOutputStream myOutStream;
    ObjectOutputStream opponentOutStream;

    Reception opponentReception;
    Reception returnReception;

    public Server(Memorys _myMemorys, Memorys _opponentMemorys) {
        myMemorys = _myMemorys;
        opponentMemorys = _opponentMemorys;
    }

    public void run() {
        System.out.println("run server");
        sendMyCopy();
        receiveOpponent();
        while (runServer) {
            sendMyCopy();
            sendOpponentCopy();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            receiveOpponent();
            receiveReturn();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void sendMyCopy() {
        try {
            testClassWrapper testWrapper = new testClassWrapper(myCopy_nextSendValid, myMemorys);
            if(myCopy_nextSendValid == true){
                myCopy_nextSendValid = false;
            }
            myOutStream.reset();
            myOutStream.writeObject(testWrapper);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendOpponentCopy() {
        try {
            testClassWrapper testWrapper = new testClassWrapper(opponent_nextSendValid, opponentMemorys);
            if(opponent_nextSendValid == true){
                opponent_nextSendValid = false;
            }
            opponentOutStream.reset();

            opponentOutStream.writeObject(testWrapper);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMyTest(){
        myCopy_nextSendValid = true;
    }

    public void updateOpponentTest(){
        opponent_nextSendValid = true;
    }

    void receiveOpponent() {
        opponentReception.run();
    }

    void receiveReturn() {
        returnReception.run();
    }

    abstract public void end();

}