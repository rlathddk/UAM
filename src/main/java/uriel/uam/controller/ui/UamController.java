package uriel.uam.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UamController {

    @GetMapping
    public String index(){
        return "html/index.html";
    }

    @GetMapping("/reservation")
    public String createReservation(){
        return "html/createReservation.html";
    }
}
