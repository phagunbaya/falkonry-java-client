package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Signal {

    private String tagIdentifier;
    private String delimiter;
    private String valueIdentifier;
    private Boolean isSignalPrefix;

    public String getTagIdentifier() {
        return tagIdentifier;
    }

    public void setTagIdentifier(String tagIdentifier) {
        this.tagIdentifier = tagIdentifier;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getValueIdentifier() {
        return valueIdentifier;
    }

    public void setValueIdentifier(String valueIdentifier) {
        this.valueIdentifier = valueIdentifier;
    }

    public Boolean getIsSignalPrefix() {
        return isSignalPrefix;
    }

    public void setIsSignalPrefix(Boolean isSignalPrefix) {
        this.isSignalPrefix = isSignalPrefix;
    }

}
