package uz.pdp.task.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.task.dto.OutputDTO;
import uz.pdp.task.service.OutputService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/output")
public class OutputController {

    final OutputService outputService;

    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    @PostMapping
    public HttpEntity<?> transferMoney(@Valid @RequestBody OutputDTO outputDTO, HttpServletRequest request) {
        String moneyToCard = outputService.tranferMoneyToCard(outputDTO, request);
        return ResponseEntity.status(202).body(moneyToCard);
    }

}
