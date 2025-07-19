package com.talento.tech.controller;

import com.talento.tech.controller.modelos.pedidos.NewPedidoDTO;
import com.talento.tech.controller.modelos.pedidos.PedidoDTO;
import com.talento.tech.service.PedidoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class PedidoController {
    private PedidoService service;
    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoDTO>> findAll(){
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @PostMapping("/pedidos")
    public ResponseEntity<PedidoDTO> addPedido(@Valid @RequestBody NewPedidoDTO pedido) {
        try {
            return ResponseEntity.ok(this.service.save(pedido));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/pedidos")
    public ResponseEntity<List<PedidoDTO>> findByUsuarioId(@PathVariable Long usuarioId) {
        return ResponseEntity.ok().body(this.service.findByUsuarioId(usuarioId));
    }
}
