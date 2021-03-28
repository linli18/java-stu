import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

public class TestBlockingNIO {

    /**
     * 客户端
     */
    @Test
    public void client() {
        SocketChannel socketChannel = null;
        ByteBuffer bf = null;
        try {
            //1、打开一个SocketChannel
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));
            //2、把通道设置为非阻塞
            socketChannel.configureBlocking(false);
            bf = ByteBuffer.allocate(1024);
            bf.put(new Date().toString().getBytes());
            //3、往通道写数据
            bf.flip();
            socketChannel.write(bf);
            bf.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //4、关闭通道
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 服务端
     */
    @Test
    public void server() {
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        try {
            //打开一个ServerSocket
            serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            //绑定连接
            serverSocketChannel.bind(new InetSocketAddress(8899));
            //获取选择器
            selector = Selector.open();
            //将通道注册到选择器上,并且指定监听事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            //轮询监听器上准备就绪的事件
            while (selector.select() > 0) {
                //获取当前选择器中所有注册的“选择键（已就绪的监听状态）”
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel)key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int len = 0;
                        while ((len = socketChannel.read(byteBuffer)) > 0) {
                            byteBuffer.flip();
                            System.out.printf(new String(byteBuffer.array(), 0, len));
                            byteBuffer.clear();
                        }
                    }

                }
                iterator.remove();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
