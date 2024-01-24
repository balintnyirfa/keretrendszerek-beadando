package com.keretrendszerek.beadando.controller;

import com.keretrendszerek.beadando.entity.Record;
import com.keretrendszerek.beadando.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecordController {
    @Autowired
    private RecordService recordService;

    /*@GetMapping("/")
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }*/

    /*@GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "artist", "asc", model);
    }*/

    @PostMapping("/saveRecord")
    public String saveRecord(@ModelAttribute("record") Record record) {
        recordService.saveRecord(record);
        return "redirect:/listRecords";
    }

    @GetMapping("/showNewRecordForm")
    public String showNewRecordForm(Model model) {
        Record record = new Record();
        model.addAttribute("record", record);
        return "uploadRecord";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {
        Record record = recordService.getRecordById(id);

        model.addAttribute("record", record);
        return "updateRecord";
    }

    @GetMapping("/deleteRecord/{id}")
    public String deleteRecord(@PathVariable (value = "id") long id) {
        this.recordService.deleteRecordById(id);
        return "redirect:/listRecords";
    }

    /*@GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Record> page = recordService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Record> listRecords = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listRecords", listRecords);
        return "listRecords";
    }*/
}
