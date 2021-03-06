package site.jaking.socket.samples1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Create by chenjiacheng on 2021/12/26
 */
public class EchoPlayer {

    public String echo(String msg) {
        return "echo: " + msg;
    }

    public void talk() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg;
        while ((msg = br.readLine()) != null) {
            System.out.println(echo(msg));
            if ("bye".equalsIgnoreCase(msg)) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoPlayer().talk();
    }
}
