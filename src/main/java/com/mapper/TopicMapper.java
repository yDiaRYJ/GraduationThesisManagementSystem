package com.mapper;

import com.entity.Topic;
import com.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TopicMapper {
    @Select("select * from t_topic where student_id = #{studentId}")
    Topic getTopicByStudentId(String studentId);

    @Insert("INSERT INTO t_topic (topic_id, topic_name, topic_description, student_id, teacher_id, topic_status) " +
            "VALUES (#{userId}, #{userName}, #{userPassword}, #{userType})")
    void insertTopic(Topic topic);
}
