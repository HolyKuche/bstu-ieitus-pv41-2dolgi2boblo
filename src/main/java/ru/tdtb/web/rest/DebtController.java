package ru.tdtb.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tdtb.business.dto.DebtDto;
import ru.tdtb.business.service.DebtService;

import java.util.List;

@RestController
@RequestMapping("/api/debt")
public class DebtController {
    @Autowired
    private DebtService service;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DebtDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @RequestMapping(value = "/all_outgoings_for_current_user", method = RequestMethod.GET)
    public List<DebtDto> getAllOutgoingsForCurrentUser() {
        return service.getAllOutgoingsForCurrentUser();
    }

    @RequestMapping(value = "/all_incomings_for_current_user", method = RequestMethod.GET)
    public List<DebtDto> getAllIncomingsForCurrentUser() {
        return service.getAllIncomingsForCurrentUser();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long create(@RequestBody DebtDto debt) {
        return service.create(debt);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody DebtDto debt) {
        service.update(debt);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
