package com.service.crimefin.model;

import com.service.crimefin.domain.NoticeVO;

import java.util.List;

public interface NoticeDAO {
    int insertNotice(NoticeVO noticeVO);
    List<NoticeVO> getNotice(String memberId);
}
