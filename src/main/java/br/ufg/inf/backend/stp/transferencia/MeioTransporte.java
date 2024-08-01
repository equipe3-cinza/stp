package br.ufg.inf.backend.stp.transferencia;

public enum MeioTransporte {
    AMBULANCIA(80.0), // km/h
    HELICOPTERO(200.0),
    AVIAO(800.0),
    EVTOL(150.0);

    private final double velocidadeMedia;

    MeioTransporte(double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }
}
