package com.j;

public class SearchResult {
    private String filePath;
    private int  line;
    private String content;

    public SearchResult() {
    }

    public String getFilePath() {
        return filePath;
    }

    public int  getLine() {
        return line;
    }

    public String getContent() {
        return content;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
