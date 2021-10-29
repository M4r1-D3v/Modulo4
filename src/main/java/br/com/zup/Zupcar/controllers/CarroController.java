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
@RequestMapping("/carros")
public class CarroController {

    private List<CarroDTO> concessionaria = new ArrayList<>();

    @GetMapping
    public List<CarroDTO> exibirTodosOsCarros() {
        return concessionaria;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarCarro(@RequestBody CarroDTO carroDTO) {
        concessionaria.add(carroDTO);
    }

    /*@GetMapping("/{nomeCarro}")//{} -> captura a variável na url
    public CarroDTO exibirCarro(@PathVariable String nomeCarro){ //passa a variável como parâmetro para o método ser executado
        System.out.println(nomeCarro);
        return new CarroDTO();
    }*/

    @GetMapping("{nomeCarro}")
    public CarroDTO buscarCarro(@PathVariable String nomeCarro) {
        CarroDTO carroDTO = null;
        for (CarroDTO nomeReferencia : concessionaria) {
            if (nomeReferencia.getModelo().equals(nomeCarro)) {
                System.out.println(nomeReferencia);
                return nomeReferencia;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{nomeCarro}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CarroDTO atualizarCarro(@PathVariable String nomeCarro, @RequestBody CarroDTO carroDTO) { //Como parametro vai precisar além do nome do carro a ser atualizado, das
        //informações do body que serão atualizadas através do PUT
        for (CarroDTO nomereferencia : concessionaria) {
            if (nomereferencia.getModelo().equals(nomeCarro)) {
                nomereferencia.setAno(carroDTO.getAno());
                nomereferencia.setMotor(carroDTO.getMotor());
                nomereferencia.setCor(carroDTO.getCor());

                return nomereferencia;


            }

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("{nomeCarro}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCarro(@PathVariable String nomeCarro) {
        CarroDTO carroDTODeletado = null;
        for (CarroDTO carroReferencia : concessionaria) {
            if (carroReferencia.getModelo().equals(nomeCarro)) {
                carroDTODeletado = carroReferencia;
                concessionaria.remove(carroReferencia);
            }

        }
        if (carroDTODeletado == null) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
}
