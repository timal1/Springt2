package com.timal1.rabbitmq.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

public class SenderApp {
  //  private final static String QUEUE_NAME = "language_queue";
    private final static String EXCHANGER_NAME = "language_exchanger";


    public static void main(String[] args) throws Exception {
        String theme;
        String messageOut;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGER_NAME, BuiltinExchangeType.DIRECT);
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            channel.queueBind(QUEUE_NAME, EXCHANGER_NAME, theme);
            Scanner scanner = new Scanner(System.in);
            System.out.println("в начале сообщения укажите тему");
            while (true) {
                String messageIn = scanner.nextLine();
                String[] parts = messageIn.split("\\s+");
                theme = parts[0];
                messageOut = messageIn.replaceFirst(theme, "");
                channel.basicPublish(EXCHANGER_NAME, theme, null, messageOut.getBytes());
                System.out.println(" [x] Sent '" + messageOut + "'");
            }
        }
    }
}
