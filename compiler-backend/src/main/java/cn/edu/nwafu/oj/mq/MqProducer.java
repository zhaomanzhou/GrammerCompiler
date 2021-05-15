package cn.edu.nwafu.oj.mq;


import com.alibaba.fastjson.JSON;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Component
public class MqProducer {

    private DefaultMQProducer producer;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${mq.nameserver.addr}")
    private String nameAddr;

    @Value("${mq.topicname}")
    private String topicName;

    @PostConstruct
    public void init() throws MQClientException {
        //做mq producer的初始化
        producer = new DefaultMQProducer("producer_group");
        producer.setNamesrvAddr(nameAddr);
        producer.setSendMsgTimeout(6000);
        producer.start();

    }


    public boolean judgeCode(Long userId,Long problemId, String uuid)  {

        redisTemplate.opsForHash().put(uuid, "status", "pending");
        redisTemplate.expire(uuid, 5, TimeUnit.MINUTES);

        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("userId",userId);
        bodyMap.put("problemId",problemId);
        bodyMap.put("uuid",uuid);


        Message message = new Message(topicName,"judge",
                JSON.toJSON(bodyMap).toString().getBytes(Charset.forName("UTF-8")));
        try {
            producer.send(message);
        } catch (MQClientException e) {
            e.printStackTrace();
            return false;
        } catch (RemotingException e) {
            e.printStackTrace();
            return false;
        } catch (MQBrokerException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

