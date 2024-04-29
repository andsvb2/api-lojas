package br.dev.andsv.apilojas.core.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cnpj", unique = true)
    private String cnpj;

    @Column(name = "nome")
    private String nome;

    @Column(name = "segmento")
    private String segmento;

    @Column(name = "telefone")
    private String telefone;

    public Loja(String cnpj, String nome, String segmento, String telefone) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.segmento = segmento;
        this.telefone = telefone;
    }

}