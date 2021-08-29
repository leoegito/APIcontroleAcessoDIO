package com.dio.controleAcesso.service;

import com.dio.controleAcesso.model.BancoHoras;
import com.dio.controleAcesso.repository.BancoHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoHorasService {

    BancoHorasRepository bancoHorasRepository;

    @Autowired
    public BancoHorasService(BancoHorasRepository bancoHorasRepository){
        this.bancoHorasRepository = bancoHorasRepository;
    }

    public BancoHoras saveBancoHoras(BancoHoras bancoHoras) {
        return bancoHorasRepository.save(bancoHoras);
    }

    public Optional<BancoHoras> getById(BancoHoras.BancoHorasId bancoHorasId){
        return bancoHorasRepository.findById(bancoHorasId);
    }

    public List<BancoHoras> findAll(){
        return bancoHorasRepository.findAll();
    }

    public BancoHoras updateBancoHoras(BancoHoras bancoHoras){
        return bancoHorasRepository.save(bancoHoras);
    }

    public void deleteBancoHoras(BancoHoras.BancoHorasId bancoHorasId){
        bancoHorasRepository.deleteById(bancoHorasId);
    }

}
