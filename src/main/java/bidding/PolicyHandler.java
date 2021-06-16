package bidding;

import bidding.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired BiddingExaminationRepository biddingExaminationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBiddingParticipated_RegistBiddingParticipation(@Payload BiddingParticipated biddingParticipated){

        if(!biddingParticipated.validate()) return;

        System.out.println("\n\n##### listener RegistBiddingParticipation : " + biddingParticipated.toJson() + "\n\n");

        // Sample Logic //
        BiddingExamination biddingExamination = new BiddingExamination();
        biddingExaminationRepository.save(biddingExamination);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBiddingParticipationCanceled_CancelExaminationResults(@Payload BiddingParticipationCanceled biddingParticipationCanceled){

        if(!biddingParticipationCanceled.validate()) return;

        System.out.println("\n\n##### listener CancelExaminationResults : " + biddingParticipationCanceled.toJson() + "\n\n");

        // Sample Logic //
        BiddingExamination biddingExamination = new BiddingExamination();
        biddingExaminationRepository.save(biddingExamination);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
