package org.datn.controller;

import org.datn.dao.VoucherDao;
import org.datn.entity.VoucherType;
import org.datn.service.VoucherTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController("/api/voucherType")
public class VoucherTypeController {
    @Autowired
    VoucherTypeService voucherTypeService;

    @GetMapping("/listVoucherType")
    public List<VoucherType> getAll(){
        return voucherTypeService.findAll();
    }
    @PostMapping("/createVoucherType")
    public VoucherType createVoucherType (@RequestBody VoucherType voucherType){
        return voucherTypeService.createVoucherType(voucherType);
    }
    @PutMapping("/updateVoucherType/{id}")
    public VoucherType updateVoucherType(@PathVariable("id") Long id,VoucherType voucherType){
        return voucherTypeService.updateVoucherType(voucherType);
    }
    @DeleteMapping("/deleteVoucherType/{id}")
    public void deleteVoucherType(@PathVariable("id") Long id){
        voucherTypeService.deleteVoucherType(id);
    }
    @GetMapping("/findByIdVoucherType/{id}")
    public VoucherType findByIDVoucherType(@PathVariable("id") Long id){
        return voucherTypeService.findByIDVoucherType(id);
    }
}
