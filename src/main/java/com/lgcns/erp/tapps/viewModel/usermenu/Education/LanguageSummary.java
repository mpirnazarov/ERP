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
    private int id;
    private int langId, listeningId, readingId, writingId, speakingId;

    public LanguageSummary() {
    }

    public LanguageSummary(String language, String listening, String reading, String writing, String speaking, int id) {
        this.language = language;
        this.listening = listening;
        this.reading = reading;
        this.writing = writing;
        this.speaking = speaking;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLangId() {
        return langId;
    }

    public void setLangId(int langId) {
        this.langId = langId;
    }

    public int getListeningId() {
        return listeningId;
    }

    public void setListeningId(int listeningId) {
        this.listeningId = listeningId;
    }

    public int getReadingId() {
        return readingId;
    }

    public void setReadingId(int readingId) {
        this.readingId = readingId;
    }

    public int getWritingId() {
        return writingId;
    }

    public void setWritingId(int writingId) {
        this.writingId = writingId;
    }

    public int getSpeakingId() {
        return speakingId;
    }

    public void setSpeakingId(int speakingId) {
        this.speakingId = speakingId;
    }

    @Override
    public String toString() {
        return "LanguageSummary{" +
                "language='" + language + '\'' +
                ", listening='" + listening + '\'' +
                ", reading='" + reading + '\'' +
                ", writing='" + writing + '\'' +
                ", speaking='" + speaking + '\'' +
                ", id=" + id +
                ", langId=" + langId +
                ", listeningId=" + listeningId +
                ", readingId=" + readingId +
                ", writingId=" + writingId +
                ", speakingId=" + speakingId +
                '}';
    }
}
