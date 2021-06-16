package bidding;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="BiddingExamination_table")
public class BiddingExamination {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String noticeNo;
    private String participateNo;
    private Integer priceScore;
    private Integer skillScore;
    private Boolean successBidderFlag;

    @PostUpdate
    public void onPostUpdate(){
        ExaminationResultRegistered examinationResultRegistered = new ExaminationResultRegistered();
        BeanUtils.copyProperties(this, examinationResultRegistered);
        examinationResultRegistered.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        bidding.external.BiddingManagement biddingManagement = new bidding.external.BiddingManagement();
        // mappings goes here
        BiddingExaminationApplication.applicationContext.getBean(bidding.external.BiddingManagementService.class)
            .registSucessBidder(biddingManagement);


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }
    public String getParticipateNo() {
        return participateNo;
    }

    public void setParticipateNo(String participateNo) {
        this.participateNo = participateNo;
    }
    public Integer getPriceScore() {
        return priceScore;
    }

    public void setPriceScore(Integer priceScore) {
        this.priceScore = priceScore;
    }
    public Integer getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(Integer skillScore) {
        this.skillScore = skillScore;
    }
    public Boolean getSuccessBidderFlag() {
        return successBidderFlag;
    }

    public void setSuccessBidderFlag(Boolean successBidderFlag) {
        this.successBidderFlag = successBidderFlag;
    }




}
