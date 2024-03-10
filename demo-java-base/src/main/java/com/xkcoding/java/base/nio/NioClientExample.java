package com.xkcoding.java.base.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClientExample {

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8080));

            while (!socketChannel.finishConnect()) {
                // 等待连接建立
            }

            // 连接建立后，发送消息给服务器
            String message = "Hello, Server!";
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            socketChannel.write(buffer);

            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);

            while (true) {
                int readyChannels = selector.select();

                if (readyChannels == 0) {
                    continue;
                }

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    if (key.isReadable()) {
                        // 处理读事件
                        SocketChannel server = (SocketChannel) key.channel();
                        ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
                        int bytesRead = server.read(responseBuffer);

                        if (bytesRead > 0) {
                            responseBuffer.flip();
                            System.out.println("收到服务器的响应：" + new String(responseBuffer.array(), 0, bytesRead));
                            // 可以在这里添加客户端的关闭逻辑
                        }
                    }

                    keyIterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
