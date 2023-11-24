package com.service.crimefin;

import com.service.crimefin.domain.AccountVO;
import com.service.crimefin.domain.BankingVO;
import com.service.crimefin.domain.MemberVO;
import com.service.crimefin.domain.NoticeVO;
import com.service.crimefin.dto.MessageDTO;
import com.service.crimefin.dto.SmsResponseDTO;
import com.service.crimefin.service.AssetService;
import com.service.crimefin.service.NoticeService;
import com.service.crimefin.service.SmsService;
import com.service.crimefin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.util.List;

@Service
public class IntervalDB {
    @Autowired
    private UserService userService;
    @Autowired
    private AssetService assetService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private NoticeService noticeService;

    private boolean flag = false;

    private ServletRequestAttributes sra;


    @Scheduled(fixedDelay = 3000) //3초 마다 실행
    public void temp() throws Exception{
        List<BankingVO>list = assetService.getLimitBanking(); //limit 넘는 거래내역 조회
        if(list == null || list.isEmpty()) { //아직 limit 넘는 거래내역이 없을 경우
            System.out.println("limit 안넘음");
        }
        else { //limit 넘는 거래내역 있을 경우
            System.out.println("limit 보만 많은 거래내역 발생!!" + list);
            for(BankingVO bankingVO : list){
                String memberId = bankingVO.getMemberId();
                String accountNum = bankingVO.getAccountNum();
                AccountVO accountVO = assetService.findByAccountNum(accountNum); //계좌 번호로 해댱 계좌의 정보 찾음
                System.out.println("memberId ="+memberId+" accountNum ="+accountNum);//출력문
                MemberVO memberVO = userService.findMemberById(memberId); // memberId로 사용자 정보 찾음
                System.out.println(memberVO);//출력문

                String phone = memberVO.getPhone(); //사용자의 전화번호 받기
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setTo(phone);
                String prefixMessage = "Crime-Fin 위험알림 서비스\n"+
                        accountVO.getBank()+" "+accountNum+"에서\n"+
                        bankingVO.getWithdrawal()+"원이 인출되었습니다.";

                messageDTO.setContent(prefixMessage);
                SmsResponseDTO smsResponseDTO = smsService.sendSms(messageDTO);
                assetService.deleteLimitBanking(bankingVO.getBankingId()); //limit 넘는 거래 내역 삭제

                NoticeVO noticeVO = new NoticeVO();
                noticeVO.setMemberId(memberId);
                noticeVO.setAccountNum(accountNum);
                noticeVO.setTime(bankingVO.getBankingDate()); //거래내역 생성할때 날짜도 넣어줘야 그걸 이용함
                noticeVO.setBankName(accountVO.getBank());
                noticeVO.setDeposit(bankingVO.getDeposit());
                noticeVO.setWithdrawal(bankingVO.getWithdrawal());
                System.out.println(noticeVO);//출력문
                noticeService.insertNotice(noticeVO); //알림 리스트 등록
            }
        }
    }
}

