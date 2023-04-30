package com.login.micrologin.Entity.Enum;

public enum OfferState{
    ACTIVE(1), COMPLETED_APPLICATIONS(2), CANCEL_APPLICATIONS(3);

    private int offerStateId;

    OfferState(int offerStateId) {
        this.offerStateId = offerStateId;
    }

    public int returnOfferStateId(){
        return offerStateId;
    }
}
