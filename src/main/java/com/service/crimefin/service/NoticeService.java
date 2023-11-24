package com.service.crimefin.service;

import com.service.crimefin.domain.NoticeVO;

import java.util.List;

public interface NoticeService {
    int insertNotice(NoticeVO noticeVO);
    List<NoticeVO> getNotice(String memberId);
}