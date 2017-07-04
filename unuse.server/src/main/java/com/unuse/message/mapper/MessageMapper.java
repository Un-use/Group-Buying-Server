package com.unuse.message.mapper;

import com.unuse.message.api.Message;
import org.springframework.stereotype.Repository;

/**
 * Created by Unuse on 2017/7/4.
 */

@Repository
public interface MessageMapper {

    void addMessage(Message message);

}
