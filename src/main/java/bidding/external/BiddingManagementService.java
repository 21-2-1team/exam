
package bidding.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="BiddingManagement", url="http://localhost:8081")
public interface BiddingManagementService {

    @RequestMapping(method= RequestMethod.PATCH, path="/biddingManagements")
    public void registSucessBidder(@RequestBody BiddingManagement biddingManagement);

}