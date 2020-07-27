package com.bt.au.shoppingcart.model.wit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityData {


    /*@JsonProperty("BRAND")
    private Map<String, String> brand;*/

    @JsonProperty("confidence")
    private long confidence;

    @JsonProperty("value")
    private String value;

    @JsonProperty("type")
    private String type;

    @JsonProperty("metadata")
    private String metadata;

    @JsonProperty("entities")
    private EntityObject entityObject;




}
