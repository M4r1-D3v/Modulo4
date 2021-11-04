package br.com.zup.Zupcar;


import br.com.zup.Zupcar.dtos.CarroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarroService {

    private List<CarroDTO> concessionaria = new ArrayList<>();// criar lista para adicionar carros

    public List<CarroDTO> pegarTodosOsCarros(){
        return concessionaria;
    }

    public void  salvarCarro(CarroDTO carroDTO){
        concessionaria.add(carroDTO);
    }

    public CarroDTO buscarCarro(String nomeDoCarro){
        for (CarroDTO objeto: concessionaria){
            if (objeto.getModelo().equals(nomeDoCarro)){
                return objeto;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrei");
    }

    public CarroDTO atualizarCarro(String nomeDoCarro, CarroDTO carroDTO){
        CarroDTO carro = buscarCarro(nomeDoCarro);
        carro.setAno(carroDTO.getAno());
        carro.setCor(carroDTO.getCor());
        carro.setMotor(carroDTO.getMotor());

        return carro;

    }
    public void apagarCarro(String nome){
        CarroDTO carroReferencia = null;
        for (CarroDTO objeto: concessionaria){
            if (objeto.getModelo().equals(nome)){
                carroReferencia = objeto;

            }
        }
        if (carroReferencia == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            concessionaria.remove(carroReferencia);
        }

    }
}
