package br.com.fiap.agroorbit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class PropriedadeCreateRequest {

    @NotBlank(message = "O nome da propriedade é obrigatório")
    private String nome;

    @NotNull(message = "A área em hectares é obrigatória")
    @Positive(message = "A área deve ser positiva")
    private Double areaHectares;

    private String culturaPrincipal;

    private Double latitude;

    private Double longitude;

    @NotNull(message = "O id do produtor é obrigatório")
    private Long produtorId;

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

    public Long getProdutorId() {
        return produtorId;
    }

    public void setProdutorId(Long produtorId) {
        this.produtorId = produtorId;
    }
}
