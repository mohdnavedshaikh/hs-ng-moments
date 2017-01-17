package in.hopscotch.moments.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HSMomentsController {

    @RequestMapping(value = "/testSpringMicroService", method = RequestMethod.GET)
    public String testSpringMicroService() {
        return "Success";
    }

}
