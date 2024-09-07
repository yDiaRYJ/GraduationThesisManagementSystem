package com.service;

import com.entity.Doc;
import com.mapper.DocMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocService {
    private final DocMapper docMapper;

    public DocService(DocMapper docMapper) {
        this.docMapper = docMapper;
    }

    public List<Doc> getDocListByUserId(@Param("userId") String userId) {
        return this.docMapper.getDocListByUserId(userId);
    }
    public Doc getDocByUserIdAndDocType(String userId, int docType) {
        return this.docMapper.getDocByUserIdAndDocType(userId, docType);
    }

    public void insertDoc(Doc doc) {
        this.docMapper.insertDoc(doc);
    }

    public void updateDoc(Doc doc) {
        this.docMapper.updateDoc(doc);
    }
}
