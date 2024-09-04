package com.service;

import com.entity.Topic;
import com.mapper.TopicMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicService {
    private final TopicMapper topicMapper;

    public TopicService(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    public Topic getTopicByStudentId(String studentId) {
        return topicMapper.getTopicByStudentId(studentId);
    }
}
