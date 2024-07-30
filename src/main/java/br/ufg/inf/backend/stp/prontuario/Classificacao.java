package br.ufg.inf.backend.stp.prontuario;

public enum Classificacao {
    PRIMARIA("primaria"), SECUNDARIA("secundaria"), TERCIARIA("terciaria");

    private final String classificacao;

    Classificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getClassificacao() {
        return classificacao;
    }
}