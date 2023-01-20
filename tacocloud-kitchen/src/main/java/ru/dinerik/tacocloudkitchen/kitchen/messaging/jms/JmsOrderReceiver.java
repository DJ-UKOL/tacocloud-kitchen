package ru.dinerik.tacocloudkitchen.kitchen.messaging.jms;

import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.dinerik.tacocloudkitchen.TacoOrder;
import ru.dinerik.tacocloudkitchen.kitchen.OrderReceiver;

/* Активное извлечение заказов из очереди */
//@Profile("jms-template")
@Component
public class JmsOrderReceiver implements OrderReceiver {

    private JmsTemplate jms;
    //private MessageConverter converter;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms
                            //, MessageConverter converter
                            ) {
        this.jms = jms;
        //this.converter = converter;
    }

    // для получения сообщения, требуется явно вызвать receive() или receiveAndConvert()
    @Override
    public TacoOrder receiveOrder() {
        //Message message = jms.receive("tacocloud.order.queue"); // место назначения, откуда должен быть извлечен заказ
        // return (TacoOrder) converter.fromMessage(message);

        // Получение преобразованного объекта TacoOrder
        return (TacoOrder) jms.receiveAndConvert("tacocloud.order.queue");
    }
}
