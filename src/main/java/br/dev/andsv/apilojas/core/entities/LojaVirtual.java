package br.dev.andsv.apilojas.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "loja_virtual")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class LojaVirtual extends Loja {
    @Column(name = "url")
    private String url;

    @Column(name = "avaliacao")
    private String avaliacao;

    public LojaVirtual(Long id, String cnpj, String nome, String segmento, String telefone, String url, String avaliacao) {
        super(id, cnpj, nome, segmento, telefone);
        this.url = url;
        this.avaliacao = avaliacao;
    }

    public LojaVirtual(String cnpj, String nome, String segmento, String telefone, String url, String avaliacao) {
        super(cnpj, nome, segmento, telefone);
        this.url = url;
        this.avaliacao = avaliacao;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        LojaVirtual that = (LojaVirtual) o;
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
                "url = " + getUrl() + ", " +
                "avaliacao = " + getAvaliacao() + ")";
    }
}