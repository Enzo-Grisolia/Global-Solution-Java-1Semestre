package br.com.fiap.agroorbit.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "propriedades")
public class Propriedade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false)
    private Double areaHectares;

    @Column(length = 60)
    private String culturaPrincipal;

    private Double latitude;

    private Double longitude;


    @ManyToOne
    @JoinColumn(name = "produtor_id", nullable = false)
    private Produtor produtor;

    @OneToMany(mappedBy = "propriedade")
    @JsonIgnore
    private List<LeituraSatelite> leituras = new ArrayList<>();

    @OneToMany(mappedBy = "propriedade")
    @JsonIgnore
    private List<AlertaClimatico> alertas = new ArrayList<>();

    public Propriedade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getAreaHectares() {
        return areaHectares;
    }

    public void setAreaHectares(Double areaHectares) {
        this.areaHectares = areaHectares;
    }

    public String getCulturaPrincipal() {
        return culturaPrincipal;
    }

    public void setCulturaPrincipal(String culturaPrincipal) {
        this.culturaPrincipal = culturaPrincipal;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Produtor getProdutor() {
        return produtor;
    }

    public void setProdutor(Produtor produtor) {
        this.produtor = produtor;
    }

    public List<LeituraSatelite> getLeituras() {
        return leituras;
    }

    public void setLeituras(List<LeituraSatelite> leituras) {
        this.leituras = leituras;
    }

    public List<AlertaClimatico> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<AlertaClimatico> alertas) {
        this.alertas = alertas;
    }
}
