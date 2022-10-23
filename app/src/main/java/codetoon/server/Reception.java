package codetoon.server;
import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import codetoon.system.*;

public class Reception extends Thread {
    Socket sock;
    ObjectInputStream in;
    Memorys memorys;

    Reception(Socket _sock, Memorys _memorys) {
        sock = _sock;
        memorys = _memorys;
        try {
            in = new ObjectInputStream(sock.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {

            try {
                testClassWrapper _testWrapper = (testClassWrapper) in.readObject();
                if (_testWrapper.valid == true) {
                    memorys = _testWrapper.memorys;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("receivedData: ");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
