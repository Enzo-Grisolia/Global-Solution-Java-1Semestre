package br.com.fiap.agroorbit.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;


public class LeituraSateliteCreateRequest {

    @NotNull(message = "A data da leitura é obrigatória")
    private LocalDate data;

    @NotNull(message = "O NDVI é obrigatório")
    private Double ndvi;

    private Double temperaturaSolo;

    private Double umidade;

    private String fonteSatelite;

    @NotNull(message = "O id da propriedade é obrigatório")
    private Long propriedadeId;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getNdvi() {
        return ndvi;
    }

    public void setNdvi(Double ndvi) {
        this.ndvi = ndvi;
    }

    public Double getTemperaturaSolo() {
        return temperaturaSolo;
    }

    public void setTemperaturaSolo(Double temperaturaSolo) {
        this.temperaturaSolo = temperaturaSolo;
    }

    public Double getUmidade() {
        return umidade;
    }

    public void setUmidade(Double umidade) {
        this.umidade = umidade;
    }

    public String getFonteSatelite() {
        return fonteSatelite;
    }

    public void setFonteSatelite(String fonteSatelite) {
        this.fonteSatelite = fonteSatelite;
    }

    public Long getPropriedadeId() {
        return propriedadeId;
    }

    public void setPropriedadeId(Long propriedadeId) {
        this.propriedadeId = propriedadeId;
    }
}
