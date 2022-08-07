package com.timal1.rabbitmq.consumer;

import com.rabbitmq.client.*;

import java.util.Scanner;

public class ReceiverApp {
    private static final String EXCHANGE_NAME = "language_exchanger";

    public static void main(String[] argv) throws Exception {
        String theme = "";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        String queueName = channel.queueDeclare().getQueue();
        System.out.println("My queue name: " + queueName);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Для выбора темы подписки введите: 'set_topic (название темы)'");

        while (true) {
            String messageIn = scanner.nextLine();
            String[] parts = messageIn.split("\\s+");
            String key = parts[0];
            if (key.equals("set_topic")){
                theme = parts[1];
                System.out.println("Вы подписались на тему: " + theme);
            }
            channel.queueBind(queueName, EXCHANGE_NAME, theme);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        }
    }
}
