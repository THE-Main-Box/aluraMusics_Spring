package br.com.alura.aluraMusics.model;

public enum GroupType {
    BAND("Banda"),
    SOLO("Solo"),
    DUET("Dueto");

    private String portugeseMusicType;

    GroupType(String portugeseArtistType) {
        this.portugeseMusicType = portugeseArtistType;
    }

    public static GroupType fromString(String text) throws Exception {
        for (GroupType a : GroupType.values()) {
            if (a.portugeseMusicType.equalsIgnoreCase(text)) {
                return a;
            }
        }
        throw new Exception("Insira um valor válido");
    }

}
