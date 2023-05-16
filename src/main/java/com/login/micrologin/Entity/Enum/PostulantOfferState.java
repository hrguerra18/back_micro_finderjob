package com.login.micrologin.Entity.Enum;

public enum PostulantOfferState {
    ACCEPTED(1), REFUSED(2), ONHOLD(3);

    private int postulantOffertStateId;

    PostulantOfferState(int postulantOffertStateId) {
        this.postulantOffertStateId = postulantOffertStateId;
    }

    public int returnPostulantOffertStateId(){
        return postulantOffertStateId;
    }
}
