package cn.edu.nwafu.oj.mq;


import cn.edu.nwafu.admin.controller.vo.response.JudgeResult;
import cn.edu.nwafu.oj.Flow;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class MqConsumer
{

    private DefaultMQPushConsumer consumer;
    @Value("${mq.nameserver.addr}")
    private String nameAddr;

    @Value("${mq.topicname}")
    private String topicName;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private Flow flow;


    @PostConstruct
    public void init() throws MQClientException {
        consumer = new DefaultMQPushConsumer("consumer_group");
        consumer.setNamesrvAddr(nameAddr);
        consumer.subscribe(topicName,"*");

        log.info("mq消费者初始化成功");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                try
                {
                    Message msg = msgs.get(0);
                    log.info("收到MQ消息：" + msg);
                    String jsonString  = new String(msg.getBody());
                    Map<String,Object>map = JSON.parseObject(jsonString, Map.class);
                    Integer userId = (Integer) map.get("userId");
                    Integer problemId = (Integer) map.get("problemId");
                    String uuid = (String) map.get("uuid");
                    final JudgeResult judge = flow.judge(Long.valueOf(userId), Long.valueOf(problemId));

                    redisTemplate.opsForHash().put(uuid, "status", "ok");
                    redisTemplate.opsForHash().put(uuid, "result", judge);
                    redisTemplate.expire(uuid, 5, TimeUnit.MINUTES);
                    log.info("用户{}, 题目{}判断处理完毕", userId, problemId);

                } catch (Exception e)
                {
                    e.printStackTrace();
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

    }
}

