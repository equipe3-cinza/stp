package br.ufg.inf.backend.stp.role;

public enum TypesRole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_MEDICO("ROLE_MEDICO"),
    ROLE_MEDICO_REGULADOR("ROLE_MEDICO_REGULADOR"),
    ROLE_USUARIO("ROLE_USUARIO");

    private final String typesRole;

    TypesRole(String typesRole) {
    this.typesRole = typesRole;
    }

    public String getTypesRole() {
        return typesRole;
    }

}