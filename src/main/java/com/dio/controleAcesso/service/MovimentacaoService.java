package com.dio.controleAcesso.service;

import com.dio.controleAcesso.model.Movimentacao;
import com.dio.controleAcesso.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository){
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public Movimentacao saveMovimentacao(Movimentacao movimentacao){
        return movimentacaoRepository.save(movimentacao);
    }

    public Optional<Movimentacao> getById(Movimentacao.MovimentacaoId movimentacaoId){
        return movimentacaoRepository.findById(movimentacaoId);
    }

    public Movimentacao updateMovimentacao(Movimentacao movimentacao){
        return movimentacaoRepository.save(movimentacao);
    }

    public void deleteMovimentacao(Movimentacao.MovimentacaoId movimentacaoId){
        movimentacaoRepository.deleteById(movimentacaoId);
    }

    public List<Movimentacao> findAll(){
        return movimentacaoRepository.findAll();
    }

}
