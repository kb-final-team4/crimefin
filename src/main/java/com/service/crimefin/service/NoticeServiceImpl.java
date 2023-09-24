package com.service.crimefin.service;

import com.service.crimefin.domain.NoticeVO;
import com.service.crimefin.model.NoticeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    private NoticeDAO noticeDAO;
    @Override
    public int insertNotice(NoticeVO noticeVO) {
        return noticeDAO.insertNotice(noticeVO);
    }
}