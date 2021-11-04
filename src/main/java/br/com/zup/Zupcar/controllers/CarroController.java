package br.com.zup.Zupcar.controllers;


import br.com.zup.Zupcar.CarroService;
import br.com.zup.Zupcar.dtos.CarroDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import java.util.List;

@RestController
@RequestMapping("/carros") //mapeia as requisições para os endpoints contidos nele
public class CarroController {

    @Autowired
    private CarroService carroService; //*INJEÇÃO DE DEPENDÊNCIA!!*



    @GetMapping
    public List<CarroDTO> exibirTodosOsCarros() {
        return carroService.pegarTodosOsCarros();
    }

    @PostMapping // Request Mapping utilizando o verbo POST do http
    @ResponseStatus(HttpStatus.CREATED) // resposta  do cadastro passa a ser a 201
    public void cadastrarCarro(@RequestBody CarroDTO carroDTO) {
        carroService.salvarCarro(carroDTO);
    }

    /*@GetMapping("/{nomeCarro}")//{} -> captura a variável na url
    public CarroDTO exibirCarro(@PathVariable String nomeCarro){ //passa a variável como parâmetro para o método ser executado
        System.out.println(nomeCarro);
        return new CarroDTO();
    }*/

    @GetMapping("{nomeCarro}")
    public CarroDTO buscarCarro(@PathVariable String nomeCarro) {
                return carroService.buscarCarro(nomeCarro);
            }


    @PutMapping("{nomeCarro}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CarroDTO atualizarCarro(@PathVariable String nomeCarro, @RequestBody CarroDTO carroDTO) { //Como parametro vai precisar além do nome do carro a ser atualizado, das
                                                                                                     //informações do body que serão atualizadas através do PUT
                return carroService.atualizarCarro(nomeCarro,carroDTO);

            }


    @DeleteMapping("{nomeCarro}")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public void deletarCarro(@PathVariable String nomeCarro) {
         carroService.apagarCarro(nomeCarro);
    }
}
