package ru.tdtb.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.tdtb.business.dto.DebtDto;
import ru.tdtb.business.service.DebtService;

import java.io.IOException;
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
    public List<DebtDto> getAllOutgoingsForCurrentUser(@RequestParam("page_num") Integer pageNumber,
                                                       @RequestParam("page_size") Integer pageSize) {
        return service.getAllOutgoingsForCurrentUser(pageNumber, pageSize);
    }

    @RequestMapping(value = "/all_incomings_for_current_user", method = RequestMethod.GET)
    public List<DebtDto> getAllIncomingsForCurrentUser() {
        return service.getAllIncomingsForCurrentUser();
    }

    @RequestMapping(value = "/count_of_all_outgoings_for_current_user", method = RequestMethod.GET)
    public Integer getCountOfAllOutgoingsForCurrentUser() {
        return service.getCountOfAllOutgoingsForCurrentUser();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long create(@RequestBody DebtDto debt) {
        return service.create(debt);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody DebtDto debt) {
        service.update(debt);
    }

    @RequestMapping(value = "/{id}/image", method = RequestMethod.POST)
    public void addImage(@PathVariable("id") Long id,
                         @RequestParam("file") MultipartFile file) throws IOException {
        service.addImage(id, file.getBytes());
    }

    @RequestMapping(value = "/{id}/image", method = RequestMethod.GET)
    public byte[] getImage(@PathVariable("id") Long id) throws IOException {
        return service.getImage(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
