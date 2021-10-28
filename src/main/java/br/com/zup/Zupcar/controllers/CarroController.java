package br.com.zup.Zupcar.controllers;


import br.com.zup.Zupcar.dtos.CarroDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import java.util.List;

@RestController
@RequestMapping ("/carros")
public class CarroController {

    private List<CarroDTO>concessionaria = new ArrayList<>();

    @GetMapping
    public List<CarroDTO>exibirTodosOsCarros(){
        return concessionaria;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarCarro(@RequestBody CarroDTO carroDTO){
        concessionaria.add(carroDTO);
    }

    /*@GetMapping("/{nomeCarro}")//{} -> captura a variável na url
    public CarroDTO exibirCarro(@PathVariable String nomeCarro){ //passa a variável como parâmetro para o método ser executado
        System.out.println(nomeCarro);
        return new CarroDTO();
    }*/

    @GetMapping("{nomeCarro}")
    public CarroDTO buscarCarro(@PathVariable String nomeCarro){
        CarroDTO carroDTO = null;
        for (CarroDTO nomeReferencia:concessionaria) {
            if (nomeReferencia.getModelo().equals(nomeCarro)){
                System.out.println(nomeReferencia);
                return nomeReferencia;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{nomeCarro}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CarroDTO atualizarCarro(@PathVariable String nomeCarro){
        for (CarroDTO nomereferencia:concessionaria) {
            if (nomereferencia.getModelo().equals(nomeCarro)){
                return nomereferencia;
            }

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }


}
