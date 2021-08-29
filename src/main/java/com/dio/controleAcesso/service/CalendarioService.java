package com.dio.controleAcesso.service;

import com.dio.controleAcesso.model.Calendario;
import com.dio.controleAcesso.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {

    CalendarioRepository calendarioRepository;

    @Autowired
    public CalendarioService(CalendarioRepository calendarioRepository){
        this.calendarioRepository = calendarioRepository;
    }

    public Calendario saveCalendario(Calendario calendario) {
        return calendarioRepository.save(calendario);
    }

    public List<Calendario> findAll(){
        return calendarioRepository.findAll();
    }

    public Optional<Calendario> getById(Long idCalendario){
        return calendarioRepository.findById(idCalendario);
    }

    public Calendario updateCalendario(Calendario calendario){
        return calendarioRepository.save(calendario);
    }

    public void deleteCalendario(Long idCalendario){
        calendarioRepository.deleteById(idCalendario);
    }
}
