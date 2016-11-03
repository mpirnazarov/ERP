package com.lgcns.erp.tapps.viewModel.usermenu.Education;

/**
 * Created by Muslimbek on 11/2/2016.
 */
public class LanguageSummary {
    private String language;
    private String listening;
    private String reading;
    private String writing;
    private String speaking;

    public LanguageSummary(String language, String listening, String reading, String writing, String speaking) {
        this.language = language;
        this.listening = listening;
        this.reading = reading;
        this.writing = writing;
        this.speaking = speaking;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getListening() {
        return listening;
    }

    public void setListening(String listening) {
        this.listening = listening;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getSpeaking() {
        return speaking;
    }

    public void setSpeaking(String speaking) {
        this.speaking = speaking;
    }
}
