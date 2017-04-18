package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author dev-falkonry-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Signal {

    private String tagIdentifier;
    private String delimiter;
    private String valueIdentifier;
    private Boolean isSignalPrefix;

    /**
     *
     * @return
     */
    public String getTagIdentifier() {
        return tagIdentifier;
    }

    /**
     *
     * @param tagIdentifier
     */
    public void setTagIdentifier(String tagIdentifier) {
        this.tagIdentifier = tagIdentifier;
    }

    /**
     *
     * @param delimiter
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     *
     * @return
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     *
     * @return
     */
    public String getValueIdentifier() {
        return valueIdentifier;
    }

    /**
     *
     * @param valueIdentifier
     */
    public void setValueIdentifier(String valueIdentifier) {
        this.valueIdentifier = valueIdentifier;
    }

    /**
     *
     * @return
     */
    public Boolean getIsSignalPrefix() {
        return isSignalPrefix;
    }

    /**
     *
     * @param isSignalPrefix
     */
    public void setIsSignalPrefix(Boolean isSignalPrefix) {
        this.isSignalPrefix = isSignalPrefix;
    }

}
