package com.sparta_spring.sparta_spring3.components;

public class Components {
    private String baseUrl;
    private String slide;
    private String srcUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public Components setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String getSlide() {
        return slide;
    }

    public Components setSlide(String slide) {
        this.slide = slide;
        return this;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public Components setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
        return this;
    }
}
