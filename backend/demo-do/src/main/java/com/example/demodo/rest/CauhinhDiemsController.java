package com.example.demodo.rest;

import com.example.demodo.entity.CauhinhDiems;
import com.example.demodo.service.CauhinhDiemsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("")
public class CauhinhDiemsController {

    @Autowired
    private CauhinhDiemsService cauhinhdiemsService;

    @PostMapping("/cauhinhdiems/add")
    public String createCauHinhDiem(@RequestParam("idMonHoc") Integer id, @RequestBody Map<String, Object> body) {
        ObjectMapper objectMapper = new ObjectMapper();
        CauhinhDiems cauhinhDiems = objectMapper.convertValue(body.get("cauHinh"), CauhinhDiems.class);
        cauhinhDiems.setMonhocId(id);
        LocalDate currentDate = LocalDate.now(); // Lấy ngày hiện tại
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng chuỗi "dd/MM/yyyy"
        String dateString = currentDate.format(formatter);
        // Chuyển đổi ngày thành chuỗi định dạng "dd/MM/yyyy"
        cauhinhDiems.setNgaybatdau(dateString);
        cauhinhdiemsService.save(cauhinhDiems);
        return "ok";
    }

    @PutMapping("/cauhinhdiems/{id}")
    public String updateCauHinhDiem(@PathVariable("id") Integer id, @RequestBody CauhinhDiems cauhinhDiems) {
        String message = "";
        Optional<CauhinhDiems> cauhinhDiems2 = cauhinhdiemsService.find(id);
        if (!cauhinhDiems2.isPresent())
            return "mon hoc nay chua duoc cau hinh diem";
        CauhinhDiems cauhinhDiems3 = cauhinhDiems2.get();
        cauhinhDiems3.setDiembt(cauhinhDiems.getDiembt());
        cauhinhDiems3.setDiemcc(cauhinhDiems.getDiemcc());
        cauhinhDiems3.setDiemck(cauhinhDiems.getDiemck());
        cauhinhDiems3.setDiemkt(cauhinhDiems.getDiemkt());
        cauhinhDiems3.setDiemth(cauhinhDiems.getDiemth());

        cauhinhdiemsService.save(cauhinhDiems3);

        return "ok";

    }

    @GetMapping("/cauhinhdiems/{id}")
    public ArrayList<CauhinhDiems> getById(@PathVariable(value = "id") Integer id) {
        return cauhinhdiemsService.findByIdMonHoc(id);
    }

    @GetMapping("/cauhinhdiems")
    public List<CauhinhDiems> getAll() {
        return cauhinhdiemsService.findAll();
    }

    @DeleteMapping("/cauhinhdiems/{id}")
    public void deleteById(@PathVariable(value = "id") Integer id) {
        cauhinhdiemsService.delete(id);
    }

}