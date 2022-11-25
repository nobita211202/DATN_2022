package org.datn.controller;

import org.datn.entity.Voucher;
import org.datn.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController("/api/voucher")
public class VoucherController {
    @Autowired
    VoucherService voucherService;
    @GetMapping("/listVoucher")
    public List<Voucher> findAll(){
        return voucherService.findAll();
    }
    @PostMapping("/createVoucher")
    public Voucher createVoucher(@RequestBody Voucher voucher){
        return voucherService.createVoucher(voucher);
    }
    @PutMapping("/updateVoucher/{id}")
    public Voucher updateVoucher(@PathVariable("id") Long id,@RequestBody Voucher voucher){
        return voucherService.updateVoucher(voucher);
    }
    @DeleteMapping("/deleteVoucher/{id}")
    public void deleteVoucher(@PathVariable("id") Long id){
         voucherService.deleteVoucher(id);
    }
    @GetMapping("/findByIDVoucher/{id}")
    public Voucher findByIDVoucher(@PathVariable("id") Long id){
        return voucherService.findByIDVoucher(id);
    }
}
