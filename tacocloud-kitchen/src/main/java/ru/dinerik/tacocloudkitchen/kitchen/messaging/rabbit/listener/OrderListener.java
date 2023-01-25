package ru.dinerik.tacocloudkitchen.kitchen.messaging.rabbit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.dinerik.tacocloudkitchen.TacoOrder;
import ru.dinerik.tacocloudkitchen.kitchen.KitchenUI;

@Component
public class OrderListener {

    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @RabbitListener(queues = "tacocloud.order.queue") // Прослушивание места назначения
    public void receiveOrder(TacoOrder order) {
        ui.displayOrder(order);
    }
}
