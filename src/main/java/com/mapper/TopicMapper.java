package com.mapper;

import com.entity.Topic;
import com.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TopicMapper {
    @Select("select * from t_topic where student_id = #{studentId}")
    Topic getTopicByStudentId(String studentId);

    @Select("select * from t_topic where topic_id = #{topicId}")
    Topic getTopicByTopicId(String topicId);

    @Insert("INSERT INTO t_topic (topic_id, topic_name, topic_description, student_id, teacher_id, topic_status) " +
            "VALUES (#{topicId}, #{topicName}, #{topicDescription}, #{studentId}, #{teacherId}, #{topicStatus})")
    void insertTopic(Topic topic);

    @Update("UPDATE t_topic SET topic_name = #{topicName}, topic_description = #{topicDescription}, topic_status = #{topicStatus} WHERE topic_id = #{topicId}")
    void updateTopic(Topic topic);
}
