package br.dev.andsv.apilojas.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "cnpj", unique = true, nullable = false)
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