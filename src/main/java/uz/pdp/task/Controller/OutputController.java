package uz.pdp.task.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.task.DTO.OutputDTO;
import uz.pdp.task.Service.OutputService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping
    public HttpEntity<?> transferMoney(@RequestBody OutputDTO outputDTO, HttpServletRequest request) {
        String moneyToCard = outputService.tranferMoneyToCard(outputDTO,request);
        return ResponseEntity.status(202).body(moneyToCard);
    }

}
