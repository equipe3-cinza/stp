package br.ufg.inf.backend.stp.medicamentoPrescrito;

public enum UnidadeDosagem {
    mg("Miligrama"),
    µg("Micrograma"),
    mL("Mililitro"),
    cp("Comprimido"),
    UI("Unidades Internacionais");

    private final String descricao;

    UnidadeDosagem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
