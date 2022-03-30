package consumer.consuming;
import consts.RabbitMQConsts;


import dto.InventoryDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class InventoryConsumer {
    @RabbitListener(queues = RabbitMQConsts.ROW_INVENTORY)
    private  void consumer(InventoryDto inventoryDto){

    }
}
