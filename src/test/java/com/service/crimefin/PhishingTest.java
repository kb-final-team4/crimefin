package com.service.crimefin;

import com.service.crimefin.domain.PhishingInfoVO;
import com.service.crimefin.model.PhishingInfoDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Commit
public class PhishingTest {

    @Autowired
    private PhishingInfoDAO phishingInfoDAO;

    @Test
    public void unitTest() throws Exception{

        System.out.println("=========== isPhishingNumber ===========");
        PhishingInfoVO resultVO = phishingInfoDAO.isPhishingNumber("37777698");
        if(resultVO!=null) {
            System.out.println("피싱 의심되는 번호입니다.");
            //history에 phishing_id 저장하기
        } else {
            System.out.println("피싱 의심되지 않는 번호입니다.");
        }



    }

}
