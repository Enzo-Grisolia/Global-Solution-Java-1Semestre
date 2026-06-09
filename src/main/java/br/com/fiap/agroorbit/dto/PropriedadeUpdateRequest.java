package br.com.fiap.agroorbit.dto;


public class PropriedadeUpdateRequest {

    private String nome;
    private Double areaHectares;
    private String culturaPrincipal;
    private Double latitude;
    private Double longitude;
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
