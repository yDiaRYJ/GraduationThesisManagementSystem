package com.mapper;

import com.entity.Doc;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DocMapper {
    @Select("select * from t_doc WHERE user_id = #{userId}")
    List<Doc> getDocListByUserId(@Param("userId") String userId);
    @Select("select * from t_doc WHERE user_id = #{userId} and doc_type = #{docType}")
    Doc getDocByUserIdAndDocType(@Param("userId") String userId, @Param("docType") int docType);
    @Insert("INSERT INTO t_doc (user_id, doc_path, doc_type, doc_status) " +
            "VALUES (#{userId}, #{docPath}, #{docType}, #{docStatus})")
    void insertDoc(Doc doc);
    @Update("UPDATE t_doc SET doc_status = #{docStatus} WHERE user_id = #{userId} and doc_type = #{docType}")
    void updateDoc(Doc doc);
}
