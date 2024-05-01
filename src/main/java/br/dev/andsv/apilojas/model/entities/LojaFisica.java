package br.dev.andsv.apilojas.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "loja_fisica")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class LojaFisica extends Loja {
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Column(name = "numero_funcionarios")
    private Integer numeroFuncionarios;

    public LojaFisica(Long id, String cnpj, String nome, String segmento, String telefone, Endereco endereco, Integer numeroFuncionarios) {
        super(id, cnpj, nome, segmento, telefone);
        this.endereco = endereco;
        this.numeroFuncionarios = numeroFuncionarios;
    }

    public LojaFisica(String cnpj, String nome, String segmento, String telefone, Endereco endereco, Integer numeroFuncionarios) {
        super(cnpj, nome, segmento, telefone);
        this.endereco = endereco;
        this.numeroFuncionarios = numeroFuncionarios;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        LojaFisica that = (LojaFisica) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "nome = " + getNome() + ", " +
                "cnpj = " + getCnpj() + ", " +
                "segmento = " + getSegmento() + ", " +
                "telefone = " + getTelefone() + ", " +
                "endereco = " + getEndereco() + ", " +
                "numeroFuncionarios = " + getNumeroFuncionarios() + ")";
    }
}