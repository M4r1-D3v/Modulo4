package br.com.zup.Zupcar.controllers;

import br.com.zup.Zupcar.dtos.MotoDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/motos")

public class MotoController {

    private List<MotoDTO> concessionaria = new ArrayList<>();

    @GetMapping

    public List<MotoDTO> exibirMotos() {
        return concessionaria;
    }


    @PostMapping

    private void cadastrarMoto(@RequestBody MotoDTO motoDTO) {
        concessionaria.add(motoDTO);

    }

}
