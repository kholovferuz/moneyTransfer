package uz.pdp.task.service;

import org.springframework.stereotype.Service;
import uz.pdp.task.dto.OutputDTO;

import javax.servlet.http.HttpServletRequest;

@Service
public interface OutputService {
    String tranferMoneyToCard(OutputDTO outputDTO, HttpServletRequest request);
}
