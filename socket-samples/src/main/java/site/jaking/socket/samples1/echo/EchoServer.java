package site.jaking.socket.samples1.echo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create by chenjiacheng on 2021/12/26
 */
public class EchoServer {
    private int port = 81;
    private final ServerSocket serverSocket;

    public EchoServer() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("服务器启动");
    }

    public String echo(String msg) {
        return "echo: " + msg;
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("新连接: "+socket.getInetAddress()+":"+socket.getPort());
                BufferedReader br = getReader(socket);
                PrintWriter pw = getWriter(socket);

                String msg = null;
                while ((msg=br.readLine())!=null){
                    System.out.println(msg);
                    pw.println(echo(msg));
                    if(msg.equals("bye")){
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                        System.out.println("连接已关闭");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoServer().service();
    }
}
